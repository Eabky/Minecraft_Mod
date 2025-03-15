package net.eabky_dev.codexa.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModModelLayers;
import net.eabky_dev.codexa.entity.client.model.GemSpikeModel;
import net.eabky_dev.codexa.entity.custom.GemSpikeEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class GemSpikeRenderer extends EntityRenderer<GemSpikeEntity>
{
    private final GemSpikeModel model;

    public GemSpikeRenderer(EntityRendererProvider.Context pContext)
    {
        super(pContext);
        this.model = new GemSpikeModel(pContext.bakeLayer(CodexaModModelLayers.GEM_SPIKE_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(GemSpikeEntity pEntity)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/entity/gem_spike.png");
    }

    @Override
    public void render(GemSpikeEntity pEntity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight)
    {
        VertexConsumer vertexconsumer = buffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

        super.render(pEntity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
