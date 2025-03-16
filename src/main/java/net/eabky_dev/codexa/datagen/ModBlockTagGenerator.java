package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
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
                .add(CodexaModBlocks.PLATINUM_BLOCK.get(),
                        CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                        CodexaModBlocks.CONCRETE.get(),
                        CodexaModBlocks.CONCRETE_SLAB.get(),
                        CodexaModBlocks.CONCRETE_STRAIRS.get(),
                        CodexaModBlocks.POLISHED_CONCRETE_SLAB.get(),
                        CodexaModBlocks.POLISHED_CONCRETE_STRAIRS.get(),
                        CodexaModBlocks.OTHERWORLDLY_LANTERN.get());



        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(CodexaModBlocks.PLATINUM_BLOCK.get(),
                        CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get());

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(ModTags.Blocks.NEEDS_PLATINUM_TOOL);

        this.tag(BlockTags.WALLS)
                .add(CodexaModBlocks.CONCRETE_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(CodexaModBlocks.SUNELM_LOG.get())
                .add(CodexaModBlocks.SUNELM_WOOD.get())
                .add(CodexaModBlocks.STRIPPED_SUNELM_LOG.get())
                .add(CodexaModBlocks.STRIPPED_SUNELM_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(CodexaModBlocks.SUNELM_PLANKS.get());
    }
}
