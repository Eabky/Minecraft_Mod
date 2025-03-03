package net.eabky_dev.codexa.util;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags
{
    public static class Blocks {

        private static TagKey<Block> createTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
        }
    }

    public static class Items {
        //public static final TagKey<Item> TAG = createTag("nameoftag");  //To add a tag to an item I have to make a nameoftag json file at resources/data/codexa/tags/item/nameoftag.json and add the items there

        private static TagKey<Item> createTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, name));
        }
    }
}
