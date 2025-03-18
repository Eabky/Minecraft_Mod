package net.eabky_dev.codexa.worldgen.dimension;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.OptionalLong;

public class ModDimensions
{
    public static final ResourceKey<LevelStem> MIDNIGHT_SEA_KEY = ResourceKey.create(Registries.LEVEL_STEM, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea"));
    public static final ResourceKey<Level> MIDNIGHT_SEA_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea"));
    public static final ResourceKey<DimensionType> MIDNIGHT_SEA_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea_type"));

    public static final ResourceKey<NoiseGeneratorSettings> MIDNIGHT_SEA_NOISE = createNoiseKey("midnight_sea_noise");

    private static ResourceKey<NoiseGeneratorSettings> createNoiseKey(String name)
    {
        return ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
    }

    public static void bootstrapType(BootstapContext<DimensionType> context)
    {
        context.register(MIDNIGHT_SEA_DIM_TYPE, new DimensionType(
                OptionalLong.of(12000), // fixedTime
                false, // hasSkylight
                false, // hasCeiling
                false, // ultraWarm
                false, // natural
                1.0, // coordinateScale
                false, // bedWorks
                false, // respawnAnchorWorks
                -64, // minY
                384, // height
                384, // logicalHeight
                BlockTags.INFINIBURN_END, // infiniburn
                BuiltinDimensionTypes.NETHER_EFFECTS, // effectsLocation
                1f, // ambientLight //will set to 0 later but now I need to see shit
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context)
    {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<StructureSet> holdergetter = context.lookup(Registries.STRUCTURE_SET);
//        HolderGetter<PlacedFeature> holdergetter1 = context.lookup(Registries.PLACED_FEATURE);
//        HolderSet.Direct<StructureSet> direct = HolderSet.direct(ImmutableSet.of(BuiltinStructureSets.VILLAGES).stream().map(holdergetter::getOrThrow).collect(Collectors.toList()));


        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(1F, 0.0F, -0.18F, 1F, 0.0F, -0.10F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.DESOLATE_WASTES_BIOME)),
                                Pair.of(Climate.parameters(1F, 1F, 0.7F, 0.2F, 0.2F, 0.9F, 0.0F),
                                        biomeRegistry.getOrThrow(ModBiomes.MIDNIGHT_DEPTHS_BIOME))))),
                noiseGenSettings.getOrThrow(MIDNIGHT_SEA_NOISE));


        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.MIDNIGHT_SEA_DIM_TYPE), wrappedChunkGenerator);

        context.register(MIDNIGHT_SEA_KEY, stem);
    }
}
