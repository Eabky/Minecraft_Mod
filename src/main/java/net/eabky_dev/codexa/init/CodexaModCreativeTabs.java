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
    public static final DeferredRegister<CreativeModeTab>  CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CODEXA.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CODEXA_TAB = CREATIVE_MODE_TABS.register("codexa_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(CodexaModItems.PLATINUM_INGOT.get()))
                    .title(Component.translatable("creativetab.codexa_tab"))
                    .displayItems((pParameters, pOutput) ->
                    {
                        pOutput.accept(CodexaModItems.PLATINUM_INGOT.get());
                        pOutput.accept(CodexaModItems.RAW_PLATINUM.get());
                        pOutput.accept(CodexaModItems.PLATINUM_RESIDUE.get());
                        pOutput.accept(CodexaModItems.PLATINUM_NUGGET.get());
                        pOutput.accept(CodexaModItems.PLATINUM_PICKAXE.get());
                        pOutput.accept(CodexaModItems.PLATINUM_HELMET.get());
                        pOutput.accept(CodexaModItems.PLATINUM_CHESTPLATE.get());
                        pOutput.accept(CodexaModItems.PLATINUM_LEGGINGS.get());
                        pOutput.accept(CodexaModItems.PLATINUM_BOOTS.get());

                        pOutput.accept(CodexaModItems.PALADINIUM_INGOT.get());

                        pOutput.accept(CodexaModBlocks.PLATINUM_BLOCK.get());
                        pOutput.accept(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get());
                        pOutput.accept(CodexaModBlocks.CONCRETE.get());
                        pOutput.accept(CodexaModBlocks.CONCRETE_STRAIRS.get());
                        pOutput.accept(CodexaModBlocks.CONCRETE_SLAB.get());
                        pOutput.accept(CodexaModBlocks.CONCRETE_WALL.get());
                        pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE.get());
                        pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE_STRAIRS.get());
                        pOutput.accept(CodexaModBlocks.POLISHED_CONCRETE_SLAB.get());

                        pOutput.accept(CodexaModBlocks.BLACK_HOLE.get());

                        pOutput.accept(CodexaModItems.DARK_STAR_RING.get());
                        pOutput.accept(CodexaModItems.IRON_LONGSWORD.get());
                        pOutput.accept(CodexaModItems.WOODEN_GREATSWORD.get());
                        pOutput.accept(CodexaModItems.TOMAHAWK.get());

                        pOutput.accept(CodexaModItems.WISDOM_FRUIT.get());
                        pOutput.accept(CodexaModBlocks.PALE_FORTUNE.get());

                        pOutput.accept(CodexaModItems.BAMBOO_CHARCOAL.get());

                        pOutput.accept(CodexaModBlocks.SUNELM_LOG.get());
                        pOutput.accept(CodexaModBlocks.SUNELM_WOOD.get());
                        pOutput.accept(CodexaModBlocks.STRIPPED_SUNELM_LOG.get());
                        pOutput.accept(CodexaModBlocks.STRIPPED_SUNELM_WOOD.get());
                        pOutput.accept(CodexaModBlocks.SUNELM_PLANKS.get());
                        pOutput.accept(CodexaModBlocks.SUNELM_LEAVES.get());
                        pOutput.accept(CodexaModBlocks.SUNELM_SAPLING.get());

                        pOutput.accept(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get());
                        pOutput.accept(CodexaModBlocks.MIDNIGHT_DIRT.get());
                        pOutput.accept(CodexaModBlocks.MIDNIGHT_STONE.get());
                        pOutput.accept(CodexaModBlocks.MIDNIGHT_GRASS.get());

                        pOutput.accept(CodexaModBlocks.OTHERWORLDLY_LANTERN.get());

                        pOutput.accept(CodexaModItems.GEM_GOLEM_SPANW_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
