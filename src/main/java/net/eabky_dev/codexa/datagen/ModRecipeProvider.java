package net.eabky_dev.codexa.datagen;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> PLATINUM_SMELTABLES = List.of(CodexaModItems.RAW_PLATINUM.get(), CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get());
    private static final List<ItemLike> PLATINUM_INGOT_SMELTABLES = List.of(CodexaModItems.PLATINUM_RESIDUE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter)
    {
        oreSmelting(pWriter, PLATINUM_SMELTABLES, RecipeCategory.MISC, CodexaModItems.PLATINUM_INGOT.get(), 0.25f, 200, "platinum");
        oreBlasting(pWriter, PLATINUM_SMELTABLES, RecipeCategory.MISC, CodexaModItems.PLATINUM_INGOT.get(), 0.25f, 200, "platinum");
        oreSmelting(pWriter, PLATINUM_INGOT_SMELTABLES, RecipeCategory.MISC, CodexaModItems.PLATINUM_NUGGET.get(), 0.15f, 200, "platinum");
        oreBlasting(pWriter, PLATINUM_INGOT_SMELTABLES, RecipeCategory.MISC, CodexaModItems.PLATINUM_NUGGET.get(), 0.15f, 200, "platinum");
        genericSmelting(pWriter, Items.BAMBOO, RecipeCategory.MISC, CodexaModItems.BAMBOO_CHARCOAL.get(), 0.15f, 200, "fuels");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CodexaModBlocks.PLATINUM_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', CodexaModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(CodexaModItems.PLATINUM_INGOT.get()), has(CodexaModItems.PLATINUM_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, CodexaModItems.PLATINUM_INGOT.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', CodexaModItems.PLATINUM_NUGGET.get())
                .unlockedBy(getHasName(CodexaModItems.PLATINUM_NUGGET.get()), has(CodexaModItems.PLATINUM_NUGGET.get()))
                .save(pWriter, CODEXA.MOD_ID + ":platinum_ingot_from_nuggets");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CodexaModItems.PLATINUM_INGOT.get(), 9)
                .requires(CodexaModBlocks.PLATINUM_BLOCK.get())
                .unlockedBy(getHasName(CodexaModBlocks.PLATINUM_BLOCK.get()), has(CodexaModBlocks.PLATINUM_BLOCK.get()))
                .save(pWriter, CODEXA.MOD_ID + ":platinum_ingot_from_block");
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CodexaModItems.PLATINUM_NUGGET.get(), 9)
                .requires(CodexaModItems.PLATINUM_INGOT.get())
                .unlockedBy(getHasName(CodexaModItems.PLATINUM_INGOT.get()), has(CodexaModItems.PLATINUM_INGOT.get()))
                .save(pWriter, CODEXA.MOD_ID + ":platinum_nugget_from_ingot");
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void genericSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, Item pIngredient, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup)
    {
        genericCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredient, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients)
        {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                            pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer,  CODEXA.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }

    protected static void genericCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, Item pIngredient, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName)
    {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(pIngredient), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                .group(pGroup).unlockedBy(getHasName(pIngredient), has(pIngredient))
                .save(pFinishedRecipeConsumer,  CODEXA.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(pIngredient));
    }
}
