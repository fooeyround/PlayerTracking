package tech.lemonlime.PlayerTracking;

import eu.pb4.polymer.blocks.api.BlockModelType;
import eu.pb4.polymer.blocks.api.PolymerBlockModel;
import eu.pb4.polymer.blocks.api.PolymerBlockResourceUtils;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.lemonlime.PlayerTracking.registry.ModBlocks;
import tech.lemonlime.PlayerTracking.registry.ModItems;
import tech.lemonlime.PlayerTracking.registry.ModRecipes;

public class PlayerTracking implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("playertracking");

	public static final String MODID = "playertracking";


	//DO NOT REMOVE
	public static final RegistryKey<ConfiguredFeature<?,?>> ORE_TRACKINIUM_CF = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(MODID, "ore_trackinium"));
	public static final RegistryKey<PlacedFeature> ORE_TRACKINIUM_PF = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(PlayerTracking.MODID, "ore_trackinium"));



	public static PlayerEntity getPlayerFromString(World world, String string) {

		for (PlayerEntity player : world.getPlayers()) {
			if (player.getName().getString().equalsIgnoreCase(string)) {
				return player;
			}
		}

		return null;
	}



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		ModRecipes.register();

		ModItems.register();
		ModBlocks.register();


		BiomeModifications.create(new Identifier(MODID, "features"))
				.add(ModificationPhase.ADDITIONS,
						BiomeSelectors.foundInTheEnd(),
						(biomeSelectionContext, biomeModificationContext) ->
								// here we can potentially narrow our biomes down
								// but here we won't
								biomeModificationContext.getGenerationSettings().addFeature(
										// ores to ores
										GenerationStep.Feature.UNDERGROUND_ORES,
										// this is the key of the placed feature
										ORE_TRACKINIUM_PF));




		PolymerResourcePackUtils.addModAssets(MODID);

		PolymerResourcePackUtils.markAsRequired();



//
//		PolymerModelData trackinium_ore_modelData = PolymerResourcePackUtils.requestModel(Items.STRUCTURE_BLOCK, new Identifier(MODID, "item/trackinium_ore"));
//
//		PolymerModelData trackinium_ingot_modelData = PolymerResourcePackUtils.requestModel(Items.DIAMOND, new Identifier(MODID, "item/trackinium_ingot"));
//
//
//		PolymerModelData raw_trackinium_modelData = PolymerResourcePackUtils.requestModel(Items.DIAMOND, new Identifier(MODID, "item/raw_trackinium"));
//
//





		LOGGER.info("PlayerTracking Loaded!");




	}


}