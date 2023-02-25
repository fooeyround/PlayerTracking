package tech.lemonlime.PlayerTracking.gen;


import net.fabricmc.fabric.api.biome.v1.BiomeModificationContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import tech.lemonlime.PlayerTracking.PlayerTracking;

import java.util.function.BiConsumer;

public class TrackiniumGen {





    public static final RegistryKey<PlacedFeature> ORE_TRACKINIUM = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(PlayerTracking.MODID, "ore_trackinium"));





    public static BiConsumer<BiomeSelectionContext, BiomeModificationContext> oreModifier() {
        return (biomeSelectionContext, biomeModificationContext) ->
                // here we can potentially narrow our biomes down
                // but here we won't
                biomeModificationContext.getGenerationSettings().addFeature(
                        // ores to ores
                        GenerationStep.Feature.UNDERGROUND_ORES,
                        // this is the key of the placed feature
                        ORE_TRACKINIUM);


    }







}