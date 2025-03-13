package net.eabky_dev.codexa.enchantment;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

import java.util.logging.Level;

public class LightningStrikeEnchantment extends Enchantment
{

    public LightningStrikeEnchantment(Rarity pRarity, EnchantmentCategory pCategory, EquipmentSlot[] pApplicableSlots)
    {
        super(pRarity, pCategory, pApplicableSlots);
    }

    @Override
    public void doPostAttack(LivingEntity pAttacker, Entity pTarget, int pLevel)
    {
        if (pLevel == 1)
        {
            LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(pAttacker.level());

            if (lightning != null)
            {
                lightning.moveTo(pTarget.getX(), pTarget.getY() + 1, pTarget.getZ());
                pAttacker.level().addFreshEntity(lightning);
            }

            System.out.println("Lightning spawned at: " + pTarget.getX() + ", " + pTarget.getY() + ", " + pTarget.getZ());
        }
    }
}
