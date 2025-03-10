package net.eabky_dev.codexa.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.eabky_dev.codexa.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.*;
import java.util.function.BiConsumer;

public class SunelmTrunkPlacer extends TrunkPlacer
{
    public static final Codec<SunelmTrunkPlacer> CODEC = RecordCodecBuilder.create(sunelmTrunkPlacerInstance ->
            trunkPlacerParts(sunelmTrunkPlacerInstance).apply(sunelmTrunkPlacerInstance, SunelmTrunkPlacer::new));

    public SunelmTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB)
    {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type()
    {
        return ModTrunkPlacerTypes.SUNELM_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource sRandom, int freeTreeHeight, BlockPos pos, TreeConfiguration config) {
        setDirtAt(level, blockSetter, sRandom, pos.below(), config);

        int height = 8 + sRandom.nextInt(3); // Height between 8 and 10

        //Thicken the Base
        List<Direction> possibleDirections = new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
        Random random = new Random(sRandom.nextLong());
        Collections.shuffle(possibleDirections, random);
        Direction dir1 = possibleDirections.get(0);
        Direction dir2 = possibleDirections.get(1);

        int extraLogs1 = 2 + sRandom.nextInt(3);
        int extraLogs2 = 2 + sRandom.nextInt(3);

        BlockPos base = pos.below();

        // First random direction
        BlockPos extendedPos1 = base.relative(dir1); // Move one block in direction
        for (int i = 0; i < extraLogs1; i++)
        {
            placeLog(level, blockSetter, sRandom, extendedPos1.above(i), config);
        }

        // Second random direction
        BlockPos extendedPos2 = base.relative(dir2); // Move one block in direction
        for (int i = 0; i < extraLogs2; i++)
        {
            placeLog(level, blockSetter, sRandom, extendedPos2.above(i), config);
        }

        //Build the Main Trunk
        for (int i = 0; i < height; i++) {
            placeLog(level, blockSetter, sRandom, pos.above(i), config);
        }

        //Generate Branches
        int branches = 4 + sRandom.nextInt(2);
        int lowerBranches = 0;

        if (height > 8)
        {
            lowerBranches = 1 + sRandom.nextInt(3);
        }

        // Upper branches
        for (int i = 0; i < branches; i++)
        {
            int branchHeight = height - sRandom.nextInt(4) - 2;
            BlockPos branchStart = pos.above(branchHeight);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(sRandom);
            generateBranch(level, blockSetter, sRandom, branchStart, direction, config, sRandom.nextInt(6));
        }

        // Lower branches
        for (int i = 0; i < lowerBranches; i++)
        {
            int branchHeight = 3 + sRandom.nextInt(height / 2);
            BlockPos branchStart = pos.above(branchHeight);
            Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(sRandom);
            generateBranch(level, blockSetter, sRandom, branchStart, direction, config, sRandom.nextInt(5));
        }

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(pos.above(height), 0, false));
    }

    private void generateBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos startPos, Direction direction, TreeConfiguration config, int length)
    {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(startPos.getX(), startPos.getY(), startPos.getZ());

        for (int i = 0; i < length; i++)
        {
            mutable.move(direction);

            if (random.nextFloat() < 0.5f)
            { // 50% chance to go up
                mutable.move(Direction.UP);
            }
            if (random.nextFloat() < 0.3f)
            { // 30% chance to place an extra log above
                placeLog(level, blockSetter, random, mutable.above(), config);
            }

            placeLog(level, blockSetter, random, mutable, config);
        }
    }
}
