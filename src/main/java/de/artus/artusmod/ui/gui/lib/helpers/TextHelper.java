package de.artus.artusmod.ui.gui.lib.helpers;

import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class TextHelper {


    public static List<String> getLinesWithMaxWidth(FontRenderer fr, String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (fr.getStringWidth(line + word) > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word + " ");
                continue;
            }
            line.append(word).append(" ");
        }
        lines.add(line.toString());
        return lines;
    }

    public static List<String> getLinesForBoxSize(FontRenderer fr, int linePadding, String text, int width, int height) {
        List<String> lines = getLinesWithMaxWidth(fr, text, width);

        // remove lines that would be out of height bounds. If some lines are removed, add "..." to the last line. If it then is bigger than the width, remove the last word and add "..."
        boolean wasRemoved = false;
        while (lines.size() * (fr.FONT_HEIGHT + linePadding) > height) {
            lines.remove(lines.size() - 1);
            wasRemoved = true;
        }
        if (!wasRemoved) return lines;

        if (lines.size() > 0) {
            String lastLine = lines.get(lines.size() - 1);
            lines.set(lines.size() - 1, getLineWithMaxWidth(fr, lastLine, width, true));
        }



        return lines;
    }

    public static String getLineWithMaxWidth(FontRenderer fr, String text, int maxWidth, boolean hasMoreContent) {
        if (fr.getStringWidth(text) <= maxWidth && !hasMoreContent) return text;
        while (fr.getStringWidth(text + "...") > maxWidth) {
            text = text.substring(0, text.length() - 1);
        }
        return text + "...";
    }


}
