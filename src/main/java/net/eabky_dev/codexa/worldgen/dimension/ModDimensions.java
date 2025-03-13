package net.eabky_dev.codexa.worldgen.dimension;

import com.google.common.collect.ImmutableSet;
import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.worldgen.CustomNoiseSettings;
import net.eabky_dev.codexa.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.FixedBiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.flat.FlatLayerInfo;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.BuiltinStructureSets;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Collectors;

public class ModDimensions 
{
    public static final ResourceKey<LevelStem> MIDNIGHT_SEA_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea"));
    public static final ResourceKey<Level> MIDNIGHT_SEA_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea"));
    public static final ResourceKey<DimensionType> MIDNIGHT_SEA_DIM_TYPE = ResourceKey.create(Registries.DIMENSION_TYPE,
            ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "midnight_sea_type"));


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
                0, // minY
                256, // height
                256, // logicalHeight
                BlockTags.INFINIBURN_OVERWORLD, // infiniburn
                BuiltinDimensionTypes.OVERWORLD_EFFECTS, // effectsLocation
                0.08f, // ambientLight
                new DimensionType.MonsterSettings(false, false, ConstantInt.of(0), 0)));
    }

    public static void bootstrapStem(BootstapContext<LevelStem> context)
    {
        HolderGetter<Biome> biomeRegistry = context.lookup(Registries.BIOME);
        HolderGetter<DimensionType> dimTypes = context.lookup(Registries.DIMENSION_TYPE);
        HolderGetter<NoiseGeneratorSettings> noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);
        HolderGetter<StructureSet> holdergetter = context.lookup(Registries.STRUCTURE_SET);
        HolderGetter<PlacedFeature> holdergetter1 = context.lookup(Registries.PLACED_FEATURE);
        HolderSet.Direct<StructureSet> direct = HolderSet.direct(ImmutableSet.of(BuiltinStructureSets.VILLAGES).stream().map(holdergetter::getOrThrow).collect(Collectors.toList()));

        FlatLevelGeneratorSettings flatLevelGeneratorSettings = new FlatLevelGeneratorSettings(Optional.of(direct), biomeRegistry.getOrThrow(ModBiomes.MIDNIGHT_SEA_BIOME), FlatLevelGeneratorSettings.createLakesList(holdergetter1));
        FlatLayerInfo grassLayer = new FlatLayerInfo(1, CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get());
        FlatLayerInfo dirtLayer = new FlatLayerInfo(3, CodexaModBlocks.MIDNIGHT_DIRT.get());
        FlatLayerInfo stoneLayer = new FlatLayerInfo(60, CodexaModBlocks.MIDNIGHT_STONE.get());
        FlatLayerInfo bedrockLayer = new FlatLayerInfo(1, Blocks.BEDROCK);
        flatLevelGeneratorSettings.getLayersInfo().add(bedrockLayer);
        flatLevelGeneratorSettings.getLayersInfo().add(stoneLayer);
        flatLevelGeneratorSettings.getLayersInfo().add(dirtLayer);
        flatLevelGeneratorSettings.getLayersInfo().add(grassLayer);


        NoiseBasedChunkGenerator wrappedChunkGenerator = new NoiseBasedChunkGenerator(
                new FixedBiomeSource(biomeRegistry.getOrThrow(ModBiomes.MIDNIGHT_SEA_BIOME)),
                noiseGenSettings.getOrThrow(CustomNoiseSettings.MIDNIGHT_SEA_NOISE));


        LevelStem stem = new LevelStem(dimTypes.getOrThrow(ModDimensions.MIDNIGHT_SEA_DIM_TYPE), wrappedChunkGenerator);

        context.register(MIDNIGHT_SEA_KEY, stem);
    }
}
