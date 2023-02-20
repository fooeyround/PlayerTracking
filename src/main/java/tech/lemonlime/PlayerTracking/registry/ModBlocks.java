package tech.lemonlime.PlayerTracking.registry;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.block.TrackingChargerBlock;

public class ModBlocks {




    public static final Block TRACKING_CHARGER_BLOCK = new TrackingChargerBlock(FabricBlockSettings.of(Material.PORTAL).hardness(2F));


    public static void register() {


        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"tracking_charger"),TRACKING_CHARGER_BLOCK);




    }





}
