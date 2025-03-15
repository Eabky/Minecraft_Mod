package net.eabky_dev.codexa.entity.custom;

import net.eabky_dev.codexa.init.CodexaModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.RenderUtils;

public class OtherworldlyLanternBlockEntity extends BlockEntity implements GeoBlockEntity
{
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public OtherworldlyLanternBlockEntity(BlockPos pPos, BlockState pBlockState)
    {
        super(CodexaModBlockEntities.OTHERWORLDLY_LANTERN_BE.get(), pPos, pBlockState);
    }

    public void tick(Level pLevel1, BlockPos pPos, BlockState pState1) {
    }

    @Override
    public double getTick(Object blockEntity)
    {
        return RenderUtils.getCurrentTick();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar)
    {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState)
    {
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}
