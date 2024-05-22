package de.artus.artusmod.ui.gui.lib.helpers;

import de.artus.artusmod.utils.render.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.util.List;

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
        if (radius > width / 2) {
            radius = width / 2;
        }
        if (radius > height / 2) {
            radius = height / 2;
        }

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

    public static void drawString(FontRenderer fr, String text, int x, int y, Color color) {
        fr.drawString(text, x, y, color.getInt());
    }
    public static void drawCenteredString(FontRenderer fr, String text, int x, int y, Color color) {
        drawString(fr, text, x - fr.getStringWidth(text) / 2, y, color);
    }
    public static void drawLinesWithPadding(FontRenderer fr, List<String> lines, int x, int y, int padding, Color color) {
        for (int i = 0; i < lines.size(); i++) {
            drawString(fr, lines.get(i), x, y + i * (fr.FONT_HEIGHT + padding), color);
        }
    }
    public static void drawCenteredLinesWithPadding(FontRenderer fr, List<String> lines, int x, int y, int padding, Color color) {
        for (int i = 0; i < lines.size(); i++) {
            drawCenteredString(fr, lines.get(i), x, y + i * (fr.FONT_HEIGHT + padding), color);
        }
    }
    public static void drawLines(FontRenderer fr, List<String> lines, int x, int y, Color color) {
        drawLinesWithPadding(fr, lines, x, y, 0, color);
    }
    public static void drawCenteredLines(FontRenderer fr, List<String> lines, int x, int y, Color color) {
        drawCenteredLinesWithPadding(fr, lines, x, y, 0, color);
    }

    public static void drawTextBlock(FontRenderer fr, String text, int linePadding, int x, int y, int width, int height, Color color) {
        List<String> lines = TextHelper.getLinesForBoxSize(fr, linePadding, text, width, height);
        drawLinesWithPadding(fr, lines, x, y, linePadding, color);
    }
    public static void drawCenteredTextBlock(FontRenderer fr, String text, int linePadding, int x, int y, int width, int height, Color color) {
        List<String> lines = TextHelper.getLinesForBoxSize(fr, linePadding, text, width, height);
        drawCenteredLinesWithPadding(fr, lines, x + width / 2, y, linePadding, color);
    }


    



}
