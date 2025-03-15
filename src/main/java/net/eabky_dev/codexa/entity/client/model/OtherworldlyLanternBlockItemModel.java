package net.eabky_dev.codexa.entity.client.model;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.OtherworldlyLanternBlockEntity;
import net.eabky_dev.codexa.item.OtherworldlyLanternBlockItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OtherworldlyLanternBlockItemModel extends GeoModel<OtherworldlyLanternBlockItem>
{

    @Override
    public ResourceLocation getModelResource(OtherworldlyLanternBlockItem otherworldlyLanternBlockItem) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "geo/otherworldly_lantern_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OtherworldlyLanternBlockItem otherworldlyLanternBlockItem) {
    return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/block/otherworldly_lantern_block.png");
}

    @Override
    public ResourceLocation getAnimationResource(OtherworldlyLanternBlockItem otherworldlyLanternBlockItem)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "animations/otherworldly_lantern.animation.json");
    }
}