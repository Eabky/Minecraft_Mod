package net.eabky_dev.codexa.init;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.entity.custom.GemSpikeEntity;
import net.eabky_dev.codexa.entity.custom.TomahawkProjectileEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;


public class CodexaModEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTRY;
    public static final RegistryObject<EntityType<GemGolemEntity>> GEM_GOLEM;
    public static final RegistryObject<EntityType<GemSpikeEntity>> GEM_SPIKE;
    public static final RegistryObject<EntityType<TomahawkProjectileEntity>> TOMAHAWK;

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder)
    {
        return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            GemGolemEntity.init();
            TomahawkProjectileEntity.init();
            GemSpikeEntity.init();
        });
    }

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CODEXA.MOD_ID);
        GEM_GOLEM = register("gem_golem", EntityType.Builder.of(GemGolemEntity::new, MobCategory.MONSTER).sized(2F, 3F).fireImmune());
        TOMAHAWK = register("tomahawk", EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC).sized(0.5f, 1.15f));
        GEM_SPIKE = register("gem_spike", EntityType.Builder.of(GemSpikeEntity::new, MobCategory.MISC).sized(1F, 4F).fireImmune());
    }
}
