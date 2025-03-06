package net.eabky_dev.codexa.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GemGolemRenderer extends MobRenderer<GemGolemEntity, GemGolemModel<GemGolemEntity>>
{
    public GemGolemRenderer(EntityRendererProvider.Context pContext)
    {
        super(pContext, new GemGolemModel<>(pContext.bakeLayer(ModModelLayers.GEM_GOLEM_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(GemGolemEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/entity/gem_golem.png");
    }

    @Override
    public void render(GemGolemEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
