package tech.lemonlime.PlayerTracking.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class ModeledItem extends Item implements PolymerItem {

    private final Item polymerItem;
    private final int modelId;
    public ModeledItem(Settings settings,Item polymerItem,int id) {
        super(settings);
        this.polymerItem = polymerItem;
        this.modelId = id;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }


    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelId;
    }
}
