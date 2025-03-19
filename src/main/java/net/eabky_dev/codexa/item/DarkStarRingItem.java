package net.eabky_dev.codexa.item;

import net.eabky_dev.codexa.init.CodexaModParticles;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class DarkStarRingItem extends Item implements ICurioItem
{
    private static final int EFFECT_DELAY = 40; // 2 seconds
    private static final double ORBIT_RADIUS = 1.0;

    public DarkStarRingItem(Properties pProperties)
    {
        super(new Properties().stacksTo(1).defaultDurability(10000));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack)
    {
        if (!(slotContext.entity() instanceof Player player)) return;

        Level pLevel = player.level();
        if (!pLevel.isClientSide() && pLevel instanceof ServerLevel serverLevel)
        {
            AABB area = new AABB(
                    player.getX() - 3, player.getY() - 3, player.getZ() - 3,
                    player.getX() + 3, player.getY() + 3, player.getZ() + 3
            );

            List<LivingEntity> nearbyEntities = pLevel.getEntitiesOfClass(LivingEntity.class, area, e -> e != player);

            // Apply damage every 80 ticks (4 seconds)
            for (LivingEntity entity : nearbyEntities)
            {
                // Spawn orbiting particles
                spawnOrbitingParticles(serverLevel, entity);

                // Check cooldown (apply damage every 80 ticks)
                if (entity.tickCount % EFFECT_DELAY == 0)
                {
                    entity.hurt(pLevel.damageSources().magic(), 6.0f);
                }
            }
        }
    }

    private void spawnOrbitingParticles(ServerLevel serverLevel, LivingEntity entity)
    {
        ParticleOptions particleType = (ParticleOptions) CodexaModParticles.DARK_STAR_RING_PARTICLE.get();
        double height = entity.getBbHeight() / 2.0;
        double entityRadius = entity.getBbWidth(); // Half of entity's width
        double spawnRadius = entityRadius + 1.0; // 1 block away from entity hitbox
        int numParticles = 7;
        double angleStep = Math.PI * 2 / numParticles;
        double angle = (entity.tickCount % EFFECT_DELAY) * Math.PI / 30.0; // Creates smooth orbiting effect

        for (int i = 0; i < numParticles; i++)
        {
            double xOffset = Math.cos(angle + i * angleStep) * spawnRadius;
            double zOffset = Math.sin(angle + i * angleStep) * spawnRadius;

            serverLevel.sendParticles(
                    particleType,
                    entity.getX() + xOffset, entity.getY() + height, entity.getZ() + zOffset,
                    1,
                    0, 0, 0,
                    0
            );
        }
    }

    @Override
    public boolean canEquipFromUse(SlotContext slotContext, ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag)
    {
        pTooltipComponents.add(Component.translatable("tooltip.codexa.dark_star_ring.tooltip"));
        super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
    }
}
