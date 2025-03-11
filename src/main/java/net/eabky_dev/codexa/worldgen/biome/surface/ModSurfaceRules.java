package net.eabky_dev.codexa.worldgen.biome.surface;

import net.eabky_dev.codexa.block.ModBlocks;
import net.eabky_dev.codexa.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModSurfaceRules
{
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(ModBlocks.MIDNIGHT_DIRT.get());
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(ModBlocks.MIDNIGHT_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(ModBlocks.MIDNIGHT_STONE.get());

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MIDNIGHT_SEA_BIOME),
                        SurfaceRules.sequence(
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, GRASS_BLOCK),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, DIRT),
                                SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, STONE),
                                SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE),
                                SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, STONE),
                                SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, STONE),
                                SurfaceRules.ifTrue(SurfaceRules.steep(), STONE)
                        )
                ),

                // Default fallback to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}