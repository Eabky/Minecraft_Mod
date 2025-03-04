package net.eabky_dev.codexa.item;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.item.custom.DarkStarRingItem;
import net.eabky_dev.codexa.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.List;

public class ModItems
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

    public static final RegistryObject<Item> WISDOM_FRUIT = ITEMS.register("wisdom_fruit",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.WISDOM_FRUIT)));

    public static final RegistryObject<Item> BAMBOO_CHARCOAL = ITEMS.register("bamboo_charcoal",
            ()-> new FuelItem(new Item.Properties(), 800));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
