package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.eabky_dev.codexa.init.CodexaModPotions;
import net.eabky_dev.codexa.util.CodexaBrewingRecipe;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class BrewingPotionsProcedure
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
