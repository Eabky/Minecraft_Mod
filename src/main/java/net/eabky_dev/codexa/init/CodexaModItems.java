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
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, CODEXA.MOD_ID);

    public static final RegistryObject<Item> PLATINUM_INGOT = ITEMS.register("platinum_ingot",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_PLATINUM = ITEMS.register("raw_platinum",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_RESIDUE = ITEMS.register("platinum_residue",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_NUGGET = ITEMS.register("platinum_nugget",
            ()-> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_PICKAXE = ITEMS.register("platinum_pickaxe",
            () -> new PickaxeItem(CodexaModToolTiers.PLATINUM, 1, 1,
                    new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_HELMET = ITEMS.register("platinum_helmet",
            () -> new ModArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_CHESTPLATE = ITEMS.register("platinum_chestplate",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_LEGGINGS = ITEMS.register("platinum_leggings",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> PLATINUM_BOOTS = ITEMS.register("platinum_boots",
            () -> new ArmorItem(ModArmorMaterials.PLATINUM, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> PALADINIUM_INGOT = ITEMS.register("paladinium_ingot",
            ()-> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DARK_STAR_RING = ITEMS.register("dark_star_ring",
            ()-> new DarkStarRingItem(new Item.Properties())
            {
                @Override
                public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag)
                {
                    pTooltipComponents.add(Component.translatable("tooltip.codexa.dark_star_ring.tooltip"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });

    public static final RegistryObject<Item> IRON_LONGSWORD = ITEMS.register("iron_longsword",
            ()-> new SwordItem(Tiers.IRON, 3,-1F,
                    new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_GREATSWORD = ITEMS.register("wooden_greatsword",
            ()-> new SwordItem(Tiers.IRON, 2,-3F,
                    new Item.Properties()));
    public static final RegistryObject<Item> BOSS_KILLER = ITEMS.register("boss_killer",
            ()-> new BossKillerItem(Tiers.NETHERITE, 5,1F,
                    new Item.Properties()));
    public static final RegistryObject<Item> TOMAHAWK = ITEMS.register("tomahawk",
            () -> new TomahawkItem(new Item.Properties().stacksTo(16))); //will remove all releveant classes and files this is just for testing


    public static final RegistryObject<Item> WISDOM_FRUIT = ITEMS.register("wisdom_fruit",
            ()-> new Item(new Item.Properties().food(CodexaModFoodProperties.WISDOM_FRUIT)));


    public static final RegistryObject<Item> BAMBOO_CHARCOAL = ITEMS.register("bamboo_charcoal",
            ()-> new FuelItem(new Item.Properties(), 800));

    public static final RegistryObject<Item> OTHERWORLDLY_LANTERN_ITEM = ITEMS.register("otherworldly_lantern",
            () -> new OtherworldlyLanternBlockItem(CodexaModBlocks.OTHERWORLDLY_LANTERN.get(), new Item.Properties()));


    public static final RegistryObject<Item> GEM_GOLEM_SPANW_EGG = ITEMS.register("gem_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(CodexaModEntities.GEM_GOLEM, 5131342, 9507726, new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
