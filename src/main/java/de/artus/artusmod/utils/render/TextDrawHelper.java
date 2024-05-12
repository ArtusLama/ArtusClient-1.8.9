package de.artus.artusmod.utils.render;

import net.minecraft.client.gui.FontRenderer;

import java.util.ArrayList;
import java.util.List;

public class TextDrawHelper {



    public static List<String> lines(FontRenderer fr, String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        for (String word : words) {
            if (fr.getStringWidth(line + word) > maxWidth) {
                lines.add(line.toString());
                line = new StringBuilder(word + " ");
            }
            line.append(word).append(" ");
        }
        lines.add(line.toString());
        return lines;
    }


}
