package tech.lemonlime.PlayerTracking.registry;

import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.block.TrackingChargerBlock;
import tech.lemonlime.PlayerTracking.block.TrackiniumOreBlock;

public class ModBlocks {




    public static final Block TRACKING_CHARGER_BLOCK = new TrackingChargerBlock(FabricBlockSettings.of(Material.PORTAL).hardness(5F));

    public static final Block TRACKINIUM_ORE = new TrackiniumOreBlock(FabricBlockSettings.of(Material.PORTAL).hardness(4F));


    public static final Block TRACKINIUM_BLOCK = new SimplePolymerBlock(FabricBlockSettings.of(Material.PORTAL).hardness(4F),Blocks.STRUCTURE_BLOCK);


    public static void register() {


        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"tracking_charger"),TRACKING_CHARGER_BLOCK);

        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"trackinium_ore"),TRACKINIUM_ORE);

        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"trackinium"),TRACKINIUM_BLOCK);






    }





}
