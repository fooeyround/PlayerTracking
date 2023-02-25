package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class TrackingChargerBlock extends Block implements PolymerBlock {

    public static final IntProperty CHARGE_LEVEL;

    public TrackingChargerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(CHARGE_LEVEL, 0));
    }


    @Override
    public String getTranslationKey() {
        return "Tracking Charger";
    }

    @Override
    public Block getPolymerBlock(BlockState state) {

        int chargeLevel = state.get(CHARGE_LEVEL);

        if (chargeLevel >= 32) return Blocks.BEDROCK;

        if(chargeLevel > 9) return Blocks.STRUCTURE_BLOCK;

        if(chargeLevel == 9) return Blocks.REPEATING_COMMAND_BLOCK;

        if(chargeLevel > 0) return Blocks.CHAIN_COMMAND_BLOCK;

        return Blocks.COMMAND_BLOCK;
    }


    @Deprecated
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(new ItemStack(ModItems.TRACKING_CHARGER));

        if (state.get(CHARGE_LEVEL) >= 32) {
            stacks.add(new ItemStack(Items.DIAMOND_HORSE_ARMOR));
        }

        return stacks;
    }

    private static boolean isChargeItem(ItemStack stack) {
        return itemChargeLevel(stack) > 0;
    }

    public static int itemChargeLevel(ItemStack stack) {

        if(stack.isOf(Items.DIAMOND_BLOCK)) return 9;

        if(stack.isOf(Items.DIAMOND_HELMET)) return 5;
        if(stack.isOf(Items.DIAMOND_CHESTPLATE)) return 8;
        if(stack.isOf(Items.DIAMOND_LEGGINGS)) return 7;
        if(stack.isOf(Items.DIAMOND_BOOTS)) return 4;

        if(stack.isOf(Items.DIAMOND_AXE)) return 3;
        if(stack.isOf(Items.DIAMOND_PICKAXE)) return 3;
        if(stack.isOf(Items.DIAMOND_HOE)) return 2;
        if(stack.isOf(Items.DIAMOND_SHOVEL)) return 1;
        if(stack.isOf(Items.DIAMOND_HORSE_ARMOR)) return 32;



        if(stack.isOf(Items.DIAMOND)) return 1;



        return 0;
    }



    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && !isChargeItem(itemStack) && isChargeItem(player.getStackInHand(Hand.OFF_HAND))) {
            return ActionResult.PASS;
        } else if (isChargeItem(itemStack) && state.get(CHARGE_LEVEL) < 9) {
            charge(world, pos, state,itemChargeLevel(itemStack));
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);


            return ActionResult.success(world.isClient);
        } else return ActionResult.PASS;
    }



    public static void charge(World world, BlockPos pos, BlockState state, int ammount) {
        world.setBlockState(pos, state.with(CHARGE_LEVEL, state.get(CHARGE_LEVEL)+ammount), Block.NOTIFY_ALL);
        world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGE_LEVEL);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return Math.min(state.get(CHARGE_LEVEL),15);
    }

    static {
        CHARGE_LEVEL = IntProperty.of("charge_level", 0, 64);
    }



}
