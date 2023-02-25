package tech.lemonlime.PlayerTracking.block;

import eu.pb4.polymer.core.api.block.PolymerBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tech.lemonlime.PlayerTracking.registry.ModItems;

import java.util.ArrayList;
import java.util.List;

public class TrackingChargerBlock extends Block implements PolymerBlock {

    public static final BooleanProperty CHARGED;

    public TrackingChargerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(CHARGED, false));
    }


    @Override
    public String getTranslationKey() {
        return "Tracking Charger";
    }

    @Override
    public Block getPolymerBlock(BlockState state) {

        return state.get(CHARGED) ? Blocks.REPEATING_COMMAND_BLOCK : Blocks.COMMAND_BLOCK;
    }


    @Deprecated
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> stacks = new ArrayList<>();
        stacks.add(new ItemStack(ModItems.TRACKING_CHARGER));

        if (state.get(CHARGED)) {
            stacks.add(new ItemStack(ModItems.TRACKINIUM_ORE));
        }

        return stacks;
    }

    private static boolean isChargeItem(ItemStack stack) {
        return stack.isOf(ModItems.TRACKINIUM_ORE);
    }



    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (hand == Hand.MAIN_HAND && !isChargeItem(itemStack) && isChargeItem(player.getStackInHand(Hand.OFF_HAND))) {
            return ActionResult.PASS;
        } else if (isChargeItem(itemStack) && !state.get(CHARGED)) {
            charge(world, pos, state);
            if (!player.getAbilities().creativeMode) {
                itemStack.decrement(1);
            }

            world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_SET_SPAWN, SoundCategory.BLOCKS, 1.0F, 1.0F);


            return ActionResult.success(world.isClient);
        } else return ActionResult.PASS;
    }



    public static void charge(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(CHARGED, true), Block.NOTIFY_ALL);
        world.playSound(null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, SoundCategory.BLOCKS, 1.0F, 1.0F);
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }

    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return state.get(CHARGED) ? 15 : 0;
    }

    static {
        CHARGED = BooleanProperty.of("charged");
    }



}
