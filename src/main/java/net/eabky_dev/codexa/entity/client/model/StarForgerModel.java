package net.eabky_dev.codexa.entity.client.model;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.item.StarForger;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class StarForgerModel extends GeoModel<StarForger>
{
    @Override
    public ResourceLocation getModelResource(StarForger animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "geo/starforger.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StarForger animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/item/starforger.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StarForger animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "animations/starforger.animation.json");
    }
}
