package de.artus.artusmod.ui.gui.lib.helpers;

import de.artus.artusmod.utils.render.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.List;

public class DrawHelper {


    public static void drawRect(double x, double y, double width, double height, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GlStateManager.color(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());

        worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x, y + height, 0.0).endVertex();
        worldRenderer.pos(x + width, y + height, 0.0).endVertex();
        worldRenderer.pos(x + width, y, 0.0).endVertex();
        worldRenderer.pos(x, y, 0.0).endVertex();
        tessellator.draw();

        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }


    public static void drawCircle(int x, int y, int radius, Color color) {
        drawFragmentCircle(x, y, 0, 360, radius, color);
    }

    public static void drawFragmentCircle(int x, int y, int startDeg, int endDeg, double radius, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        GlStateManager.color(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());

        worldRenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worldRenderer.pos(x + radius, y + radius, 0.0).endVertex();

        double dots = 360;
        for (double i = endDeg; i >= startDeg; i -= 360 / dots) {
            double angle = Math.toRadians(i - 90); // -90 because of the screen coordinates
            double xOff = Math.cos(angle) * radius;
            double yOff = Math.sin(angle) * radius;
            worldRenderer.pos(x + radius + xOff, y + radius + yOff, 0.0).endVertex();
        }



        tessellator.draw();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRoundedRect(int x, int y, int width, int height, double radius, Color color) {
        if (radius > (double) width / 2) {
            radius = (double) width / 2;
        }
        if (radius > (double) height / 2) {
            radius = (double) height / 2;
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
        drawFragmentCircle((int) (x + width - 2 * radius), y, 0, 90, radius, color);
        // bottom left corner
        drawFragmentCircle(x, (int) (y + height - 2 * radius), 180, 270, radius, color);
        // bottom right corner
        drawFragmentCircle((int) (x + width - 2 * radius), (int) (y + height - 2 * radius), 90, 180, radius, color);
    }




    public static void drawRectOutline(int x, int y, int width, int height, double thickness, Color color) {
        drawRect(x - thickness, y - thickness, width + 2 * thickness, thickness, color);
        drawRect(x - thickness, y + height, width + 2 * thickness, thickness, color);
        drawRect(x - thickness, y, thickness, height, color);
        drawRect(x + width, y, thickness, height, color);
    }

    public static void drawCircleOutline(int x, int y, int radius, int thickness, Color color) {
        drawFragmentCircleOutline(x, y, 0, 360, radius, thickness, color);
    }
    public static void drawFragmentCircleOutline(int x, int y, int startDeg, int endDeg, double radius, double thickness, Color color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GL11.glEnable(GL11.GL_POLYGON_SMOOTH);
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);


        GlStateManager.color(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());

        worldRenderer.begin(GL11.GL_TRIANGLE_STRIP, DefaultVertexFormats.POSITION);
        double dots = 360;
        for (double i = endDeg; i >= startDeg; i -= 360 / dots) {
            double angle = Math.toRadians(i - 90); // -90 because of the screen coordinates
            double xOffInner = Math.cos(angle) * radius;
            double yOffInner = Math.sin(angle) * radius;
            double xOffOuter = Math.cos(angle) * (radius + thickness);
            double yOffOuter = Math.sin(angle) * (radius + thickness);

            worldRenderer.pos(x + radius + xOffInner, y + radius + yOffInner, 0.0).endVertex();
            worldRenderer.pos(x + radius + xOffOuter, y + radius + yOffOuter, 0.0).endVertex();
        }
        tessellator.draw();


        GL11.glDisable(GL11.GL_POLYGON_SMOOTH);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }


    public static void drawRoundedRectOutline(int x, int y, int width, int height, double radius, double thickness, Color color) {
        if (radius > (double) width / 2) radius = (double) width / 2;
        if (radius > (double) height / 2) radius = (double) height / 2;


        // top left corner
        drawFragmentCircleOutline(x, y, 270, 360, radius, thickness, color);
        // top right corner
        drawFragmentCircleOutline((int) (x + width - 2 * radius), y, 0, 90, radius, thickness, color);
        // bottom left corner
        drawFragmentCircleOutline(x, (int) (y + height - 2 * radius), 180, 270, radius, thickness, color);
        // bottom right corner
        drawFragmentCircleOutline((int) (x + width - 2 * radius), (int) (y + height - 2 * radius), 90, 180, radius, thickness, color);

        // top rect
        drawRect(x + radius, y - thickness, width - 2 * radius, thickness, color);
        // bottom rect
        drawRect(x + radius, y + height, width - 2 * radius, thickness, color);
        // left rect
        drawRect(x - thickness, y + radius, thickness, height - 2 * radius, color);
        // right rect
        drawRect(x + width, y + radius, thickness, height - 2 * radius, color);
    }




    // - - - - - - - - - -   T E X T   - - - - - - - - - -

    public static void drawString(FontRenderer fr, String text, int x, int y, Color color) {
        fr.drawString(text, x, y, color.getInt());
    }
    public static void drawCenteredString(FontRenderer fr, String text, int x, int y, Color color) {
        drawString(fr, text, x - fr.getStringWidth(text) / 2, y, color);
    }
    public static void drawTextLinesWithPadding(FontRenderer fr, List<String> lines, int x, int y, int padding, Color color) {
        for (int i = 0; i < lines.size(); i++) {
            drawString(fr, lines.get(i), x, y + i * (fr.FONT_HEIGHT + padding), color);
        }
    }
    public static void drawCenteredTextLinesWithPadding(FontRenderer fr, List<String> lines, int x, int y, int padding, Color color) {
        for (int i = 0; i < lines.size(); i++) {
            drawCenteredString(fr, lines.get(i), x, y + i * (fr.FONT_HEIGHT + padding), color);
        }
    }
    public static void drawTextLines(FontRenderer fr, List<String> lines, int x, int y, Color color) {
        drawTextLinesWithPadding(fr, lines, x, y, 0, color);
    }
    public static void drawCenteredTextLines(FontRenderer fr, List<String> lines, int x, int y, Color color) {
        drawCenteredTextLinesWithPadding(fr, lines, x, y, 0, color);
    }

    public static void drawTextBlock(FontRenderer fr, String text, int linePadding, int x, int y, int width, int height, Color color) {
        List<String> lines = TextHelper.getLinesForBoxSize(fr, linePadding, text, width, height);
        drawTextLinesWithPadding(fr, lines, x, y, linePadding, color);
    }
    public static void drawCenteredTextBlock(FontRenderer fr, String text, int linePadding, int x, int y, int width, int height, Color color) {
        List<String> lines = TextHelper.getLinesForBoxSize(fr, linePadding, text, width, height);
        drawCenteredTextLinesWithPadding(fr, lines, x + width / 2, y, linePadding, color);
    }

    // - - - - - - - - - - - - - - - - - - - - - - - - - -




    public static void drawImage(ResourceLocation rl, int x, int y, int width, int height, int imgWidth, int imgHeight) {
        GlStateManager.disableAlpha();
        Minecraft.getMinecraft().getTextureManager().bindTexture(rl);
        Gui.drawScaledCustomSizeModalRect(x, y, 0, 0, imgWidth, imgHeight, width, height, imgWidth, imgHeight);
        GlStateManager.enableAlpha();
    }
    



}
