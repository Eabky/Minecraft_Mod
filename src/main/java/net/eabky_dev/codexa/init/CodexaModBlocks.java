package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.block.*;
import net.eabky_dev.codexa.worldgen.tree.SunelmTreeGrower;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class CodexaModBlocks
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

    public static final RegistryObject<Block> CONCRETE = registerBlock("concrete",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> CONCRETE_STRAIRS = registerBlock("concrete_stairs",
            ()-> new StairBlock(()-> CodexaModBlocks.CONCRETE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> CONCRETE_SLAB = registerBlock("concrete_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.ANDESITE)));
    public static final RegistryObject<Block> CONCRETE_WALL = registerBlock("concrete_wall",
            ()-> new WallBlock(BlockBehaviour.Properties.copy(CONCRETE.get())));
    public static final RegistryObject<Block> POLISHED_CONCRETE = registerBlock("polished_concrete",
            ()-> new Block(BlockBehaviour.Properties.copy(CONCRETE.get())));
    public static final RegistryObject<Block> POLISHED_CONCRETE_STRAIRS = registerBlock("polished_concrete_stairs",
            ()-> new StairBlock(()-> CodexaModBlocks.POLISHED_CONCRETE.get().defaultBlockState(), BlockBehaviour.Properties.copy(POLISHED_CONCRETE.get())));
    public static final RegistryObject<Block> POLISHED_CONCRETE_SLAB = registerBlock("polished_concrete_slab",
            ()-> new SlabBlock(BlockBehaviour.Properties.copy(POLISHED_CONCRETE.get())));

    public static final RegistryObject<Block> BLACK_HOLE = registerBlock("black_hole",
            ()-> new BlackHoleBlock(BlockBehaviour.Properties.copy(Blocks.BEDROCK))); //temp will need to make it into an entity so the King can use it as an attack

    public static final RegistryObject<Block> PALE_FORTUNE = registerBlock("pale_fortune",
            () -> new FlowerBlock(() -> MobEffects.LUCK, 5,
                    BlockBehaviour.Properties.copy(Blocks.ALLIUM).noOcclusion().noCollission()));
    public static final RegistryObject<Block> POTTED_PALE_FORTUNE = BLOCKS.register("potted_pale_fortune",
            () -> new FlowerPotBlock(() -> ((FlowerPotBlock) Blocks.FLOWER_POT), CodexaModBlocks.PALE_FORTUNE,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_ALLIUM).noOcclusion()));

    public static final RegistryObject<Block> SUNELM_LOG = registerBlock("sunelm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> SUNELM_WOOD = registerBlock("sunelm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SUNELM_LOG = registerBlock("stripped_sunelm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_SUNELM_WOOD = registerBlock("stripped_sunelm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> SUNELM_PLANKS = registerBlock("sunelm_planks",
            () -> new ModPlankBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SUNELM_LEAVES = registerBlock("sunelm_leaves",
            () -> new ModLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));
    public static final RegistryObject<Block> SUNELM_SAPLING = registerBlock("sunelm_sapling",
            () -> new SaplingBlock(new SunelmTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> MIDNIGHT_GRASS_BLOCK = registerBlock("midnight_grass_block",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.GRASS_BLOCK)));
    public static final RegistryObject<Block> MIDNIGHT_DIRT = registerBlock("midnight_dirt",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.DIRT)));
    public static final RegistryObject<Block> MIDNIGHT_STONE = registerBlock("midnight_stone",
            ()-> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> MIDNIGHT_GRASS = registerBlock("midnight_grass",
            () -> new GrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion().noCollission()));

    public static final RegistryObject<Block> OTHERWORLDLY_LANTERN;


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block)
    {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block)
    {
        return CodexaModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
    }

    static
    {
    OTHERWORLDLY_LANTERN = BLOCKS.register("otherworldly_lantern", ()-> new OtherworldlyLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel((state) -> 15)));
    }
}
