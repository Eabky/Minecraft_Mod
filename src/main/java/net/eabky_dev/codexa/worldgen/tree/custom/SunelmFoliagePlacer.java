package net.eabky_dev.codexa.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.eabky_dev.codexa.worldgen.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SunelmFoliagePlacer extends FoliagePlacer
{
    public static final Codec<SunelmFoliagePlacer> CODEC = RecordCodecBuilder.create(sunelmFoliagePlacerInstance -> foliagePlacerParts(sunelmFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(sunelmFoliagePlacerInstance, SunelmFoliagePlacer::new));

    private final int height;

    public SunelmFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height)
    {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type()
    {
        return ModFoliagePlacers.SUNELM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader pLevel, FoliageSetter pBlockSetter, RandomSource pRandom, TreeConfiguration pConfig, int pMaxFreeTreeHeight, FoliageAttachment pAttachment, int pFoliageHeight, int pFoliageRadius, int pOffset) {

        boolean flag = pAttachment.doubleTrunk();
        BlockPos blockpos = pAttachment.pos().above(pOffset);
        int i = pFoliageRadius + pAttachment.radiusOffset() - 1;
        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i - 2, pFoliageHeight - 3, flag);
        this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i - 1, pFoliageHeight - 4, flag);

        for(int j = pFoliageHeight - 5; j >= 0; --j)
        {
            this.placeLeavesRow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i, j, flag);
        }

        this.placeLeavesRowWithHangingLeavesBelow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i, -1, flag, 0.4f, 0.3f);
        this.placeLeavesRowWithHangingLeavesBelow(pLevel, pBlockSetter, pRandom, pConfig, blockpos, i - 1, -2, flag, 0.4f, 0.3f);
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge)
    {
        if (pLocalY == -1 && (pLocalX == pRange || pLocalZ == pRange) && pRandom.nextFloat() < 0.3)
        {
            return true;
        }
        else
        {
            boolean flag = pLocalX == pRange && pLocalZ == pRange;
            boolean flag1 = pRange > 2;
            if (flag1)
            {
                return flag || pLocalX + pLocalZ > pRange * 2 - 2 && pRandom.nextFloat() < 0.4;
            }
            else
            {
                return flag && pRandom.nextFloat() < 0.4;
            }
        }

    }
}
