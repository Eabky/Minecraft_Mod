package net.eabky_dev.codexa;

import com.mojang.logging.LogUtils;
import net.eabky_dev.codexa.init.*;
import net.eabky_dev.codexa.loot.ModLootModifiers;
import net.eabky_dev.codexa.procedures.BrewingPotionsProcedure;
import net.eabky_dev.codexa.procedures.FlowerPotProcedure;
import net.eabky_dev.codexa.sound.ModSounds;
import net.eabky_dev.codexa.util.CodexaBrewingRecipe;
import net.eabky_dev.codexa.worldgen.biome.surface.ModSurfaceRules;
import net.eabky_dev.codexa.worldgen.tree.ModFoliagePlacers;
import net.eabky_dev.codexa.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;


@Mod(net.eabky_dev.codexa.CODEXA.MOD_ID)
public class CODEXA
{
    public static final String MOD_ID = "codexa";
    private static final Logger LOGGER = LogUtils.getLogger();
//    private static final String PROTOCOL_VERSION = "1";
//    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath("codexa", "codexa"), () -> "1", "1"::equals, "1"::equals);
//    private static int messageID = 0;
//    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue();

    public CODEXA(FMLJavaModLoadingContext context)
    {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modEventBus = context.getModEventBus();

        CodexaModCreativeTabs.register(modEventBus);
        CodexaModItems.register(modEventBus);
        CodexaModBlocks.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        CodexaModEntities.REGISTRY.register(modEventBus);
        CodexaModMobEffects.REGISTRY.register(modEventBus);
        CodexaModPotions.REGISTRY.register(modEventBus);
        CodexaModEnchantments.REGISTRY.register(modEventBus);
        ModSounds.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        modEventBus.addListener(FlowerPotProcedure::flowerPot);
        modEventBus.addListener(BrewingPotionsProcedure::potionBrewing);
    }

//    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
//        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
//        ++messageID;
//    }
//
//    public static void queueServerWork(int tick, Runnable action)
//    {
//        if (Thread.currentThread().getThreadGroup() == SidedThreadGroups.SERVER)
//        {
//            workQueue.add(new AbstractMap.SimpleEntry(action, tick));
//        }
//
//    }
//
//    @SubscribeEvent
//    public void tick(TickEvent.ServerTickEvent event)
//    {
//        if (event.phase == TickEvent.Phase.END)
//        {
//            List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList();
//            workQueue.forEach((work) ->
//            {
//                work.setValue((Integer)work.getValue() - 1);
//                if ((Integer)work.getValue() == 0) {
//                    actions.add(work);
//                }
//
//            });
//
//            actions.forEach((e) -> ((Runnable)e.getKey()).run());
//            workQueue.removeAll(actions);
//        }
//
//    }
}
