package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModCreativeTabs
{
    public static final DeferredRegister<CreativeModeTab>  REGISTRY;
    public static final RegistryObject<CreativeModeTab> CODEXA_BLOCKS_TAB;
    public static final RegistryObject<CreativeModeTab> CODEXA_MATERIALS_TAB;
    public static final RegistryObject<CreativeModeTab> CODEXA_FOOD_TAB;
    public static final RegistryObject<CreativeModeTab> CODEXA_EQUIPMENT_TAB;
    public static final RegistryObject<CreativeModeTab> CODEXA_CREATURES_TAB;
    public static final RegistryObject<CreativeModeTab> CODEXA_MISC_TAB;

    static
    {
        REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CODEXA.MOD_ID);

        CODEXA_BLOCKS_TAB = REGISTRY.register("codexa_blocks_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get()))
                .title(Component.translatable("creativetab.codexa_blocks_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get());
                    pOutput.accept(CodexaModBlocks.MIDNIGHT_DIRT.get());
                    pOutput.accept(CodexaModBlocks.MIDNIGHT_STONE.get());
                    pOutput.accept(CodexaModBlocks.PLATINUM_BLOCK.get());
                    pOutput.accept(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get());
                    pOutput.accept(CodexaModBlocks.CONCRETE.get());
                    pOutput.accept(CodexaModBlocks.CONCRETE_STRAIRS.get());
                    pOutput.accept(CodexaModBlocks.CONCRETE_SLAB.get());
                    pOutput.accept(CodexaModBlocks.CONCRETE_WALL.get());
                    pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE.get());
                    pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE_STRAIRS.get());
                    pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE_SLAB.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_BRICKS.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_PILLAR.get());
                    pOutput.accept(CodexaModBlocks.CARVED_ALABASTER.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_STAIRS.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_SLAB.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_WALL.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_BRICK_STAIRS.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_BRICK_SLAB.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER_BRICK_WALL.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER.get());
                    pOutput.accept(CodexaModBlocks.ALABASTER.get());
                    pOutput.accept(CodexaModBlocks.BLACK_HOLE.get());
                    pOutput.accept(CodexaModBlocks.SUNELM_LOG.get());
                    pOutput.accept(CodexaModBlocks.SUNELM_WOOD.get());
                    pOutput.accept(CodexaModBlocks.STRIPPED_SUNELM_LOG.get());
                    pOutput.accept(CodexaModBlocks.STRIPPED_SUNELM_WOOD.get());
                    pOutput.accept(CodexaModBlocks.SUNELM_PLANKS.get());
                    pOutput.accept(CodexaModBlocks.SUNELM_LEAVES.get());
                    pOutput.accept(CodexaModBlocks.SUNELM_SAPLING.get());
                    pOutput.accept(CodexaModBlocks.MIDNIGHT_GRASS.get());
                    pOutput.accept(CodexaModBlocks.PALE_FORTUNE.get());
                }).build());

        CODEXA_MATERIALS_TAB = REGISTRY.register("codexa_materials_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.PLATINUM_INGOT.get()))
                .title(Component.translatable("creativetab.codexa_materials_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModItems.PLATINUM_INGOT.get());
                    pOutput.accept(CodexaModItems.RAW_PLATINUM.get());
                    pOutput.accept(CodexaModItems.PLATINUM_RESIDUE.get());
                    pOutput.accept(CodexaModItems.PLATINUM_NUGGET.get());
                    pOutput.accept(CodexaModItems.PALADINIUM_INGOT.get());
                    pOutput.accept(CodexaModItems.BAMBOO_CHARCOAL.get());

                }).build());

        CODEXA_FOOD_TAB = REGISTRY.register("codexa_food_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.WISDOM_FRUIT.get()))
                .title(Component.translatable("creativetab.codexa_food_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModItems.WISDOM_FRUIT.get());

                }).build());

        CODEXA_EQUIPMENT_TAB = REGISTRY.register("codexa_equipment_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.DARK_STAR_RING.get()))
                .title(Component.translatable("creativetab.codexa_equipment_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModItems.PLATINUM_PICKAXE.get());
                    pOutput.accept(CodexaModItems.PLATINUM_HELMET.get());
                    pOutput.accept(CodexaModItems.PLATINUM_CHESTPLATE.get());
                    pOutput.accept(CodexaModItems.PLATINUM_LEGGINGS.get());
                    pOutput.accept(CodexaModItems.PLATINUM_BOOTS.get());
                    pOutput.accept(CodexaModItems.DARK_STAR_RING.get());
                    pOutput.accept(CodexaModItems.RING_OF_DEFIANCE.get());
                    pOutput.accept(CodexaModItems.MANTLE_OF_THE_UNIVERSE.get());
                    pOutput.accept(CodexaModItems.IRON_LONGSWORD.get());
                    pOutput.accept(CodexaModItems.WOODEN_GREATSWORD.get());
                    pOutput.accept(CodexaModItems.TOMAHAWK.get());
                    pOutput.accept(CodexaModItems.STAR_FORGER.get());

                }).build());

        CODEXA_CREATURES_TAB = REGISTRY.register("codexa_creatures_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.GEM_GOLEM_SPAWN_EGG.get()))
                .title(Component.translatable("creativetab.codexa_creatures_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModItems.GEM_GOLEM_SPAWN_EGG.get());
                    pOutput.accept(CodexaModItems.GEMITE_SPAWN_EGG.get());

                }).build());

        CODEXA_MISC_TAB = REGISTRY.register("codexa_miscellaneous_tab", ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.OTHERWORLDLY_LANTERN_ITEM.get()))
                .title(Component.translatable("creativetab.codexa_miscellaneous_tab"))
                .displayItems((pParameters, pOutput) ->
                {
                    pOutput.accept(CodexaModItems.OTHERWORLDLY_LANTERN_ITEM.get());

                }).build());
    }
}
