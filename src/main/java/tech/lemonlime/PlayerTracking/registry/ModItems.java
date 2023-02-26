package tech.lemonlime.PlayerTracking.registry;

import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.PlayerTracking;
import tech.lemonlime.PlayerTracking.item.FindingCompassItem;
import tech.lemonlime.PlayerTracking.item.FindingCompassShardItem;
import tech.lemonlime.PlayerTracking.item.ModeledItem;

public class ModItems {






    public static final Item FINDING_COMPASS = new FindingCompassItem(new Item.Settings().maxCount(1).fireproof());

    public static final Item FINDING_COMPASS_SHARD = new FindingCompassShardItem(new Item.Settings().maxCount(1).fireproof());



    public static final Item TRACKING_CHARGER = new PolymerBlockItem(ModBlocks.TRACKING_CHARGER_BLOCK,new Item.Settings().fireproof(), Items.LODESTONE);

    public static final Item TRACKINIUM_ORE = new PolymerBlockItem(ModBlocks.TRACKINIUM_ORE,new Item.Settings().fireproof(),Items.STRUCTURE_BLOCK);

    public static final Item RAW_TRACKINIUM = new ModeledItem(new Item.Settings().fireproof(),Items.DIAMOND, PolymerResourcePackUtils.requestModel(Items.DIAMOND, new Identifier(PlayerTracking.MODID, "item/raw_trackinium")).value());

    public static final Item TRACKINIUM_INGOT = new ModeledItem(new Item.Settings().fireproof(),Items.DIAMOND, PolymerResourcePackUtils.requestModel(Items.DIAMOND, new Identifier(PlayerTracking.MODID, "item/trackinium_ingot")).value());


    public static final Item TRACKINIUM_ITEM = new PolymerBlockItem(ModBlocks.TRACKINIUM_BLOCK,new Item.Settings().fireproof(),Items.STRUCTURE_BLOCK);







    public static void register() {




        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"finding_compass"),FINDING_COMPASS);

        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"tracking_shard"),FINDING_COMPASS_SHARD);

        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"tracking_charger"),TRACKING_CHARGER);



        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"trackinium_ore"),TRACKINIUM_ORE);

        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"raw_trackinium"),RAW_TRACKINIUM);

        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"trackinium_ingot"),TRACKINIUM_INGOT);

        Registry.register(Registries.ITEM,new Identifier(PlayerTracking.MODID,"trackinium"),TRACKINIUM_ITEM);







    }




}
