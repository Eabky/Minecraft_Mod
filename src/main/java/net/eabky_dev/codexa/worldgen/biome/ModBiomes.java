package net.eabky_dev.codexa.worldgen.biome;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModParticles;
import net.eabky_dev.codexa.worldgen.ModPlacedFeatures;
import net.eabky_dev.codexa.worldgen.carvers.MidnightSeaCarver;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class ModBiomes
{
    private static final int FOG_COLOR = 2696491;
    private static final int WATER_COLOR = 2696491;
    private static final int WATER_FOG =0;

    public static final ResourceKey<Biome> MIDNIGHT_SEA_BIOME = ResourceKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea_biome"));

    public static void boostrap(BootstapContext<Biome> context)
    {
        context.register(MIDNIGHT_SEA_BIOME, midnightBiome(context));
    }

    public static Biome midnightBiome(BootstapContext<Biome> context)
    {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        //spawnBuilder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(ModEntities.RHINO.get(), 2, 3, 5));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        globalMidnightGeneration(biomeBuilder);
        defaultMidnightCaves(biomeBuilder);
        defaultMidnightOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.MIDNIGHT_GRASS_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .downfall(0.8f)
                .temperature(0.7f)
                .generationSettings(biomeBuilder.build())
                .mobSpawnSettings(spawnBuilder.build())
                .specialEffects(commonBiomeSpecialEffects()
                        .backgroundMusic(Musics.END)
                        .build()).build();
    }

    public static void globalMidnightGeneration(BiomeGenerationSettings.Builder builder)
    {
        BiomeDefaultFeatures.addAncientDebris(builder);
    }

    public static void defaultMidnightCaves(BiomeGenerationSettings.Builder builder) {
        builder.addCarver(GenerationStep.Carving.AIR, MidnightSeaCarver.MIDNIGHT_SEA_CAVE);
    }

    public static void defaultMidnightOres(BiomeGenerationSettings.Builder builder)
    {

    }

    public static BiomeSpecialEffects.Builder commonBiomeSpecialEffects()
    {
        return new BiomeSpecialEffects.Builder()
                .waterColor(WATER_COLOR)
                .waterFogColor(WATER_FOG)
                .skyColor(FOG_COLOR)
                .fogColor(FOG_COLOR)
                .ambientParticle(new AmbientParticleSettings((ParticleOptions) CodexaModParticles.DARK_STAR_RING_PARTICLE.get(), 0.0025F)) //change later to the ambient particles
                .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS);
    }


}
