package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.entity.custom.GemSpikeEntity;
import net.eabky_dev.codexa.init.CodexaModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;
import java.util.List;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class GemGolemMelleeAttackProcedure
{
    private static boolean spikeExists = false;

    @SubscribeEvent
    public static void onGemGolemMelleeAttack(LivingAttackEvent event)
    {
        execute(event, event.getEntity().level(), event.getEntity());
    }

    public static void execute(Level level, double x, double y, double z, Entity entity)
    {
        execute((LivingAttackEvent) null, level, entity);
    }

    private static void execute(@Nullable LivingAttackEvent event, Level level, Entity entity)
    {
        if (event.getSource().getDirectEntity() instanceof GemGolemEntity gemGolemEntity)
        {
            GemSpikeEntity gemSpikeEntity = CodexaModEntities.GEM_SPIKE.get().create(level);
            LivingEntity target = gemGolemEntity.getTarget();

            double radius = 6.0; // Define the radius around the target
            AABB area = new AABB(
                    target.getX() - radius, target.getY() - radius, target.getZ() - radius,
                    target.getX() + radius, target.getY() + radius, target.getZ() + radius
            );

            List<Entity> nearbyEntities = target.level().getEntitiesOfClass(Entity.class, area);

            for(Entity nearbyEntity : nearbyEntities)
            {
                System.out.println(nearbyEntity);

                if (nearbyEntity instanceof GemSpikeEntity)
                {
                    spikeExists = true;
                }
            }

            if (!level.isClientSide() && gemSpikeEntity != null && !spikeExists)
            {
                gemSpikeEntity.setPos(target.getX(), target.getY(), target.getZ());
                level.addFreshEntity(gemSpikeEntity);
            }

            spikeExists = false;
        }
    }

}
