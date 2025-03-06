package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CODEXA.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PLATINUM_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockWithItem(ModBlocks.BLACK_HOLE);
        blockWithItem(ModBlocks.CONCRETE);
        blockWithItem(ModBlocks.POLISHED_CONCRETE);

        stairsBlock(((StairBlock) ModBlocks.CONCRETE_STRAIRS.get()), blockTexture(ModBlocks.CONCRETE.get()));
        stairsBlock(((StairBlock) ModBlocks.POLISHED_CONCRETE_STRAIRS.get()), blockTexture(ModBlocks.POLISHED_CONCRETE.get()));

        slabBlock(((SlabBlock) ModBlocks.CONCRETE_SLAB.get()), blockTexture(ModBlocks.CONCRETE.get()), blockTexture(ModBlocks.CONCRETE.get()));
        slabBlock(((SlabBlock) ModBlocks.POLISHED_CONCRETE_SLAB.get()), blockTexture(ModBlocks.POLISHED_CONCRETE.get()), blockTexture(ModBlocks.POLISHED_CONCRETE.get()));

        wallBlock(((WallBlock) ModBlocks.CONCRETE_WALL.get()), blockTexture(ModBlocks.CONCRETE.get()));

        simpleBlockWithItem(ModBlocks.PALE_FORTUNE.get(), models().cross(blockTexture(ModBlocks.PALE_FORTUNE.get()).getPath(),
                blockTexture(ModBlocks.PALE_FORTUNE.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_PALE_FORTUNE.get(), models().singleTexture("potted_pale_fortune", ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.PALE_FORTUNE.get())).renderType("cutout"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
