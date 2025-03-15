package net.eabky_dev.codexa.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.client.model.SpiderWebSpiderModel;
import net.eabky_dev.codexa.entity.custom.SpiderWebSpiderEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SpiderWebSpiderRenderer extends GeoEntityRenderer<SpiderWebSpiderEntity>
{

    public SpiderWebSpiderRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new SpiderWebSpiderModel());
    }

    @Override
    public void render(SpiderWebSpiderEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight)
    {
        // Apply the stored random rotation (0, 90, 180, or 270 degrees)
        int rotation = entity.getRotation();
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation)); // Rotate around Y-axis

        // Call the super method to continue the rendering process
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(SpiderWebSpiderEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick)
    {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
