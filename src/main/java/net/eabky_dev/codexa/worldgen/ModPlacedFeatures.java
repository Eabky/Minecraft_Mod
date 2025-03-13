package net.eabky_dev.codexa.worldgen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures
{
    public static final ResourceKey<PlacedFeature> PLATINUME_ORE_PLACED_KEY = registerKey("platinum_ore_placed");
    public static final ResourceKey<PlacedFeature> SUNELM_PLACED_KEY = registerKey("sunelm_placed");
    public static final ResourceKey<PlacedFeature> MIDNIGHT_GRASS_PLACED_KEY = registerKey("midnight_grass_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, PLATINUME_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_PLATINUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

        register(context, SUNELM_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SUNELM_KEY),
                VegetationPlacements.treePlacement(controlledTreePlacement(),
                        CodexaModBlocks.SUNELM_SAPLING.get()));

        register(context, MIDNIGHT_GRASS_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MIDNIGHT_GRASS_KEY),
                List.of(RarityFilter.onAverageOnceEvery(32), // Controls density of patches
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()));
    }

    public static PlacementModifier controlledTreePlacement()
    {
        return CountPlacement.of(new WeightedListInt(
                SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(0), 4)  // No tree in 4/5 chunks
                        .add(ConstantInt.of(1), 1)  // Tree in 1/5 chunks
                        .build()
        ));
    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers)
    {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
