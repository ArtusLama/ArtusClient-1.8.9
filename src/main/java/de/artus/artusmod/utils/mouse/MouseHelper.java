package de.artus.artusmod.utils.mouse;


import net.minecraft.client.Minecraft;
import org.lwjgl.input.Mouse;

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
