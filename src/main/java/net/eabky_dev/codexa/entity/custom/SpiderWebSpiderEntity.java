package net.eabky_dev.codexa.entity.custom;

import com.mojang.blaze3d.vertex.PoseStack;
import net.eabky_dev.codexa.init.CodexaModMobEffects;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

import java.util.List;

public class SpiderWebSpiderEntity extends Entity implements GeoEntity
{
    private boolean isPlayerNearby = false;
    private boolean wasPlayerNearby = false;
    private boolean hasAttacked = false;
    private int animationCooldown = 0;

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private int rotation; // Rotation angle (0, 90, 180, 270)

    public SpiderWebSpiderEntity(EntityType<?> entityType, Level level)
    {
        super(entityType, level);
        this.rotation = (int) (Math.random() * 4) * 90; // Random rotation on spawn (0, 90, 180, 270)
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return null;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public void baseTick()
    {
        super.baseTick();

        // Get the nearest player every tick
        Player player = getNearestPlayer();
        if (player != null)
        {
            double distance = this.distanceTo(player);

            // If the player is within 1 block, attack and apply damage/effects
            if (distance <= 1.0 && !hasAttacked)
            {
                attackPlayer(player);
            }
            else if(distance > 1.0)
            {
                hasAttacked = false;
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers)
    {
        controllers.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState)
    {
        AnimationController<T> controller = tAnimationState.getController();

        if (animationCooldown > 0)
        {
            animationCooldown--; // Reduce cooldown to prevent instant animation switching
            return PlayState.CONTINUE;
        }

        Player player = getNearestPlayer();
        if (player != null)
        {
            double distance = this.distanceTo(player);

            if (distance <= 1.0)
            {
                controller.setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE).then("idle", Animation.LoopType.LOOP)); // Smooth transition back to idle
                animationCooldown = 40;
                return PlayState.CONTINUE;
            }
            else if (distance <= 5.0)
            {
                if (!isPlayerNearby)
                {
                    controller.setAnimation(RawAnimation.begin()
                            .then("scare", Animation.LoopType.PLAY_ONCE)
                            .then("idle", Animation.LoopType.LOOP)); // Queue idle after scare
                    isPlayerNearby = true;
                    wasPlayerNearby = true;
                    animationCooldown = 40; // Prevent spam switching
                    return PlayState.CONTINUE;
                }
                controller.setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
                return PlayState.CONTINUE;
            }
        }

        if (wasPlayerNearby)
        {
            controller.setAnimation(RawAnimation.begin()
                    .then("scared", Animation.LoopType.PLAY_ONCE)
                    .then("afk", Animation.LoopType.LOOP)); // Queue AFK after scared
            wasPlayerNearby = false;
            isPlayerNearby = false;
            animationCooldown = 40; // Prevent instant re-triggering
            return PlayState.CONTINUE;
        }

        controller.setAnimation(RawAnimation.begin().then("afk", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    private Player getNearestPlayer()
    {
        List<Player> players = this.level().getEntitiesOfClass(Player.class, new AABB(this.blockPosition()).inflate(5));
        return players.isEmpty() ? null : players.get(0);
    }

    private void attackPlayer(Player player)
    {
        player.hurt(player.damageSources().generic(), 2.0f);
        player.addEffect(new MobEffectInstance(MobEffects.POISON, 100, 0));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache()
    {
        return cache;
    }
}
