package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider 
{
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(p_275343_, p_275729_, p_275322_, CODEXA.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider)
    {
        this.tag(TagKey.create(Registries.ITEM, ResourceLocation.parse("curios:ring")))
                .add(CodexaModItems.DARK_STAR_RING.get());

        this.tag(ItemTags.TRIMMABLE_ARMOR).add(CodexaModItems.PLATINUM_HELMET.get(),
                CodexaModItems.PLATINUM_CHESTPLATE.get(),
                CodexaModItems.PLATINUM_LEGGINGS.get(),
                CodexaModItems.PLATINUM_BOOTS.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(CodexaModBlocks.SUNELM_LOG.get().asItem())
                .add(CodexaModBlocks.SUNELM_WOOD.get().asItem())
                .add(CodexaModBlocks.STRIPPED_SUNELM_LOG.get().asItem())
                .add(CodexaModBlocks.STRIPPED_SUNELM_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(CodexaModBlocks.SUNELM_PLANKS.get().asItem());
    }
}
