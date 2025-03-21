package net.eabky_dev.codexa.datagen.loot;

import net.eabky_dev.codexa.init.CodexaModBlocks;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(CodexaModBlocks.PLATINUM_BLOCK.get());
        this.dropSelf(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get());
        this.dropSelf(CodexaModBlocks.BLACK_HOLE.get());
        this.dropSelf(CodexaModBlocks.CONCRETE.get());
        this.dropSelf(CodexaModBlocks.POLISHED_CONCRETE.get());
        this.dropSelf(CodexaModBlocks.CONCRETE_STRAIRS.get());
        this.dropSelf(CodexaModBlocks.POLISHED_CONCRETE_STRAIRS.get());
        this.dropSelf(CodexaModBlocks.CONCRETE_WALL.get());
        this.dropSelf(CodexaModBlocks.ALABASTER.get());
        this.dropSelf(CodexaModBlocks.ALABASTER_BRICKS.get());
        this.dropSelf(CodexaModBlocks.ALABASTER_PILLAR.get());
        this.dropSelf(CodexaModBlocks.ALABASTER_STAIRS.get());
        this.add(CodexaModBlocks.ALABASTER_SLAB.get(), block -> createSlabItemTable(CodexaModBlocks.ALABASTER_SLAB.get()));
        this.dropSelf(CodexaModBlocks.ALABASTER_WALL.get());
        this.dropSelf(CodexaModBlocks.ALABASTER_BRICK_STAIRS.get());
        this.add(CodexaModBlocks.ALABASTER_BRICK_SLAB.get(), block -> createSlabItemTable(CodexaModBlocks.ALABASTER_BRICK_SLAB.get()));
        this.dropSelf(CodexaModBlocks.ALABASTER_BRICK_WALL.get());
        this.dropSelf(CodexaModBlocks.CARVED_ALABASTER.get());
        this.add(CodexaModBlocks.CONCRETE_SLAB.get(), block -> createSlabItemTable(CodexaModBlocks.CONCRETE_SLAB.get()));
        this.add(CodexaModBlocks.POLISHED_CONCRETE_SLAB.get(), block -> createSlabItemTable(CodexaModBlocks.POLISHED_CONCRETE_SLAB.get()));
        this.add(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get(), block -> createCopperLikeOreDrops(CodexaModBlocks.DEEPSLATE_PLATINUM_ORE.get(), CodexaModItems.RAW_PLATINUM.get()));
        this.dropSelf(CodexaModBlocks.PALE_FORTUNE.get());
        this.add(CodexaModBlocks.POTTED_PALE_FORTUNE.get(), createPotFlowerItemTable(CodexaModBlocks.PALE_FORTUNE.get()));
        this.dropSelf(CodexaModBlocks.SUNELM_LOG.get());
        this.dropSelf(CodexaModBlocks.SUNELM_WOOD.get());
        this.dropSelf(CodexaModBlocks.STRIPPED_SUNELM_LOG.get());
        this.dropSelf(CodexaModBlocks.STRIPPED_SUNELM_WOOD.get());
        this.dropSelf(CodexaModBlocks.SUNELM_PLANKS.get());
        this.dropSelf(CodexaModBlocks.SUNELM_SAPLING.get());
        this.add(CodexaModBlocks.SUNELM_LEAVES.get(), block -> createLeavesDrops(block, CodexaModBlocks.SUNELM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.dropSelf(CodexaModBlocks.MIDNIGHT_GRASS_BLOCK.get());
        this.dropSelf(CodexaModBlocks.MIDNIGHT_DIRT.get());
        this.dropSelf(CodexaModBlocks.MIDNIGHT_STONE.get());
        this.dropSelf(CodexaModBlocks.MIDNIGHT_GRASS.get());
        this.dropSelf(CodexaModBlocks.OTHERWORLDLY_LANTERN.get());
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
        return CodexaModBlocks.REGISTRY.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
