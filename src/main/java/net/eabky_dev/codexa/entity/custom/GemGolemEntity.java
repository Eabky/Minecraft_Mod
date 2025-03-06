package net.eabky_dev.codexa.entity.custom;

import net.eabky_dev.codexa.entity.ai.GemGolemAttackGoal;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class GemGolemEntity extends Monster
{
    private boolean isAttackable = true;
    private int howLongIsDead = 0;
    private boolean rageTriggered = false;

    public boolean isDead = false;;

    Predicate<Goal> pFilter = goal ->
            goal instanceof WaterAvoidingRandomStrollGoal ||
                    goal instanceof LookAtPlayerGoal ||
                    goal instanceof RandomLookAroundGoal ||
                    goal instanceof HurtByTargetGoal ||
                    goal instanceof GemGolemAttackGoal ||
                    goal instanceof NearestAttackableTargetGoal;


    public GemGolemEntity(EntityType<? extends Monster> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
    }

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(GemGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RAGING =
            SynchedEntityData.defineId(GemGolemEntity.class, EntityDataSerializers.BOOLEAN);

    private final ServerBossEvent bossEvent = new ServerBossEvent(Component.literal("Gem Golem"),
            BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS);


    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;
    public final AnimationState rageAnimationState = new AnimationState();
    public final AnimationState deathAnimationState = new AnimationState();
    public int deathAnimationTimeout = 1;



    @Override
    public void tick()
    {
        super.tick();

        if(this.level().isClientSide())
        {
            setupAnimationStates();
        }

        if(this.getHealth() == 0.1F)
        {
            this.isAttackable = false;
            this.isDead = true;

            if (deathAnimationTimeout == 1)
            {
                this.deathAnimationState.start(tickCount);
            }

            this.idleAnimationState.stop();
            this.removeAllGoals(pFilter);
            this.setRaging(false);
            this.setAttacking(false);

            if (deathAnimationTimeout > 0)
            {
                deathAnimationTimeout--;
            }
            else
            {
                howLongIsDead++;
            }
        }

        if(isDead && howLongIsDead >= 80)
        {
            this.remove(RemovalReason.KILLED);
        }

        if (!rageTriggered && this.getHealth() <= this.getMaxHealth() * 0.5)
        {
            this.setRaging(true);
            rageAnimationState.start(this.tickCount);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(2f);
            rageTriggered = true;
        }
    }

    private void setupAnimationStates()
    {
        if(this.idleAnimationTimeout <= 0)
        {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        }
        else
        {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0)
        {
            attackAnimationTimeout = 54;
            attackAnimationState.start(this.tickCount);
        }
        else
        {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick)
    {
        float f;
        if(this.getPose() == Pose.STANDING && !isDead)
        {
            f = Math.min(pPartialTick * 6F, 1f);
        }
        else
        {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }



    public void setAttacking(boolean attacking)
    {
        this.entityData.set(ATTACKING, attacking);
    }

    public void setRaging(boolean raging)
    {
        this.entityData.set(RAGING, raging);
    }

    public boolean isAttacking()
    {
        return this.entityData.get(ATTACKING);
    }

    @Override
    protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false);
        this.entityData.define(RAGING, false);
    }

    @Override
    protected void registerGoals()
    {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.2D, 120));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.goalSelector.addGoal(1, new GemGolemAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 1000d)
                .add(Attributes.ARMOR_TOUGHNESS, 100f)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 25f)
                .add(Attributes.ATTACK_KNOCKBACK, 2f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.2f);
    }

    @Override
    public boolean isAttackable()
    {
        return isAttackable;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount)
    {
        this.walkAnimation.setSpeed(1.5F);
        boolean flag1 = true;

        if ((float)this.invulnerableTime > 10.0F && !pSource.is(DamageTypeTags.BYPASSES_COOLDOWN))
        {
            if (pAmount <= this.lastHurt)
            {
                return false;
            }

            this.actuallyHurt(pSource, pAmount - this.lastHurt);
            this.lastHurt = pAmount;
            flag1 = false;
        }
        else
        {
            this.lastHurt = pAmount;
            this.invulnerableTime = 20;
            this.actuallyHurt(pSource, pAmount);
            this.hurtDuration = 10;
            this.hurtTime = this.hurtDuration;
            System.out.println("Golem health: " + this.getHealth() );
        }

        if (this.isDeadOrDying())
        {
            SoundEvent soundevent = this.getDeathSound();
            if (flag1 && soundevent != null)
            {
                this.playSound(soundevent, this.getSoundVolume(), this.getVoicePitch());
            }

            this.die(pSource);

        }
        else if (flag1)
        {
            this.playHurtSound(pSource);
        }

        return super.hurt(pSource, pAmount);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.IRON_GOLEM_STEP;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

    /* BOSS BAR */
    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer)
    {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer)
    {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }
}
