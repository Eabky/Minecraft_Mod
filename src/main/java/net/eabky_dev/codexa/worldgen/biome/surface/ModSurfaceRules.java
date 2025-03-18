
package net.eabky_dev.codexa.worldgen.biome.surface;

import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.worldgen.biome.ModBiomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class ModSurfaceRules
{
    private static final SurfaceRules.RuleSource MIDNIGHT_DIRT = makeStateRule(CodexaModBlocks.MIDNIGHT_DIRT.get());
    private static final SurfaceRules.RuleSource MIDNIGHT_GRASS_BLOCK = makeStateRule(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get());
    private static final SurfaceRules.RuleSource STONE = makeStateRule(CodexaModBlocks.MIDNIGHT_STONE.get());
    private static final SurfaceRules.RuleSource BEDROCK = makeStateRule(Blocks.BEDROCK);

    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.RuleSource defaultOverWorld = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        SurfaceRules.RuleSource bedrock_floor = SurfaceRules.ifTrue(SurfaceRules.verticalGradient("minecraft:bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK);

        SurfaceRules.RuleSource desolateSurface = SurfaceRules.ifTrue(
                SurfaceRules.abovePreliminarySurface(),
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.ifTrue(isAtOrAboveWaterLevel, MIDNIGHT_GRASS_BLOCK)),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_FLOOR, MIDNIGHT_DIRT),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, STONE),
                        SurfaceRules.ifTrue(SurfaceRules.UNDER_CEILING, STONE),
                        SurfaceRules.ifTrue(SurfaceRules.VERY_DEEP_UNDER_FLOOR, STONE))
        );

        SurfaceRules.RuleSource depthsSurface =
                SurfaceRules.sequence(
                        SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, BEDROCK));


        SurfaceRules.RuleSource midnightSurface = SurfaceRules.sequence(
                bedrock_floor,
                desolateSurface,
                depthsSurface
        );

        SurfaceRules.RuleSource midnightGeneration = SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.DESOLATE_WASTES_BIOME), depthsSurface),
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.MIDNIGHT_DEPTHS_BIOME), depthsSurface)
        );

        return SurfaceRules.sequence(
                midnightGeneration,
                defaultOverWorld
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
