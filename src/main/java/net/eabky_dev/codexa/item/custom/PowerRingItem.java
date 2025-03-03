package net.eabky_dev.codexa.item.custom;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class PowerRingItem extends Item
{
    public PowerRingItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
    {

        if (!pLevel.isClientSide && pEntity instanceof Player player)
        {
            DamageSource ringDamageSource = pLevel.damageSources().magic();
            float minDamage = 0.5f;
            float maxDamage = 4f;

            double radius = 3.0; // Define the radius around the player
            AABB area = new AABB(
                    pEntity.getX() - radius, pEntity.getY() - radius, pEntity.getZ() - radius,
                    pEntity.getX() + radius, pEntity.getY() + radius, pEntity.getZ() + radius
            );

            List<LivingEntity> nearbyEntities = pLevel.getEntitiesOfClass(LivingEntity.class, area, e -> e != player);
            for (LivingEntity entity : nearbyEntities)
            {
                float distanceToPlayer = entity.distanceTo(player);

                // Normalize distance (closer = higher damage). Distance / radius * maxDamage - minDamage scales the damage reduction. Then we subtract from maxDamage. When distance=0 we get maxDamage, when distance=radius we get minDamage.
                float pAmount = maxDamage - (distanceToPlayer / (float) radius) * (maxDamage - minDamage);
                pAmount = Math.max(minDamage, pAmount); // Ensure it doesn't go below minDamage

                entity.hurt(ringDamageSource, pAmount);
                System.out.println(entity + "detected");
                System.out.println(entity.getHealth()-pAmount);
            }
        }

        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }
}
