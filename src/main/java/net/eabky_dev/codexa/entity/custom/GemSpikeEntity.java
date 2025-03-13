package net.eabky_dev.codexa.entity.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Arrays;

public class GemSpikeEntity extends Entity
{
    private final double[] pistonDeltas = new double[]{0.0D, 0.0D, 0.0D};
    private long pistonDeltasGameTime;

    public GemSpikeEntity(EntityType<?> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);
        this.setInvulnerable(false);
        this.isAttackable();
        this.canBeHitByProjectile();
    }

    public static void init() {
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
    public boolean canBeCollidedWith()
    {
        return true;
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount)
    {

        System.out.println("Hit");
        this.remove(Entity.RemovalReason.KILLED);

        return super.hurt(pSource, pAmount);
    }

    @Override
    public void setOnGround(boolean pOnGround) {
        super.setOnGround(pOnGround);
    }

    protected Vec3 limitPistonMovement(Vec3 pPos) {
        if (pPos.lengthSqr() <= 1.0E-7D) {
            return pPos;
        } else {
            long i = this.level().getGameTime();
            if (i != this.pistonDeltasGameTime) {
                Arrays.fill(this.pistonDeltas, 0.0D);
                this.pistonDeltasGameTime = i;
            }

            if (pPos.x != 0.0D) {
                double d2 = this.applyPistonMovementRestriction(Direction.Axis.X, pPos.x);
                return Math.abs(d2) <= (double)1.0E-5F ? Vec3.ZERO : new Vec3(d2, 0.0D, 0.0D);
            } else if (pPos.y != 0.0D) {
                double d1 = this.applyPistonMovementRestriction(Direction.Axis.Y, pPos.y);
                return Math.abs(d1) <= (double)1.0E-5F ? Vec3.ZERO : new Vec3(0.0D, d1, 0.0D);
            } else if (pPos.z != 0.0D) {
                double d0 = this.applyPistonMovementRestriction(Direction.Axis.Z, pPos.z);
                return Math.abs(d0) <= (double)1.0E-5F ? Vec3.ZERO : new Vec3(0.0D, 0.0D, d0);
            } else {
                return Vec3.ZERO;
            }
        }
    }

    private double applyPistonMovementRestriction(Direction.Axis pAxis, double pDistance) {
        int i = pAxis.ordinal();
        double d0 = Mth.clamp(pDistance + this.pistonDeltas[i], -0.51D, 0.51D);
        pDistance = d0 - this.pistonDeltas[i];
        this.pistonDeltas[i] = d0;
        return pDistance;
    }

    @Override
    public void copyPosition(Entity pEntity)
    {
        super.copyPosition(pEntity);
    }

    @Override
    protected void moveTowardsClosestSpace(double pX, double pY, double pZ) {
        super.moveTowardsClosestSpace(pX, pY, pZ);
    }

    @Override
    public boolean isCurrentlyGlowing() {
        return super.isCurrentlyGlowing();
    }
}
