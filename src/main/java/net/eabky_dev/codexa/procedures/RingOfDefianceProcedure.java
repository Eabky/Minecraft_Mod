package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.eabky_dev.codexa.item.RingOfDefiance;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import top.theillusivec4.curios.api.CuriosApi;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class RingOfDefianceProcedure
{
    private static int REVIVE_COOLDOWN = 0; // Cooldown in ticks (40 seconds)

    @SubscribeEvent
    public static void onPlayerDamage(LivingDamageEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player)
        {
            CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory ->
            {
                // Access the "ring" slot inventory
                curiosInventory.getStacksHandler("ring").ifPresent(slotInventory ->
                {
                    // Iterate through the items in the slot inventory
                    for (int i = 0; i < slotInventory.getSlots(); i++)
                    {
                        // Get the item stack in the current slot
                        ItemStack stack = slotInventory.getStacks().getStackInSlot(i);

                        // Check if the stack is not empty and if it matches the item you're looking for
                        if (!stack.isEmpty() && stack.getItem() == CodexaModItems.RING_OF_DEFIANCE.get())
                        {
                            if (stack.getItem() instanceof RingOfDefiance)
                            {
                                // 60% chance to negate damage
                                if (Math.random() <= 0.3)
                                {
                                    event.setAmount(0); // Negate damage completely
                                    System.out.println("Damage negated.");
                                }
                            }
                        }
                    }
                });
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event)
    {
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player player)
        {
            // Check if player is on cooldown
            if (player.getPersistentData().getInt("RingOfDefianceCooldown") > 0) {
                return; // Prevent revival if cooldown is active
            }

            CuriosApi.getCuriosInventory(player).ifPresent(curiosInventory ->
            {
                // Access the "ring" slot inventory
                curiosInventory.getStacksHandler("ring").ifPresent(slotInventory ->
                {
                    // Iterate through the items in the slot inventory
                    for (int i = 0; i < slotInventory.getSlots(); i++)
                    {
                        // Get the item stack in the current slot
                        ItemStack stack = slotInventory.getStacks().getStackInSlot(i);

                        // Check if the stack is not empty and if it matches the item you're looking for
                        if (!stack.isEmpty() && stack.getItem() == CodexaModItems.RING_OF_DEFIANCE.get())
                        {
                            if (stack.getItem() instanceof RingOfDefiance && REVIVE_COOLDOWN == 0)
                            {
                                event.setCanceled(true);
                                double reviveHealth = (1 + Math.random() * 5); // Random between 1 and 6 hearts
                                player.revive();
                                player.setHealth((float) reviveHealth); // Set player's health

                                REVIVE_COOLDOWN = 800; // Set cooldown to 40 seconds (20 ticks per second)

                                event.getEntity().level().playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1.0f, 1.0f);
                                event.getEntity().level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, player.getX() + 0.5, player.getY() + 0.5, player.getZ() + 0.5, 0.1, 0.1, 0.1);
                                player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 400, 3));

                                System.out.println("Revived player with " + reviveHealth + " health.");
                            }
                        }
                    }
                });
            });
        }
    }

    @SubscribeEvent
    public static void onTick(TickEvent.PlayerTickEvent event)
    {
        Player player = event.player;
        if (!player.level().isClientSide)
        {
            if (REVIVE_COOLDOWN > 0)
            {
                REVIVE_COOLDOWN--;
            }
        }
    }
}
