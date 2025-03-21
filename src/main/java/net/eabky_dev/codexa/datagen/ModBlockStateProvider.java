package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CODEXA.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels()
    {
        blockWithItem(CodexaModBlocks.PLATINUM_BLOCK);
        blockWithItem(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE);
        blockWithItem(CodexaModBlocks.BLACK_HOLE);
        blockWithItem(CodexaModBlocks.CONCRETE);
        blockWithItem(CodexaModBlocks.POLISHED_CONCRETE);
        stairsBlock(((StairBlock) CodexaModBlocks.CONCRETE_STRAIRS.get()), blockTexture(CodexaModBlocks.CONCRETE.get()));
        stairsBlock(((StairBlock) CodexaModBlocks.POLISHED_CONCRETE_STRAIRS.get()), blockTexture(CodexaModBlocks.POLISHED_CONCRETE.get()));
        slabBlock(((SlabBlock) CodexaModBlocks.CONCRETE_SLAB.get()), blockTexture(CodexaModBlocks.CONCRETE.get()), blockTexture(CodexaModBlocks.CONCRETE.get()));
        slabBlock(((SlabBlock) CodexaModBlocks.POLISHED_CONCRETE_SLAB.get()), blockTexture(CodexaModBlocks.POLISHED_CONCRETE.get()), blockTexture(CodexaModBlocks.POLISHED_CONCRETE.get()));
        wallBlock(((WallBlock) CodexaModBlocks.CONCRETE_WALL.get()), blockTexture(CodexaModBlocks.CONCRETE.get()));
        blockWithItem(CodexaModBlocks.ALABASTER);
        blockWithItem(CodexaModBlocks.ALABASTER_BRICKS);
        blockItem(CodexaModBlocks.ALABASTER_PILLAR);
        blockItem(CodexaModBlocks.CARVED_ALABASTER);
        slabBlock(((SlabBlock) CodexaModBlocks.ALABASTER_SLAB.get()), blockTexture(CodexaModBlocks.ALABASTER.get()), blockTexture(CodexaModBlocks.ALABASTER.get()));
        slabBlock(((SlabBlock) CodexaModBlocks.ALABASTER_BRICK_SLAB.get()), blockTexture(CodexaModBlocks.ALABASTER_BRICKS.get()), blockTexture(CodexaModBlocks.ALABASTER_BRICKS.get()));
        wallBlock(((WallBlock) CodexaModBlocks.ALABASTER_WALL.get()), blockTexture(CodexaModBlocks.ALABASTER.get()));
        wallBlock(((WallBlock) CodexaModBlocks.ALABASTER_BRICK_WALL.get()), blockTexture(CodexaModBlocks.ALABASTER_BRICKS.get()));
        stairsBlock(((StairBlock) CodexaModBlocks.ALABASTER_STAIRS.get()), blockTexture(CodexaModBlocks.ALABASTER.get()));
        stairsBlock(((StairBlock) CodexaModBlocks.ALABASTER_BRICK_STAIRS.get()), blockTexture(CodexaModBlocks.ALABASTER_BRICKS.get()));
        axisBlock(((RotatedPillarBlock) CodexaModBlocks.ALABASTER_PILLAR.get()), blockTexture(CodexaModBlocks.ALABASTER_PILLAR.get()), ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/alabaster"));
        axisBlock(((RotatedPillarBlock) CodexaModBlocks.CARVED_ALABASTER.get()), blockTexture(CodexaModBlocks.CARVED_ALABASTER.get()), ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/alabaster_bricks"));
        simpleBlockWithItem(CodexaModBlocks.PALE_FORTUNE.get(), models().cross(blockTexture(CodexaModBlocks.PALE_FORTUNE.get()).getPath(), blockTexture(CodexaModBlocks.PALE_FORTUNE.get())).renderType("cutout"));
        simpleBlockWithItem(CodexaModBlocks.POTTED_PALE_FORTUNE.get(), models().singleTexture("potted_pale_fortune", ResourceLocation.parse("flower_pot_cross"), "plant", blockTexture(CodexaModBlocks.PALE_FORTUNE.get())).renderType("cutout"));
        logBlock(((RotatedPillarBlock) CodexaModBlocks.SUNELM_LOG.get()));
        axisBlock(((RotatedPillarBlock) CodexaModBlocks.SUNELM_WOOD.get()), blockTexture(CodexaModBlocks.SUNELM_LOG.get()), blockTexture(CodexaModBlocks.SUNELM_LOG.get()));
        axisBlock(((RotatedPillarBlock) CodexaModBlocks.STRIPPED_SUNELM_LOG.get()), blockTexture(CodexaModBlocks.STRIPPED_SUNELM_LOG.get()), ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/stripped_sunelm_log_top"));
        axisBlock(((RotatedPillarBlock) CodexaModBlocks.STRIPPED_SUNELM_WOOD.get()), blockTexture(CodexaModBlocks.STRIPPED_SUNELM_LOG.get()), blockTexture(CodexaModBlocks.STRIPPED_SUNELM_LOG.get()));
        blockItem(CodexaModBlocks.SUNELM_LOG);
        blockItem(CodexaModBlocks.SUNELM_WOOD);
        blockItem(CodexaModBlocks.STRIPPED_SUNELM_LOG);
        blockItem(CodexaModBlocks.STRIPPED_SUNELM_WOOD);
        blockWithItem(CodexaModBlocks.SUNELM_PLANKS);
        leavesBlock(CodexaModBlocks.SUNELM_LEAVES);
        saplingBlock(CodexaModBlocks.SUNELM_SAPLING);
        grassBlock(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK);
        blockWithItem(CodexaModBlocks.MIDNIGHT_DIRT);
        blockWithItem(CodexaModBlocks.MIDNIGHT_STONE);
        simpleBlockWithItem(CodexaModBlocks.MIDNIGHT_GRASS.get(), models().cross(blockTexture(CodexaModBlocks.MIDNIGHT_GRASS.get()).getPath(), blockTexture(CodexaModBlocks.MIDNIGHT_GRASS.get())).renderType("cutout"));
    }

    private void grassBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeBottomTop(
                        ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + "_side"),
                        ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + "_bottom"),
                        ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + "_top")
                )
        );
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject)
    {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(CODEXA.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

}
