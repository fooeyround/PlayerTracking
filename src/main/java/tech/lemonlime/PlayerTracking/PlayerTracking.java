package tech.lemonlime.PlayerTracking;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.lemonlime.PlayerTracking.gen.TrackiniumGen;
import tech.lemonlime.PlayerTracking.registry.ModBlocks;
import tech.lemonlime.PlayerTracking.registry.ModItems;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class PlayerTracking implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("playertracking");

	public static final String MODID = "playertracking";








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


		ModItems.register();
		ModBlocks.register();


		BiomeModifications.create(new Identifier(MODID, "features"))
				.add(ModificationPhase.ADDITIONS,
						BiomeSelectors.foundInTheEnd(),
						TrackiniumGen.oreModifier());







		LOGGER.info("PlayerTracking Loaded!");




	}


}