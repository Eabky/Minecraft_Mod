package net.eabky_dev.codexa.procedures;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class FlowerPotProcedure
{
    @SubscribeEvent
    public static void flowerPot(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(CodexaModBlocks.PALE_FORTUNE.getId(), CodexaModBlocks.POTTED_PALE_FORTUNE);

            //BrewingRecipeRegistry.addRecipe(new CodexaBrewingRecipe(Potions.AWKWARD, CodexaModItems.PALADINIUM_INGOT.get(), CodexaModPotions.CORROSION_PORION.get()));
        });
    }
}
