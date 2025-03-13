package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class CodexaModToolTiers {
    public static final Tier PLATINUM = TierSortingRegistry.registerTier(
            new ForgeTier(5, 1500, 5f, 4f, 25, //Balance later.
                    ModTags.Blocks.NEEDS_PLATINUM_TOOL, () -> Ingredient.of(CodexaModItems.PLATINUM_INGOT.get())),
            ResourceLocation.fromNamespaceAndPath(CODEXA.MOD_ID, "platinum"), List.of(Tiers.IRON), List.of());

}
