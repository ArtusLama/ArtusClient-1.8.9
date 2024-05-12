package de.artus.artusmod.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class ScissorHelper {


    public static void startScissor(int x, int y, int width, int height) {
        int scaleFactor = new ScaledResolution(Minecraft.getMinecraft()).getScaleFactor();
        GL11.glScissor(x * scaleFactor, Minecraft.getMinecraft().displayHeight - (y + height) * scaleFactor, width * scaleFactor, height * scaleFactor);
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
    }
    public static void stopScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }

}
