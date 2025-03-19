package net.eabky_dev.codexa.event;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.client.model.GemSpikeModel;
import net.eabky_dev.codexa.entity.custom.GemiteEntity;
import net.eabky_dev.codexa.entity.custom.SpiderWebSpiderEntity;
import net.eabky_dev.codexa.init.CodexaModEntities;
import net.eabky_dev.codexa.entity.client.model.GemGolemModel;
import net.eabky_dev.codexa.init.CodexaModModelLayers;
import net.eabky_dev.codexa.entity.client.model.TomahawkProjectileModel;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.util.CodexaKeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents
{
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(CodexaModModelLayers.GEM_GOLEM_LAYER, GemGolemModel::createBodyLayer);
        event.registerLayerDefinition(CodexaModModelLayers.TOMAHAWK_LAYER, TomahawkProjectileModel::createBodyLayer);
        event.registerLayerDefinition(CodexaModModelLayers.GEM_SPIKE_LAYER, GemSpikeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(CodexaModEntities.GEM_GOLEM.get(), GemGolemEntity.createAttributes());
        event.put(CodexaModEntities.GEMITE.get(), GemiteEntity.createAttributes());
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event)
    {
        event.register(CodexaKeyBindings.MANTLE);
    }
}