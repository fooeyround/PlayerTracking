package tech.lemonlime.PlayerTracking.item;

import eu.pb4.polymer.core.api.item.PolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import tech.lemonlime.PlayerTracking.PlayerTracking;

public class RawTrackiniumOreItem extends Item implements PolymerItem {

    PolymerModelData MODEL_DATA;

    public RawTrackiniumOreItem(Settings settings) {
        super(settings);
        this.MODEL_DATA = PolymerResourcePackUtils.requestModel(Items.DIAMOND, new Identifier(PlayerTracking.MODID, "item/trackinium_ore"));
    }

    @Override
    public Item getPolymerItem(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return Items.DIAMOND;
    }

    @Override
    public int getPolymerCustomModelData(ItemStack itemStack, @Nullable ServerPlayerEntity player) {
        return MODEL_DATA.value();
    }
}


