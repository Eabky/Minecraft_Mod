package net.eabky_dev.codexa.entity.client.model;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.OtherworldlyLanternBlockEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class OtherworldlyLanternModel extends GeoModel<OtherworldlyLanternBlockEntity>
{

    @Override
    public ResourceLocation getModelResource(OtherworldlyLanternBlockEntity otherworldlyLanternBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "geo/otherworldly_lantern_block.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(OtherworldlyLanternBlockEntity otherworldlyLanternBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/block/otherworldly_lantern_block.png");
    }

    @Override
    public ResourceLocation getAnimationResource(OtherworldlyLanternBlockEntity otherworldlyLanternBlockEntity) {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "animations/otherworldly_lantern.animation.json");
    }
}
