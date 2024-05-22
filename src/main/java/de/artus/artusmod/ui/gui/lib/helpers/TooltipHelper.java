package de.artus.artusmod.ui.gui.lib.helpers;

import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.opengl.Display;

public class TooltipHelper {

    @Getter @Setter
    private static FontRenderer tooltipFontRenderer = Minecraft.getMinecraft().fontRendererObj;

    @Getter @Setter
    private static Color tooltipTextColor = Color.of("#D2D2D2");
    @Getter @Setter
    private static Color tooltipBackgroundColor = Color.of("#1F1F1F").getWithAlpha(220);

    public static void drawTooltip(int x, int y, String text, FontRenderer fr) {
        // Offset to prevent the tooltip from being hidden by the cursor
        x += 5;

        int textWidth = fr.getStringWidth(text);
        int padding = 3;
        int width = textWidth + padding * 2;
        int height = fr.FONT_HEIGHT + padding * 2;
        int bgRadius = 4;

        int displayWidth = Minecraft.getMinecraft().currentScreen.width;
        if (x + width > displayWidth) x = displayWidth - width; // fix out of screen

        DrawHelper.drawRoundedRect(x, y, width, height, bgRadius, getTooltipBackgroundColor());
        DrawHelper.drawString(fr, text, x + padding, (int) (y + padding * 1.5), getTooltipTextColor());

    }
}
