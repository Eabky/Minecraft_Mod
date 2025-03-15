package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.OtherworldlyLanternBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaModBlockEntities
{
    public static final DeferredRegister<BlockEntityType<?>> REGISTRY;
    public static final RegistryObject<BlockEntityType<OtherworldlyLanternBlockEntity>> OTHERWORLDLY_LANTERN_BE;

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, CODEXA.MOD_ID);
        OTHERWORLDLY_LANTERN_BE = REGISTRY.register("otherworldly_lantern_entity", () -> BlockEntityType.Builder.of(OtherworldlyLanternBlockEntity::new, CodexaModBlocks.OTHERWORLDLY_LANTERN.get()).build(null));

    }
}
