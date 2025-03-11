package net.eabky_dev.codexa.datagen.loot;

import net.eabky_dev.codexa.block.ModBlocks;
import net.eabky_dev.codexa.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PLATINUM_BLOCK.get());
        this.dropSelf(ModBlocks.DEEPSLATE_PLATINUM_ORE.get());
        this.dropSelf(ModBlocks.BLACK_HOLE.get());
        this.dropSelf(ModBlocks.CONCRETE.get());
        this.dropSelf(ModBlocks.POLISHED_CONCRETE.get());
        this.dropSelf(ModBlocks.CONCRETE_STRAIRS.get());
        this.dropSelf(ModBlocks.POLISHED_CONCRETE_STRAIRS.get());
        this.dropSelf(ModBlocks.CONCRETE_WALL.get());

        this.add(ModBlocks.CONCRETE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.CONCRETE_SLAB.get()));
        this.add(ModBlocks.POLISHED_CONCRETE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.POLISHED_CONCRETE_SLAB.get()));

        this.add(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_PLATINUM_ORE.get(), ModItems.RAW_PLATINUM.get()));

        this.dropSelf(ModBlocks.PALE_FORTUNE.get());
        this.add(ModBlocks.POTTED_PALE_FORTUNE.get(), createPotFlowerItemTable(ModBlocks.PALE_FORTUNE.get()));

        this.dropSelf(ModBlocks.SUNELM_LOG.get());
        this.dropSelf(ModBlocks.SUNELM_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_SUNELM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_SUNELM_WOOD.get());
        this.dropSelf(ModBlocks.SUNELM_PLANKS.get());
        this.dropSelf(ModBlocks.SUNELM_SAPLING.get());

        this.add(ModBlocks.SUNELM_LEAVES.get(), block ->
                createLeavesDrops(block, ModBlocks.SUNELM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.MIDNIGHT_GRASS_BLOCK.get());
        this.dropSelf(ModBlocks.MIDNIGHT_DIRT.get());
        this.dropSelf(ModBlocks.MIDNIGHT_STONE.get());
        this.dropSelf(ModBlocks.MIDNIGHT_GRASS.get());
    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
