package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.enchantment.LightningStrikeEnchantment;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModEnchantments
{
    public static final DeferredRegister<Enchantment> REGISTRY;
    public static final RegistryObject<Enchantment> LIGHTNING_STRIKE_ENCHANTMENT;

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CODEXA.MOD_ID);
        LIGHTNING_STRIKE_ENCHANTMENT = REGISTRY.register("lightning_strike_enchantment", ()-> new LightningStrikeEnchantment(Enchantment.Rarity.COMMON, EnchantmentCategory.WEAPON, new net.minecraft.world.entity.EquipmentSlot[]{net.minecraft.world.entity.EquipmentSlot.MAINHAND}));

    }
}
