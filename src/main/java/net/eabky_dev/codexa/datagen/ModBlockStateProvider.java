package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.ModBlocks;
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

        logBlock(((RotatedPillarBlock) ModBlocks.SUNELM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SUNELM_WOOD.get()), blockTexture(ModBlocks.SUNELM_LOG.get()), blockTexture(ModBlocks.SUNELM_LOG.get()));

        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SUNELM_LOG.get()), blockTexture(ModBlocks.STRIPPED_SUNELM_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "block/stripped_sunelm_log_top"));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SUNELM_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SUNELM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_SUNELM_LOG.get()));

        blockItem(ModBlocks.SUNELM_LOG);
        blockItem(ModBlocks.SUNELM_WOOD);
        blockItem(ModBlocks.STRIPPED_SUNELM_LOG);
        blockItem(ModBlocks.STRIPPED_SUNELM_WOOD);
        blockWithItem(ModBlocks.SUNELM_PLANKS);
        leavesBlock(ModBlocks.SUNELM_LEAVES);
        saplingBlock(ModBlocks.SUNELM_SAPLING);

        grassBlock(ModBlocks.MIDNIGHT_GRASS_BLOCK);
        blockWithItem(ModBlocks.MIDNIGHT_DIRT);
        blockWithItem(ModBlocks.MIDNIGHT_STONE);
        simpleBlockWithItem(ModBlocks.MIDNIGHT_GRASS.get(), models().cross(blockTexture(ModBlocks.MIDNIGHT_GRASS.get()).getPath(),
                blockTexture(ModBlocks.MIDNIGHT_GRASS.get())).renderType("cutout"));
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
