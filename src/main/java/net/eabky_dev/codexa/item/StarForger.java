package net.eabky_dev.codexa.item;

import net.eabky_dev.codexa.entity.client.renderer.StarForgerRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

import java.util.function.Consumer;

public class StarForger extends SwordItem implements GeoItem
{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public StarForger(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties)
    {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }


    private PlayState predicate(AnimationState animationState)
    {
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.STOP;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private StarForgerRenderer renderer = null;
            // Don't instantiate until ready. This prevents race conditions breaking things
            public BlockEntityWithoutLevelRenderer getItemStackRenderer()
            {
                if (this.renderer == null)
                    this.renderer = new StarForgerRenderer();

                return renderer;
            }
        });
    }

    @Override
    public double getTick(Object itemStack)
    {
        return RenderUtils.getCurrentTick();
    }
}

