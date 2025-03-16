package net.eabky_dev.codexa.client;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModParticles;
import net.eabky_dev.codexa.particle.DarkStarRingParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleRegisterEvent
{
    @SubscribeEvent
    public static void registerParticles(RegisterParticleProvidersEvent event)
    {
        event.registerSpriteSet(CodexaModParticles.DARK_STAR_RING_PARTICLE.get(), DarkStarRingParticle.Provider::new);
    }
}
