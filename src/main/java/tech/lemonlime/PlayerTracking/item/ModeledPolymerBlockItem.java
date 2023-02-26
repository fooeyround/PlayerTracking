package tech.lemonlime.PlayerTracking.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;

public class ModeledPolymerBlockItem extends BlockItem implements PolymerItem {

    private final Item polymerItem;
    private final PolymerModelData modelData;
    public ModeledPolymerBlockItem(Block block, Settings settings, Item polymerItem, PolymerModelData modelData) {
        super(block, settings);
        this.polymerItem = polymerItem;
        this.modelData = modelData;
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.polymerItem;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return this.modelData.value();
    }


}


