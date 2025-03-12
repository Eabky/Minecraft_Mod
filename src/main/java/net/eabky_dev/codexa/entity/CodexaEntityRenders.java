package net.eabky_dev.codexa.entity;

import net.eabky_dev.codexa.entity.client.GemGolemRenderer;
import net.eabky_dev.codexa.entity.client.TomahawkProjectileRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class CodexaEntityRenders
{
    public CodexaEntityRenders() {
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(CodexaEntities.GEM_GOLEM.get(), GemGolemRenderer::new);
        event.registerEntityRenderer(CodexaEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
    }
}
