package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.effect.CorrosionMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModMobEffects
{
    public static final DeferredRegister<MobEffect> REGISTRY;
    public static final RegistryObject<MobEffect> CORROSION;

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, CODEXA.MOD_ID);
        CORROSION = REGISTRY.register("corrosion", CorrosionMobEffect::new);
    }
}
