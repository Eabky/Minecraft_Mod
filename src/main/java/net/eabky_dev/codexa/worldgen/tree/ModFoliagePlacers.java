package net.eabky_dev.codexa.worldgen.tree;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.worldgen.tree.custom.SunelmFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers
{
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, CODEXA.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<SunelmFoliagePlacer>> SUNELM_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("sunelm_foliage_placer", () -> new FoliagePlacerType<>(SunelmFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus)
    {
        FOLIAGE_PLACERS.register(eventBus);
    }
}
