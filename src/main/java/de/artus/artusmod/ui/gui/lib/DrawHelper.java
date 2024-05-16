package de.artus.artusmod.ui.gui.lib;

import de.artus.artusmod.utils.render.Color;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class DrawHelper {


    public static void drawRect(int x, int y, int width, int height, Color color) {
        Gui.drawRect(x, y, x + width, y + height, color.getInt());
    }


    public static void drawCircle(int x, int y, int radius, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GlStateManager.color(color.getR(), color.getG(), color.getB(), color.getA());

        worldRenderer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);

        int dots = 360;
        for (int i = 0; i < 360; i += 360 / dots) {
            int deg = 360 - i;
            double xOff = Math.cos(Math.toRadians(deg)) * radius;
            double yOff = Math.sin(Math.toRadians(deg)) * radius;
            worldRenderer.pos(x + radius + xOff, y + radius + yOff, 0.0).endVertex();
        }



        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawFragmentCircle(int x, int y, int startDeg, int endDeg, int radius, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GlStateManager.color(color.getR(), color.getG(), color.getB(), color.getA());

        worldRenderer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);

        int dots = 360;
        for (int i = endDeg; i >= startDeg; i -= 360 / dots) {
            double angle = Math.toRadians(i - 90); // -90 because of the screen coordinates
            double xOff = Math.cos(angle) * radius;
            double yOff = Math.sin(angle) * radius;
            worldRenderer.pos(x + radius + xOff, y + radius + yOff, 0.0).endVertex();
        }



        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }


}
