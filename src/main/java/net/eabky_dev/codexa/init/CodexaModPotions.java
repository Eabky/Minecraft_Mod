package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModPotions
{
    public static final DeferredRegister<Potion> REGISTRY;
    public static final RegistryObject<Potion> CORROSION_PORION;

    public CodexaModPotions() {
    }

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.POTIONS, CODEXA.MOD_ID);
        CORROSION_PORION = REGISTRY.register("corrosion_potion", () -> new Potion(new MobEffectInstance(CodexaModMobEffects.CORROSION.get(), 200, 0)));
    }
}
