package net.eabky_dev.codexa.event;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.client.GemSpikeModel;
import net.eabky_dev.codexa.init.CodexaModEntities;
import net.eabky_dev.codexa.entity.client.GemGolemModel;
import net.eabky_dev.codexa.entity.client.ModModelLayers;
import net.eabky_dev.codexa.entity.client.TomahawkProjectileModel;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents
{
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ModModelLayers.GEM_GOLEM_LAYER, GemGolemModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TOMAHAWK_LAYER, TomahawkProjectileModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.GEM_SPIKE_LAYER, GemSpikeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event)
    {
        event.put(CodexaModEntities.GEM_GOLEM.get(), GemGolemEntity.createAttributes().build());
    }
}