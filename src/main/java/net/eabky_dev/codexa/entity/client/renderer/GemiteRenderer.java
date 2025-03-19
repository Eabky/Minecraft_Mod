package net.eabky_dev.codexa.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.eabky_dev.codexa.entity.client.model.GemiteModel;
import net.eabky_dev.codexa.entity.client.model.SpiderWebSpiderModel;
import net.eabky_dev.codexa.entity.custom.GemiteEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GemiteRenderer extends GeoEntityRenderer<GemiteEntity>
{
    public GemiteRenderer(EntityRendererProvider.Context renderManager)
    {
        super(renderManager, new GemiteModel());
    }
}
