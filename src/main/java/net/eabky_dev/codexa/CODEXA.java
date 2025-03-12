package net.eabky_dev.codexa;

import com.mojang.logging.LogUtils;
import net.eabky_dev.codexa.block.ModBlocks;
import net.eabky_dev.codexa.entity.CodexaEntities;
import net.eabky_dev.codexa.item.ModCreativeModTabs;
import net.eabky_dev.codexa.item.ModItems;
import net.eabky_dev.codexa.loot.ModLootModifiers;
import net.eabky_dev.codexa.sound.ModSounds;
import net.eabky_dev.codexa.worldgen.biome.surface.ModSurfaceRules;
import net.eabky_dev.codexa.worldgen.tree.ModFoliagePlacers;
import net.eabky_dev.codexa.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.util.thread.SidedThreadGroups;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(net.eabky_dev.codexa.CODEXA.MOD_ID)
public class CODEXA
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "codexa";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel PACKET_HANDLER = NetworkRegistry.newSimpleChannel(ResourceLocation.fromNamespaceAndPath("codexa", "codexa"), () -> "1", "1"::equals, "1"::equals);
    private static int messageID = 0;
    private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue();

    public CODEXA(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.register(modEventBus);
        CodexaEntities.REGISTRY.register(modEventBus);
        ModSounds.register(modEventBus);
        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.PALE_FORTUNE.getId(), ModBlocks.POTTED_PALE_FORTUNE);
        });

        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

//    public static <T> void addNetworkMessage(Class<T> messageType, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Supplier<NetworkEvent.Context>> messageConsumer) {
//        PACKET_HANDLER.registerMessage(messageID, messageType, encoder, decoder, messageConsumer);
//        ++messageID;
//    }

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
