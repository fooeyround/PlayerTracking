package tech.lemonlime.PlayerTracking.registry;

import eu.pb4.polymer.core.api.block.SimplePolymerBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.block.RawTrackiniumBlock;
import tech.lemonlime.PlayerTracking.block.TrackingChargerBlock;
import tech.lemonlime.PlayerTracking.block.TrackiniumBlock;
import tech.lemonlime.PlayerTracking.block.TrackiniumOreBlock;

public class ModBlocks {




    public static final Block TRACKING_CHARGER_BLOCK = new TrackingChargerBlock(FabricBlockSettings.of(Material.PORTAL).hardness(5.5F));

    public static final Block TRACKINIUM_ORE = new TrackiniumOreBlock(FabricBlockSettings.of(Material.PORTAL).hardness(3F));


    public static final Block RAW_TRACKINIUM_BLOCK = new RawTrackiniumBlock(FabricBlockSettings.of(Material.PORTAL).hardness(2F));


    public static final Block TRACKINIUM_BLOCK = new TrackiniumBlock(FabricBlockSettings.of(Material.PORTAL).hardness(4F));


    public static void register() {


        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"tracking_charger"),TRACKING_CHARGER_BLOCK);

        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"trackinium_ore"),TRACKINIUM_ORE);

        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"raw_trackinium_block"),RAW_TRACKINIUM_BLOCK);


        Registry.register(Registries.BLOCK,new Identifier(PlayerTracking.MODID,"trackinium"),TRACKINIUM_BLOCK);






    }





}
