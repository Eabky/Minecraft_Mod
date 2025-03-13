package net.eabky_dev.codexa.datagen.loot;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.eabky_dev.codexa.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider
{
    public ModGlobalLootModifiersProvider(PackOutput output)
    {
        super(output, CODEXA.MOD_ID);
    }

    @Override
    protected void start() {
        add("platinum_residue_from_copper_ore", new AddItemModifier(
                new LootItemCondition[]
                {
                    LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.COPPER_ORE).build(),
                    LootItemRandomChanceCondition.randomChance(0.35f).build()
                }, CodexaModItems.PLATINUM_RESIDUE.get()));

        //These are how to modifie the loot tables of entities and chests in structures:

//        add("pine_cone_from_creeper", new AddItemModifier(new LootItemCondition[] {
//                new LootTableIdCondition.Builder(ResourceLocation.parse("entities/creeper")).build() }, CodexaModItems.PINE_CONE.get()));

//        add("metal_detector_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
//                new LootTableIdCondition.Builder(ResourceLocation.parse("chests/jungle_temple")).build() }, CodexaModItems.METAL_DETECTOR.get()));


    }
}
