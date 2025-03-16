package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModParticles
{
    public static DeferredRegister<ParticleType<?>> REGISTRY;
    public static RegistryObject<ParticleType> DARK_STAR_RING_PARTICLE;

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, CODEXA.MOD_ID);
        DARK_STAR_RING_PARTICLE = REGISTRY.register("dark_star_ring_particle", () -> new SimpleParticleType(true));
    }
}
