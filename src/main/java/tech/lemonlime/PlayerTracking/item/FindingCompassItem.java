package tech.lemonlime.PlayerTracking.item;

import com.mojang.serialization.DataResult;
import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.nbt.*;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.poi.PointOfInterestTypes;
import org.jetbrains.annotations.Nullable;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.block.TrackingChargerBlock;
import tech.lemonlime.PlayerTracking.registry.ModBlocks;

import java.util.Objects;
import java.util.Optional;

public class FindingCompassItem extends Item implements PolymerItem {
    public FindingCompassItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.COMPASS;
    }

    @Override
    public ItemStack getPolymerItemStack(ItemStack itemStack, TooltipContext context, @Nullable ServerPlayerEntity player) {


        ItemStack polymer_stack = PolymerItem.super.getPolymerItemStack(itemStack, context, player);


        NbtCompound pre_compound = itemStack.getOrCreateNbt();

        NbtCompound compound = polymer_stack.getOrCreateNbt();


        if (pre_compound.contains("LodestonePos")) {
            compound.put("LodestonePos",pre_compound.get("LodestonePos"));
        }

        if (pre_compound.contains("LodestoneDimension")) {
            compound.put("LodestoneDimension",pre_compound.get("LodestoneDimension"));

        }


        return polymer_stack;
    }

    //Use
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        NbtCompound compound = stack.getOrCreateNbt();







        BlockPos blockPos = user.getBlockPos();


        PlayerEntity trackedPlayer = getPlayerFromString(world,compound.getString("tracked_player"));

        if (stack.getCount() > 1 || compound.getInt("uses") == 0 || trackedPlayer == null) {
            return TypedActionResult.fail(stack);
        }


        world.playSound(null, blockPos, SoundEvents.ITEM_LODESTONE_COMPASS_LOCK, SoundCategory.PLAYERS, 1.0F, 1.0F);


        //Track Player


        this.writeNbt(world.getRegistryKey(), trackedPlayer.getBlockPos(), compound);


        stack.setSubNbt("uses",NbtInt.of(stack.getOrCreateNbt().getInt("uses")-1));



        return TypedActionResult.success(stack);
    }

    //Refuel

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        ItemStack stack = context.getStack();

        BlockState state = context.getWorld().getBlockState(context.getBlockPos());

        World world = context.getWorld();

        PlayerEntity player = context.getPlayer();

        BlockPos blockPos = context.getBlockPos();





        if (!state.isOf(ModBlocks.TRACKING_CHARGER_BLOCK) || !state.get(TrackingChargerBlock.CHARGED) || stack.getCount() != 1 || player.getAbilities().creativeMode) {
            return ActionResult.FAIL;

        }


        //Take away to diamondBlock
        world.setBlockState(blockPos,state.with(TrackingChargerBlock.CHARGED, false));



        NbtCompound compound = stack.getOrCreateNbt();
        compound.put("uses", NbtInt.of(2));
        stack.setNbt(compound);










        return ActionResult.success(context.getWorld().isClient);
    }




    public static boolean hasLodestone(ItemStack stack) {
        NbtCompound nbtCompound = stack.getNbt();
        return nbtCompound != null && (nbtCompound.contains("LodestoneDimension") || nbtCompound.contains("LodestonePos"));
    }

    private static Optional<RegistryKey<World>> getLodestoneDimension(NbtCompound nbt) {
        return World.CODEC.parse(NbtOps.INSTANCE, nbt.get("LodestoneDimension")).result();
    }

    @Nullable
    public static GlobalPos createLodestonePos(NbtCompound nbt) {
        boolean bl = nbt.contains("LodestonePos");
        boolean bl2 = nbt.contains("LodestoneDimension");
        if (bl && bl2) {
            Optional<RegistryKey<World>> optional = getLodestoneDimension(nbt);
            if (optional.isPresent()) {
                BlockPos blockPos = NbtHelper.toBlockPos(nbt.getCompound("LodestonePos"));
                return GlobalPos.create(optional.get(), blockPos);
            }
        }

        return null;
    }

    @Nullable
    public static GlobalPos createSpawnPos(World world) {
        return world.getDimension().natural() ? GlobalPos.create(world.getRegistryKey(), world.getSpawnPos()) : null;
    }

    public boolean hasGlint(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("uses") > 0;
    }


    public void norun_inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient) {
            if (hasLodestone(stack)) {
                NbtCompound nbtCompound = stack.getOrCreateNbt();
                //For Clients to use the right client methods and checks.
                nbtCompound.putBoolean("PlayerTracked", true);
                if (nbtCompound.contains("PlayerTracked") && !nbtCompound.getBoolean("PlayerTracked")) {
                    return;
                }

                Optional<RegistryKey<World>> optional = getLodestoneDimension(nbtCompound);
                if (optional.isPresent() && optional.get() == world.getRegistryKey() && nbtCompound.contains("LodestonePos")) {
                    BlockPos blockPos = NbtHelper.toBlockPos(nbtCompound.getCompound("LodestonePos"));
                    if (!world.isInBuildLimit(blockPos) || !((ServerWorld) world).getPointOfInterestStorage().hasTypeAt(PointOfInterestTypes.LODESTONE, blockPos)) {
                        nbtCompound.remove("LodestonePos");
                    }
                }
            }

        }

    }

    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.DIAMOND_BLOCK);
    }



    private void writeNbt(RegistryKey<World> worldKey, BlockPos pos, NbtCompound nbt) {
        nbt.put("LodestonePos", NbtHelper.fromBlockPos(pos));
        DataResult<NbtElement> dataResult = World.CODEC.encodeStart(NbtOps.INSTANCE, worldKey);
        Objects.requireNonNull(PlayerTracking.LOGGER);
        dataResult.resultOrPartial(PlayerTracking.LOGGER::error).ifPresent((nbtElement) -> nbt.put("LodestoneDimension", nbtElement));
        nbt.putBoolean("PlayerTracked", true);
    }


    public String getTranslationKey(ItemStack stack) {
        return stack.getOrCreateNbt().getInt("uses") > 0 ? stack.getOrCreateNbt().getInt("uses")+" "+stack.getOrCreateNbt().getString("tracked_player") : stack.getOrCreateNbt().getString("tracked_player");
    }




    public PlayerEntity getPlayerFromString(World world, String string) {

        for (PlayerEntity player : world.getPlayers()) {
            if (player.getName().getString().equalsIgnoreCase(string)) {
                return player;
            }
        }

        return null;
    }

}
