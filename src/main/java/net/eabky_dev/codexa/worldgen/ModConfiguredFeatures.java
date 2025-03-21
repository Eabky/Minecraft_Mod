package net.eabky_dev.codexa.worldgen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.worldgen.tree.custom.SunelmFoliagePlacer;
import net.eabky_dev.codexa.worldgen.tree.custom.SunelmTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_PLATINUM_ORE_KEY = registerKey("platinum_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNELM_KEY = registerKey("sunelm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MIDNIGHT_GRASS_KEY = registerKey("midnight_grass");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        List<OreConfiguration.TargetBlockState> overworldPlatinumOres = List.of(OreConfiguration.target(deepslateReplaceables, CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get().defaultBlockState()));

        register(context, OVERWORLD_PLATINUM_ORE_KEY, Feature.ORE, new OreConfiguration(overworldPlatinumOres, 3));

        register(context, SUNELM_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(CodexaModBlocks.SUNELM_LOG.get()),
                new SunelmTrunkPlacer(5, 4, 3),

                BlockStateProvider.simple(CodexaModBlocks.SUNELM_LEAVES.get()),
                new SunelmFoliagePlacer(ConstantInt.of(4), ConstantInt.of(2), 4),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(context, MIDNIGHT_GRASS_KEY, Feature.RANDOM_PATCH, grassPatch(BlockStateProvider.simple(CodexaModBlocks.MIDNIGHT_GRASS.get()), 15));
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int p_195204_)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(p_195204_, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }


    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration)
    {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
