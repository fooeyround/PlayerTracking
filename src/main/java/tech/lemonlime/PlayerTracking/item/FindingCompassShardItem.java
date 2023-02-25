package tech.lemonlime.PlayerTracking.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtString;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.registry.ModItems;

public class FindingCompassShardItem extends Item implements PolymerItem {
    public FindingCompassShardItem(Settings settings) {
        super(settings);
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.ECHO_SHARD;
    }


    @Override
    public String getTranslationKey() {
        return "Tracking Shard (rename me)";
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        ItemStack stack = user.getStackInHand(hand);

        String player_id = PlayerTracking.getPlayerFromString(world,stack.getName().getString()).getUuidAsString();

        if (!stack.hasCustomName() || player_id == null) {
            return TypedActionResult.fail(stack);
        }



        ItemStack new_stack = new ItemStack(ModItems.FINDING_COMPASS);





        new_stack.setSubNbt("tracked_player", NbtString.of(player_id));







        if (!user.getInventory().insertStack(new_stack)) {
            user.dropItem(new_stack, false);
        }

        stack.decrement(1);




        return TypedActionResult.consume(new_stack);
    }
}
