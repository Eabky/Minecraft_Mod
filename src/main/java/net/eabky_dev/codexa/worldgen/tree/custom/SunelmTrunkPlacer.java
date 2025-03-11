package net.eabky_dev.codexa.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.eabky_dev.codexa.block.ModBlocks;
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

        int treeHeight = 8 + sRandom.nextInt(5); // Height between 8 and 12

        //Thicken the Base
        List<Direction> possibleDirections = new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));
        List<FoliagePlacer.FoliageAttachment> foliageAttachments = new ArrayList<>();

        Random random = new Random(sRandom.nextLong());
        Collections.shuffle(possibleDirections, random);

        Direction dir1 = possibleDirections.get(0);
        Direction dir2 = possibleDirections.get(1);
        Direction dir3 = possibleDirections.get(2);

        int extraLogs1 = 1 + sRandom.nextInt(3);
        int extraLogs2 = 2 + sRandom.nextInt(2);
        int extraLogs3 = 3 + sRandom.nextInt(2);

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

        // Thirds random direction
        BlockPos extendedPos3 = base.relative(dir3); // Move one block in direction
        for (int i = 0; i < extraLogs3; i++)
        {
            placeLog(level, blockSetter, sRandom, extendedPos3.above(i), config);
        }

        //Build the Main Trunk
        for (int i = 0; i < treeHeight; i++)
        {
            placeLog(level, blockSetter, sRandom, pos.above(i), config);
        }

        //Generate Branches
        for (Direction direction : possibleDirections)
        {
            int branchHeight = 3 + random.nextInt(3);

            if (!isBranchSpaceFree(level, pos.above(branchHeight), direction))
            {
                continue; // Skip if space is occupied
            }

            BlockPos branchStart = pos.above(branchHeight);
            generateBranch(level, blockSetter, sRandom, branchStart, direction, config, 2 + sRandom.nextInt(4), foliageAttachments);
        }

        // Return the foliage attachment for the trunk and branches
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(pos.above(treeHeight), 0, false)); // Trunk top foliage
        return foliageAttachments;
    }

    private void generateBranch(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> blockSetter, RandomSource random, BlockPos startPos, Direction direction, TreeConfiguration config, int length, List<FoliagePlacer.FoliageAttachment> foliageAttachments)
    {
        BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos(startPos.getX(), startPos.getY(), startPos.getZ());

        // Generate the branch logs
        for (int i = 0; i < length; i++)
        {
            mutable.move(direction);

            if (random.nextFloat() < 0.8f)
            { // 80% chance to go up
                mutable.move(Direction.UP);
            }
            if (random.nextFloat() < 0.55f)
            { // 55% chance to place an extra log above
                placeLog(level, blockSetter, random, mutable.above(), config);
            }
            // 35% chance to move left or right (diagonal growth)
            if (random.nextFloat() < 0.35f)
            {
                Direction leftRight = (direction.getAxis() == Direction.Axis.X) ? Direction.NORTH : Direction.WEST;
                if (random.nextBoolean())
                {
                    leftRight = leftRight.getOpposite(); // Randomly choose left or right
                }
                mutable.move(leftRight);
            }

            placeLog(level, blockSetter, random, mutable, config);


            if (i > 1 && i < length - 1 && random.nextFloat() < 0.1f)
            {   // 20% chance to split midway
                Direction newDirection = getRandomSplitDirection(direction, random);
                generateBranch(level, blockSetter, random, mutable.immutable(), newDirection, config, length - i, foliageAttachments);
            }
        }

        // After placing logs, add foliage at the end of the branch
        foliageAttachments.add(new FoliagePlacer.FoliageAttachment(mutable, 0, false)); // Foliage at branch end
    }

    private Direction getRandomSplitDirection(Direction currentDirection, RandomSource random)
    {
        List<Direction> possibleDirections = new ArrayList<>(Arrays.asList(Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST));

        // Remove the current direction to avoid extending too straight
        possibleDirections.remove(currentDirection);
        if (currentDirection.getAxis() != Direction.Axis.Y)
        {
            possibleDirections.remove(currentDirection.getOpposite()); // Avoid direct reversal
        }

        return possibleDirections.get(random.nextInt(possibleDirections.size()));
    }

    private boolean isBranchSpaceFree(LevelSimulatedReader level, BlockPos startPos, Direction direction)
    {
        // Move horizontally in the direction of the branch
        BlockPos horizontalPos = startPos.relative(direction);

        // Check up to 2 blocks above the horizontal position
        for (int i = 1; i <= 2; i++)
        {
            BlockPos checkPos = horizontalPos.above(i); // Get the position above
            if (level.isStateAtPosition(checkPos, state -> state.is(ModBlocks.SUNELM_LOG.get())))
            {
                return false; // If a log block is found, space is not free
            }
        }
        return true; // No log blocks found, space is free
    }

}
