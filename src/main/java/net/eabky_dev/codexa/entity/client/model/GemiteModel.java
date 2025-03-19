package net.eabky_dev.codexa.entity.client.model;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemiteEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GemiteModel extends GeoModel<GemiteEntity> {
    @Override
    public ResourceLocation getModelResource(GemiteEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "geo/gemite.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GemiteEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "textures/entity/gemite.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GemiteEntity animatable)
    {
        return ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "animations/gemite.animation.json");
    }

    @Override
    public void setCustomAnimations(GemiteEntity animatable, long instanceId, AnimationState<GemiteEntity> animationState)
    {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null)
        {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
