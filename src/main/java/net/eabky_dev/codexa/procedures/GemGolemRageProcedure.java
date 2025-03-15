package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.entity.custom.GemSpikeEntity;
import net.eabky_dev.codexa.init.CodexaModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class GemGolemRageProcedure
{
    private static final Map<Integer, Integer> spikeLifetimes = new HashMap<>();

    @SubscribeEvent
    public static void gemGolemRage(LivingEvent.LivingTickEvent event)
    {
        if (event.getEntity() instanceof GemGolemEntity gemGolem && !gemGolem.isRaging() && gemGolem.getHealth() <= gemGolem.getMaxHealth() * 0.5)
        {
            execute(gemGolem.level(), gemGolem);
        }
    }

    private static void execute(Level level, GemGolemEntity gemGolem)
    {
        if (level instanceof ServerLevel serverLevel)
        {
            System.out.println(" GEM GOLEM IS RAGING!");

            gemGolem.setRaging(true);
            gemGolem.playSound(gemGolem.getRoarSound(), 5, 1); // need to fix cause when I get pushed the sound is too low

            int maxDistance = 8; // Maximum number of blocks for the wave
            int rowSpacing = 3; // Each row will be spaced 3 blocks apart

            // Loop to create a row for each distance (up to 8 blocks)
            for (int i = 1; i <= maxDistance; i++)
            {
                double currentDistance = rowSpacing * i;

                // Calculate the positions for each row of spikes (8 directions per row)
                Vec3[] directions = {
                        new Vec3(currentDistance, 0, 0), new Vec3(-currentDistance, 0, 0),
                        new Vec3(0, 0, currentDistance), new Vec3(0, 0, -currentDistance),
                        new Vec3(currentDistance, 0, currentDistance), new Vec3(currentDistance, 0, -currentDistance),
                        new Vec3(-currentDistance, 0, currentDistance), new Vec3(-currentDistance, 0, -currentDistance)
                };

                // Spawn spikes for this row
                for (Vec3 dir : directions)
                {
                    GemSpikeEntity gemSpike = CodexaModEntities.GEM_SPIKE.get().create(level);
                    if (gemSpike != null)
                    {
                        Vec3 spawnPos = gemGolem.position().add(dir);
                        gemSpike.moveTo(spawnPos.x, spawnPos.y, spawnPos.z);
                        serverLevel.addFreshEntity(gemSpike);
                        spikeLifetimes.put(gemSpike.getId(), 1000 + (i*500));
                    }
                }

                try {
                    // Add a delay between each row to give a wave effect
                    Thread.sleep(300);  // Adjust delay as needed for the effect
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(LivingEvent.LivingTickEvent event)
    {
        if (!(event.getEntity().level() instanceof ServerLevel serverLevel)) return;

        Iterator<Map.Entry<Integer, Integer>> iterator = spikeLifetimes.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int entityId = entry.getKey();
            int ticksLeft = entry.getValue();

            if (ticksLeft <= 0)
            {
                Entity entity = serverLevel.getEntity(entityId);
                if (entity instanceof GemSpikeEntity gemSpike)
                {
                    // Check if the spike has a rider
                    if (gemSpike.getControllingPassenger() != null)
                    {
                        // If the spike has a rider, don't remove it
                        System.out.println("Gem Spike " + entityId + " has a rider and won't be removed.");
                        continue; // Skip removal for this spike
                    }

                    // If no rider, proceed with removal
                    System.out.println("Entity " + entityId + " has expired!");
                    gemSpike.discard();
                }
                iterator.remove();
            }
            else
            {
                entry.setValue(ticksLeft - 1);
            }
        }
    }
}
