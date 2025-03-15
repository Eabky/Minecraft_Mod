package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemSpikeEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class GemSpikeSpawnProcedure
{
    @SubscribeEvent
    public static void onSpikeSpawn(EntityJoinLevelEvent event)
    {
        // Only process if the entity is a GemSpikeEntity
        if (event.getEntity() instanceof GemSpikeEntity gemSpike && !gemSpike.level().isClientSide && gemSpike.level() instanceof ServerLevel serverLevel)
        {
            // Get the spike's position
            BlockPos spikePos = gemSpike.blockPosition();

            // Play block breaking sound and create explosion particles
            serverLevel.playSound(null, spikePos, SoundEvents.GENERIC_EXPLODE, net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

            // Create a particle effect using addParticle
            ParticleOptions particleData = ParticleTypes.EXPLOSION;
            serverLevel.addParticle(particleData, spikePos.getX() + 0.5, spikePos.getY() + 0.5, spikePos.getZ() + 0.5, 0.1, 0.1, 0.1);

            Set<String> gemTeamEntities = gemSpike.loadGemTeamEntities(serverLevel);

            double grabRadius = 1.0;
            for (Entity entity : gemSpike.level().getEntities(gemSpike, gemSpike.getBoundingBox().inflate(grabRadius)))
            {
                String entityKey = entity.getType().toString();

                if (!gemTeamEntities.contains(entityKey))
                {
                    entity.hurt(serverLevel.damageSources().explosion(gemSpike, entity), 10.0F);

                    if (!entity.hasPassenger(gemSpike))
                    {
                        entity.startRiding(gemSpike, true);
                        System.out.println("Entity " + entity.getStringUUID() + " is now mounted on the GemSpike.");
                    }
                }
            }
        }
    }
}
