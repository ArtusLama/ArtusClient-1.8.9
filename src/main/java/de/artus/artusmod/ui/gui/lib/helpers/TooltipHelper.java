package de.artus.artusmod.ui.gui.lib.helpers;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.Theme;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

public class TooltipHelper {


    public static FontRenderer getTooltipFontRenderer() {
        return ArtusMod.getGuiConfiguration().getTheme().getDefaultFontRenderer();
    }

    @Getter @Setter
    private static Color tooltipTextColor = Color.of("#D2D2D2");
    @Getter @Setter
    private static Color tooltipBackgroundColor = Color.of("#1A1A1A").getWithAlpha(220);

    public static void drawTooltip(int x, int y, String text, FontRenderer fr) {
        // Offset to prevent the tooltip from being hidden by the cursor
        x += 5;
        y += 5;

        int textWidth = fr.getStringWidth(text);
        int padding = 3;
        int width = textWidth + padding * 2;
        int height = fr.FONT_HEIGHT + padding * 2;
        int bgRadius = 2;

        int displayWidth = Minecraft.getMinecraft().currentScreen.width;
        if (x + width > displayWidth) x = displayWidth - width; // fix out of screen

        DrawHelper.drawRoundedRect(x, y, width, height, bgRadius, getTooltipBackgroundColor());
        DrawHelper.drawString(fr, text, x + padding, (int) (y + padding * 1.5), getTooltipTextColor());

    }
}
