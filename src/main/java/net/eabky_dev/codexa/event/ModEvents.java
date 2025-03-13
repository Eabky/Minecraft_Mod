package net.eabky_dev.codexa.event;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.init.CodexaModPotions;
import net.eabky_dev.codexa.item.BossKillerItem;
import net.minecraft.server.Bootstrap;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.sound.sampled.Port;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID)
public class ModEvents
{

    @SubscribeEvent
    public static void onGemGolemDeath(LivingDeathEvent event)
    {
        if(event.getEntity() instanceof GemGolemEntity entity)
        {
            event.setCanceled(true);

            entity.setPose(Pose.STANDING);
            entity.setHealth(0.1f);

            System.out.println("Gem Golem died: " +event.isCanceled()+ " Gem Golem pose: " +entity.getPose());
        }
    }

    @SubscribeEvent
    public static void onBossKillerAttack(LivingAttackEvent event)
    {
        if (event.getSource().getDirectEntity() instanceof Player player)
        {
            ItemStack mainHandItem = player.getMainHandItem();

            if (mainHandItem.getItem() instanceof BossKillerItem bossKillerItem)
            {
                float extraDamage = event.getEntity().getMaxHealth() * 0.10f;

                bossKillerItem.setBonusDamage(mainHandItem, extraDamage);

                System.out.println("Boss Killer attack bonus set to: " + extraDamage);
            }
        }
    }
}
