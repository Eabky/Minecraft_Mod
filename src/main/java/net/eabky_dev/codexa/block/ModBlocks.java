package net.eabky_dev.codexa.block;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.custom.BlackHoleBlock;
import net.eabky_dev.codexa.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks
{
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, CODEXA.MOD_ID);

    public static final RegistryObject<Block> PLATINUM_BLOCK = registerBlock("platinum_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));
    public static final RegistryObject<Block> DEEPSLATE_PLATINUM_ORE = registerBlock("deepslate_platinum_ore",
            ()-> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .strength(5f)
                    .sound(SoundType.DEEPSLATE)
                    .requiresCorrectToolForDrops(),UniformInt.of(3, 6)));
    public static final RegistryObject<Block> BLACK_HOLE = registerBlock("black_hole",
            ()-> new BlackHoleBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK)));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }
}
