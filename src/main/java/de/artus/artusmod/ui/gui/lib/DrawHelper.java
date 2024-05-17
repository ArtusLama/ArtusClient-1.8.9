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

        GlStateManager.color(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());

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

        GlStateManager.color(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());

        worldRenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x + radius, y + radius, 0.0).endVertex();

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

    public static void drawRoundedRect(int x, int y, int width, int height, int radius, Color color) {
        // main rect
        drawRect(x, y + radius, width, height - 2 * radius, color);
        // top rect
        drawRect(x + radius, y, width - 2 * radius, radius, color);
        // bottom rect
        drawRect(x + radius, y + height - radius, width - 2 * radius, radius, color);

        // top left corner
        drawFragmentCircle(x, y, 270, 360, radius, color);
        // top right corner
        drawFragmentCircle(x + width - 2 * radius, y, 0, 90, radius, color);
        // bottom left corner
        drawFragmentCircle(x, y + height - 2 * radius, 180, 270, radius, color);
        // bottom right corner
        drawFragmentCircle(x + width - 2 * radius, y + height - 2 * radius, 90, 180, radius, color);

    }
    



}
