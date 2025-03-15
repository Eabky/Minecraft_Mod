package net.eabky_dev.codexa.entity.client.model;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.SpiderWebSpiderEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.model.GeoModel;

public class SpiderWebSpiderModel extends GeoModel<SpiderWebSpiderEntity>
{
    @Override
    public ResourceLocation getModelResource(SpiderWebSpiderEntity spiderWebSpiderEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "geo/spider_web_spider.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpiderWebSpiderEntity spiderWebSpiderEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/entity/spider_web_spider.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SpiderWebSpiderEntity spiderWebSpiderEntity)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "animations/spider_web_spider.animation.json");
    }

    public CoreGeoBone getBone ()
    {
        CoreGeoBone coreBone = getAnimationProcessor().getBone("scene_spider");
        return coreBone;
    }
}
