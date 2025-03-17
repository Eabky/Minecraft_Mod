package net.eabky_dev.codexa.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class CodexaKeyBindings
{
    public static final String KEY_CATEGORY_CODEXA = "key.category.codexa.codexa";
    public static final String KEY_MANTLE = "key.codexa.mantle";

    public static final KeyMapping MANTLE = new KeyMapping(KEY_MANTLE, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_CODEXA);
}

