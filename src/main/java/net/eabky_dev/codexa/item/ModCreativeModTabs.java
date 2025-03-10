package net.eabky_dev.codexa.item;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs
{
    public static final DeferredRegister<CreativeModeTab>  CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CODEXA.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CODEXA_TAB = CREATIVE_MODE_TABS.register("codexa_tab",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.PLATINUM_INGOT.get()))
                    .title(Component.translatable("creativetab.codexa_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PLATINUM_INGOT.get());
                        pOutput.accept(ModItems.RAW_PLATINUM.get());
                        pOutput.accept(ModItems.PLATINUM_RESIDUE.get());
                        pOutput.accept(ModItems.PLATINUM_NUGGET.get());
                        pOutput.accept(ModItems.PLATINUM_PICKAXE.get());
                        pOutput.accept(ModItems.PLATINUM_HELMET.get());
                        pOutput.accept(ModItems.PLATINUM_CHESTPLATE.get());
                        pOutput.accept(ModItems.PLATINUM_LEGGINGS.get());
                        pOutput.accept(ModItems.PLATINUM_BOOTS.get());

                        pOutput.accept(ModItems.PALADINIUM_INGOT.get());

                        pOutput.accept(ModBlocks.PLATINUM_BLOCK.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_PLATINUM_ORE.get());
                        pOutput.accept(ModBlocks.CONCRETE.get());
                        pOutput.accept(ModBlocks.CONCRETE_STRAIRS.get());
                        pOutput.accept(ModBlocks.CONCRETE_SLAB.get());
                        pOutput.accept(ModBlocks.CONCRETE_WALL.get());
                        pOutput.accept(ModBlocks.POLISHED_CONCRETE.get());
                        pOutput.accept(ModBlocks.POLISHED_CONCRETE_STRAIRS.get());
                        pOutput.accept(ModBlocks.POLISHED_CONCRETE_SLAB.get());

                        pOutput.accept(ModBlocks.BLACK_HOLE.get());

                        pOutput.accept(ModItems.DARK_STAR_RING.get());
                        pOutput.accept(ModItems.IRON_GREATSWORD.get());

                        pOutput.accept(ModItems.WISDOM_FRUIT.get());
                        pOutput.accept(ModBlocks.PALE_FORTUNE.get());

                        pOutput.accept(ModItems.BAMBOO_CHARCOAL.get());

                        pOutput.accept(ModBlocks.SUNELM_LOG.get());
                        pOutput.accept(ModBlocks.SUNELM_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_SUNELM_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_SUNELM_WOOD.get());
                        pOutput.accept(ModBlocks.SUNELM_PLANKS.get());
                        pOutput.accept(ModBlocks.SUNELM_LEAVES.get());
                        pOutput.accept(ModBlocks.SUNELM_SAPLING.get());

                        pOutput.accept(ModItems.GEM_GOLEM_SPANW_EGG.get());
                    })
                    .build());

    public static void register(IEventBus eventBus)
    {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
