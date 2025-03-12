package net.eabky_dev.codexa.entity;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.eabky_dev.codexa.entity.custom.TomahawkProjectileEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CodexaEntities
{
    public static final DeferredRegister<EntityType<?>> REGISTRY;
    public static final RegistryObject<EntityType<GemGolemEntity>> GEM_GOLEM;
    public static final RegistryObject<EntityType<TomahawkProjectileEntity>> TOMAHAWK;

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            GemGolemEntity.init();
            TomahawkProjectileEntity.init();
        });
    }

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder)
    {
        return REGISTRY.register(registryname, () -> entityTypeBuilder.build(registryname));
    }

    static
    {
        REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CODEXA.MOD_ID);
        GEM_GOLEM = REGISTRY.register("gem_golem", () -> EntityType.Builder.of(GemGolemEntity::new, MobCategory.MONSTER).sized(2.5f, 4f).build("gem_golem"));
        TOMAHAWK = REGISTRY.register("tomahawk", () -> EntityType.Builder.<TomahawkProjectileEntity>of(TomahawkProjectileEntity::new, MobCategory.MISC).sized(0.5f, 1.15f).build("tomahawk"));
    }
}
