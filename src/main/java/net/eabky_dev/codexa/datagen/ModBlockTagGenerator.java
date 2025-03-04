package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.ModBlocks;
import net.eabky_dev.codexa.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CODEXA.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        ModBlocks.CONCRETE.get(),
                        ModBlocks.CONCRETE_SLAB.get(),
                        ModBlocks.CONCRETE_STRAIRS.get(),
                        ModBlocks.POLISHED_CONCRETE_SLAB.get(),
                        ModBlocks.POLISHED_CONCRETE_STRAIRS.get());


        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PLATINUM_BLOCK.get(),
                        ModBlocks.DEEPSLATE_PLATINUM_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(ModTags.Blocks.NEEDS_PLATINUM_TOOL);

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.CONCRETE_WALL.get());
    }
}
