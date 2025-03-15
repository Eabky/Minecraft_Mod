package net.eabky_dev.codexa.entity.custom;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.eabky_dev.codexa.CODEXA;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class GemSpikeEntity extends Entity
{
    private final double[] pistonDeltas = new double[]{0.0D, 0.0D, 0.0D};
    private long pistonDeltasGameTime;
    private int rotation;

    public GemSpikeEntity(EntityType<?> pEntityType, Level pLevel)
    {
        super(pEntityType, pLevel);

        this.setInvulnerable(false);
        this.isAttackable();
        this.canBeHitByProjectile();

        this.rotation = (int) (Math.random() * 4) * 90;

        System.out.println("Spike Spawned: " + this.getStringUUID());
    }

    @Override
    public void baseTick() {
        super.baseTick();

        if (!this.level().isClientSide && this.level() instanceof ServerLevel serverLevel)
        {
            Set<String> gemTeamEntities = loadGemTeamEntities(serverLevel);

            double radius = 10D;
            for (Entity entity : this.level().getEntities(this, this.getBoundingBox().inflate(radius)))
            {
                String entityKey = entity.getType().toString();

                if (!gemTeamEntities.contains(entityKey))
                {
                    pushEntitiesBack();
                    entity.hurt(this.level().damageSources().magic(), 5.0F); // Optional damage
                }
            }
        }
    }

    private void pushEntitiesBack()
    {
        // Define the radius within which to apply pushback
        double radius = 3.0;  // Adjust the radius to your needs
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

        System.out.println("Gem spike got hit");
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

    private double applyPistonMovementRestriction(Direction.Axis pAxis, double pDistance)
    {
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

    public Set<String> loadGemTeamEntities(ServerLevel serverLevel)
    {
        Set<String> entitySet = new HashSet<>();
        ResourceLocation fileLocation = ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "entity_teams/gemteam.json");

        try (InputStreamReader reader = new InputStreamReader(serverLevel.getServer().getResourceManager().getResource(fileLocation).get().open(), StandardCharsets.UTF_8))
        {
            JsonElement jsonElement = JsonParser.parseReader(reader);
            if (jsonElement.isJsonObject())
            {
                JsonObject jsonObject = jsonElement.getAsJsonObject();
                JsonArray entityArray = jsonObject.getAsJsonArray("entities");

                for (JsonElement element : entityArray)
                {
                    entitySet.add(element.getAsString());
                }
            }
        }
        catch (Exception e)
        {
            System.err.println("Failed to load gem team entities: " + e.getMessage());
        }

        return entitySet;
    }

    public int getRotation()
    {
        return rotation;
    }

    public void setRotation(int rotation)
    {
        this.rotation = rotation;
    }
}
