package net.eabky_dev.codexa.client;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.eabky_dev.codexa.init.CodexaModPotions;
import net.eabky_dev.codexa.util.CodexaBrewingRecipe;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = CODEXA.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BrewingPotionsRegisterEvent
{
    @SubscribeEvent
    public static void potionBrewing(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            BrewingRecipeRegistry.addRecipe(new CodexaBrewingRecipe(Potions.AWKWARD, CodexaModItems.PALADINIUM_INGOT.get(), CodexaModPotions.CORROSION_PORION.get()));
        });
    }
}
