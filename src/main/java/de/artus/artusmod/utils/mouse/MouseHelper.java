package de.artus.artusmod.utils.mouse;


import lombok.Getter;
import net.minecraft.client.Minecraft;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
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

    @Getter
    public static final Cursor defaultCursor = Mouse.getNativeCursor();
    public static void setCursor(Cursor cursor) {
        try {
            Mouse.setNativeCursor(cursor);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

}
