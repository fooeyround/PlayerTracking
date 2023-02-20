package tech.lemonlime.PlayerTracking;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CompassItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.lemonlime.PlayerTracking.item.FindingCompassItem;
import tech.lemonlime.PlayerTracking.registry.ModBlocks;
import tech.lemonlime.PlayerTracking.registry.ModItems;

public class PlayerTracking implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("player-tracking");

	public static final String MODID = "playertracking";

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


		ModItems.register();
		ModBlocks.register();

//		if (FabricLoader.getInstance().getEnvironmentType().equals(EnvType.CLIENT)) {
//			ModelPredicateProviderRegistry.register(ModItems.FINDING_COMPASS, new Identifier("angle"), new CompassAnglePredicateProvider((world, stack, entity) ->
//					CompassItem.hasLodestone(stack) ? FindingCompassItem.createLodestonePos(stack.getOrCreateNbt()) : FindingCompassItem.createSpawnPos(world)));
//		}


		LOGGER.info("PlayerTracking Loaded!");




	}


}