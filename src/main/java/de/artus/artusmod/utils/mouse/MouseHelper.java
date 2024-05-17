package de.artus.artusmod.utils.mouse;


import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

import java.awt.image.BufferedImage;
import java.nio.IntBuffer;

public class MouseHelper {

    private static Minecraft getMC() {
        return Minecraft.getMinecraft();
    }

    public static int getMouseX() {
        return Mouse.getEventX() * getMC().currentScreen.width / getMC().displayWidth;
    }
    public static int getMouseY() {
        return getMC().currentScreen.height - Mouse.getEventY() * getMC().currentScreen.height / getMC().displayHeight - 1;
    }

}
