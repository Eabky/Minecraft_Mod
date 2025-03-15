package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.client.renderer.*;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class CodexaModEntityRenders
{
    public CodexaModEntityRenders() {
    }

    @SubscribeEvent
    public static void registerEntityRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(CodexaModEntities.GEM_GOLEM.get(), GemGolemRenderer::new);
        event.registerEntityRenderer(CodexaModEntities.TOMAHAWK.get(), TomahawkProjectileRenderer::new);
        event.registerEntityRenderer(CodexaModEntities.GEM_SPIKE.get(), GemSpikeRenderer::new);
        event.registerEntityRenderer(CodexaModEntities.SPIDER_WEB_SPIDER.get(), SpiderWebSpiderRenderer::new);

        BlockEntityRenderers.register(CodexaModBlockEntities.OTHERWORLDLY_LANTERN_BE.get(), OtherworldlyLanternRenderer::new);
    }

    @SubscribeEvent
    public static void registerBlockEntityRenderer(FMLClientSetupEvent event)
    {

    }
}
