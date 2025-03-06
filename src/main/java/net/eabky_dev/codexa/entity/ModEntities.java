package net.eabky_dev.codexa.entity;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.entity.custom.GemGolemEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CODEXA.MOD_ID);

    public static final RegistryObject<EntityType<GemGolemEntity>> GEM_GOLEM =
            ENTITY_TYPES.register("gem_golem", () -> EntityType.Builder.of(GemGolemEntity::new, MobCategory.MONSTER)
                    .sized(2.5f, 4f).build("gem_golem"));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
