package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.item.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.List;

public class CodexaModItems
{
    public static final DeferredRegister<Item> REGISTRY =
        DeferredRegister.create(ForgeRegistries.ITEMS, CODEXA.MOD_ID);

    public static final RegistryObject<Item> PLATINUM_INGOT = REGISTRY.register("platinum_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLATINUM = REGISTRY.register("raw_platinum",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_RESIDUE = REGISTRY.register("platinum_residue",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET = REGISTRY.register("platinum_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = REGISTRY.register("platinum_pickaxe",
            () -> new PickaxeItem(CodexaModToolTiers.PLATINUM, 1, 1,
                    new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HELMET = REGISTRY.register("platinum_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = REGISTRY.register("platinum_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = REGISTRY.register("platinum_leggings",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BOOTS = REGISTRY.register("platinum_boots",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PALADINIUM_INGOT = REGISTRY.register("paladinium_ingot",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STAR_RING = REGISTRY.register("dark_star_ring",
            ()-> new DarkStarRingItem(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag)
                {
                    pTooltipComponents.add(Component.translatable("tooltip.codexa.dark_star_ring.tooltip"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> RING_OF_DEFIANCE = REGISTRY.register("ring_of_defiance",
            ()-> new RingOfDefiance(new Item.Properties()));
    public static final RegistryObject<Item> MANTLE_OF_THE_UNIVERSE = REGISTRY.register("mantle_of_the_universe",
            ()-> new MantleOfTheUniverse(new Item.Properties()));

    public static final RegistryObject<Item> IRON_LONGSWORD = REGISTRY.register("iron_longsword",
            ()-> new SwordItem(Tiers.IRON, 3,-1F,
                    new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_GREATSWORD = REGISTRY.register("wooden_greatsword",
            ()-> new SwordItem(Tiers.IRON, 2,-3F,
                    new Item.Properties()));
    public static final RegistryObject<Item> BOSS_KILLER = REGISTRY.register("boss_killer",
            ()-> new BossKillerItem(Tiers.NETHERITE, 5,1F,
                    new Item.Properties()));
    public static final RegistryObject<Item> TOMAHAWK = REGISTRY.register("tomahawk",
            () -> new TomahawkItem(new Item.Properties().stacksTo(16))); //will remove all releveant classes and files this is just for testing


    public static final RegistryObject<Item> WISDOM_FRUIT = REGISTRY.register("wisdom_fruit",
            ()-> new Item(new Item.Properties().food(CodexaModFoodProperties.WISDOM_FRUIT)));


    public static final RegistryObject<Item> BAMBOO_CHARCOAL = REGISTRY.register("bamboo_charcoal",
            ()-> new FuelItem(new Item.Properties(), 800));

    public static final RegistryObject<Item> OTHERWORLDLY_LANTERN_ITEM = REGISTRY.register("otherworldly_lantern",
            () -> new OtherworldlyLanternBlockItem(CodexaModBlocks.OTHERWORLDLY_LANTERN.get(), new Item.Properties()));


    public static final RegistryObject<Item> GEM_GOLEM_SPANW_EGG = REGISTRY.register("gem_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(CodexaModEntities.GEM_GOLEM, 5131342, 9507726, new Item.Properties()));

    static
    {

    }
}
