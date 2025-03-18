package net.eabky_dev.codexa.worldgen.carvers;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;

public class MidnightSeaCarver
{
    public static final ResourceKey<ConfiguredWorldCarver<?>> MIDNIGHT_CAVE = createKey("midnight_cave");

    private static ResourceKey<ConfiguredWorldCarver<?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_CARVER, ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
    }

    public static void bootstrap(BootstapContext<ConfiguredWorldCarver<?>> ctx)
    {
        ctx.register(MIDNIGHT_CAVE, WorldCarver.CAVE.configured(getCaveCarver(ctx)));

    }

    public static CaveCarverConfiguration getCaveCarver(BootstapContext<ConfiguredWorldCarver<?>> ctx)
    {
        HolderGetter<Block> holdergetter = ctx.lookup(Registries.BLOCK);
        return new CaveCarverConfiguration(
                0.92F, //cave probability
                UniformHeight.of(VerticalAnchor.aboveBottom(5), VerticalAnchor.absolute(80)), // min & max of height
                UniformFloat.of(1F, 5F), // tunnel size
                VerticalAnchor.absolute(-90), //lava level
                holdergetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                UniformFloat.of(4F, 16F), //horizontal radius multiplier
                UniformFloat.of(8F, 18F), //vertical radius multiplier
                UniformFloat.of(-1F, -0.5F)); //floor level
    }
}