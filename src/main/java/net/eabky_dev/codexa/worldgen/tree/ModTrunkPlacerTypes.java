package net.eabky_dev.codexa.worldgen.tree;

import net.eabky_dev.codexa.CODEXA;
import net.eabky_dev.codexa.worldgen.tree.custom.SunelmTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTrunkPlacerTypes
{
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, CODEXA.MOD_ID);

    public static final RegistryObject<TrunkPlacerType<SunelmTrunkPlacer>> SUNELM_TRUNK_PLACER = TRUNK_PLACER.register("sunelm_trunk_placer", ()-> new TrunkPlacerType<>(SunelmTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus)
    {
        TRUNK_PLACER.register(eventBus);
    }
}
