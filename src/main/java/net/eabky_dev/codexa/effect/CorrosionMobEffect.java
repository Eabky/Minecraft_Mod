package net.eabky_dev.codexa.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;

public class CorrosionMobEffect extends MobEffect
{
    private static int tickCountCheck = 20;
    private static float playerDamageModifier = 1.0f;

    public CorrosionMobEffect()
    {
        super(MobEffectCategory.HARMFUL, 9281546);
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier)
    {
        return true;
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier)
    {
        if(pAmplifier == 1)
        {
            tickCountCheck = 15;
            playerDamageModifier = 2.0f;
        }
        else if(pAmplifier == 2)
        {
            tickCountCheck = 10;
            playerDamageModifier = 4.0f;
        }
        else if (pAmplifier > 2)
        {
            tickCountCheck = 5;
            playerDamageModifier = 8.0f;
        }

        if (pLivingEntity.tickCount % tickCountCheck == 0)
        {
            if (pLivingEntity.getHealth() > 1.0F)
            {
                pLivingEntity.hurt(pLivingEntity.damageSources().magic(), playerDamageModifier);
            }

            damageEquipment(pLivingEntity, EquipmentSlot.HEAD);
            damageEquipment(pLivingEntity, EquipmentSlot.CHEST);
            damageEquipment(pLivingEntity, EquipmentSlot.LEGS);
            damageEquipment(pLivingEntity, EquipmentSlot.FEET);
            damageEquipment(pLivingEntity, EquipmentSlot.MAINHAND);
            damageEquipment(pLivingEntity, EquipmentSlot.OFFHAND);

            CuriosApi.getCuriosInventory(pLivingEntity).ifPresent(curiosInventory ->
            {
                curiosInventory = CuriosApi.getCuriosInventory(pLivingEntity).resolve().get();
                Map<String, ICurioStacksHandler> curios = curiosInventory.getCurios();

                curios.forEach((identifier, slotInventory) ->
                {
                    for (int i = 0; i < slotInventory.getSlots(); i++)
                    {
                        slotInventory.getStacks().getStackInSlot(i).hurtAndBreak(1, pLivingEntity, (livingEntity) ->
                        {
                            livingEntity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
                        });
                    }
                });
            });
        }
    }

    private void damageEquipment(LivingEntity entity, EquipmentSlot slot)
    {


        if (entity.hasItemInSlot(slot))
        {
            entity.getItemBySlot(slot).hurtAndBreak(1, entity, (livingEntity) ->
            {
                livingEntity.broadcastBreakEvent(slot);
            });
        }

    }
}
