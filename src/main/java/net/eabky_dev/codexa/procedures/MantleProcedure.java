package net.eabky_dev.codexa.procedures;

import com.sun.jna.platform.unix.X11;
import net.eabky_dev.codexa.init.CodexaModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.eabky_dev.codexa.item.MantleOfTheUniverse;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.event.CurioEquipEvent;

@Mod.EventBusSubscriber
public class MantleProcedure
{
    private static Player trackedPlayer;

//    @SubscribeEvent
//    public static void onMantleEquip(CurioEquipEvent event)
//    {
//        Player player = (Player) event.getEntity();
//        if (player != null && event.getStack().getItem() instanceof MantleOfTheUniverse)
//        {
//            trackedPlayer = player;
//
//            CuriosApi.getCuriosInventory(trackedPlayer).ifPresent(curiosInventory ->
//            {
//                curiosInventory.getStacksHandler("back").ifPresent(slotInventory ->
//                {
//                    for (int i = 0; i < slotInventory.getSlots(); i++) {
//                        ItemStack stack = slotInventory.getStacks().getStackInSlot(i);
//                        if (!stack.isEmpty() && stack.getItem() == CodexaModItems.MANTLE_OF_THE_UNIVERSE.get())
//                        {
//                            MantleOfTheUniverse.activateMantle(trackedPlayer);
//                        }
//                    }
//                });
//            });
//        }
//    }
}
