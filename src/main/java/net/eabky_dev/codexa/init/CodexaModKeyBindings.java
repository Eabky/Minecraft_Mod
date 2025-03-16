package net.eabky_dev.codexa.init;

import com.mojang.blaze3d.platform.InputConstants;
import cpw.mods.util.Lazy;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;

import org.lwjgl.glfw.GLFW;

public class CodexaModKeyBindings
{
    public static final Lazy<KeyMapping> TOGGLE_MANTLE_KEY = Lazy.of(() -> new KeyMapping("key.codexa.toggle_mantle", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, "key.categories.codexa"));

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event)
    {
        event.register(TOGGLE_MANTLE_KEY.get());
    }
}
