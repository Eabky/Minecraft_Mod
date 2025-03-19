package net.eabky_dev.codexa.entity.custom;

import net.eabky_dev.codexa.entity.ai.GemGolemAttackGoal;
import net.eabky_dev.codexa.sound.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
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
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.function.Predicate;

public class GemGolemEntity extends Monster
{
    private boolean isAttackable;
    private int howLongIsDead;
    private int rageTimer;

    private static float attackDamage = 10f;
    private static float maxHealth = 300f;
    private static float armorToughness = 35f;
    private static float attackKnockback = 2f;
    private static float knockbackResistance = 1.2f;
    private static double movementSpeed = 0.2D;

    public boolean isDead = false;
    public boolean isRaging = false;

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

        isAttackable = true;
        howLongIsDead = 0;
        rageTimer = 0;
        isRaging = false;

        this.setRaging(false);

        System.out.println("GEM GOLEM SPAWNED ON: " + pLevel);
    }

    private static final EntityDataAccessor<Boolean> ATTACKING = SynchedEntityData.defineId(GemGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> RAGING = SynchedEntityData.defineId(GemGolemEntity.class, EntityDataSerializers.BOOLEAN);


    // private final ServerBossEvent bossEvent = new ServerBossEvent(Component.literal("Gem Golem"), BossEvent.BossBarColor.PURPLE, BossEvent.BossBarOverlay.PROGRESS);


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

        if (this.level().isClientSide())
        {
            setupAnimationStates();
        }

        // Handling the rage animation
        if (this.getHealth() == 0.1F)
        {
            this.isAttackable = false;
            this.isDead = true;

            if (deathAnimationTimeout == 1)
            {
                this.deathAnimationState.start(tickCount);
            }

            this.idleAnimationState.stop();
            this.removeAllGoals(pFilter);
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

        if (isDead && howLongIsDead >= 80)
        {
            this.remove(RemovalReason.KILLED);
        }

        // Rage state handling
        if (!isRaging && this.getHealth() <= this.getMaxHealth() * 0.5)
        {
            this.isRaging = true;
            this.rageAnimationState.start(tickCount);
            this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage * 2);

            rageTimer++;

            if (rageTimer < 88)
            {
                pushEntitiesBack();
            }

            if (rageTimer >= 88)
            {
                this.rageAnimationState.stop();
            }
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

    public boolean isRaging()
    {
        return this.entityData.get(RAGING);
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

    public static AttributeSupplier createAttributes()
    {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, maxHealth)
                .add(Attributes.ARMOR_TOUGHNESS, armorToughness)
                .add(Attributes.MOVEMENT_SPEED, movementSpeed)
                .add(Attributes.ATTACK_DAMAGE, attackDamage)
                .add(Attributes.ATTACK_KNOCKBACK, attackKnockback)
                .add(Attributes.KNOCKBACK_RESISTANCE, knockbackResistance).build();
    }

    private void pushEntitiesBack()
    {
        // Define the radius within which to apply pushback
        double radius = 10.0;  // Adjust the radius to your needs
        Vec3 golemPos = this.position();

        // Iterate over entities in the level and push them back if they are not GemGolems
        for (Entity entity : level().getEntities(this, this.getBoundingBox().inflate(radius)))
        {
            if (!(entity instanceof GemGolemEntity))  // Don't push back other Gem Golems
            {
                // Calculate the direction opposite to the golem
                Vec3 pushDirection = entity.position().subtract(golemPos).normalize().scale(1.5); // Push force (adjust scale)

                // Apply the pushback by modifying entity's velocity
                entity.setDeltaMovement(entity.getDeltaMovement().add(pushDirection));
            }
        }
    }

    @Override
    public boolean isAttackable()
    {
        return isAttackable;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount)
    {
        System.out.println("Golem health: " + this.getHealth() );
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

    public SoundEvent getRoarSound()
    {
        return ModSounds.GEM_GOLEM_ROAR.get();
    }

//    /* BOSS BAR */
//    @Override
//    public void startSeenByPlayer(ServerPlayer pServerPlayer)
//    {
//        super.startSeenByPlayer(pServerPlayer);
//        this.bossEvent.addPlayer(pServerPlayer);
//    }

//    @Override
//    public void stopSeenByPlayer(ServerPlayer pServerPlayer)
//    {
//        super.stopSeenByPlayer(pServerPlayer);
//        this.bossEvent.removePlayer(pServerPlayer);
//    }

    @Override
    public void aiStep()
    {
        super.aiStep();
        // this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    public static void init() {
    }
}