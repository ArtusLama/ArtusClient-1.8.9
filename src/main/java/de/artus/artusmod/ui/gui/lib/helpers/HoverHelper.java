package de.artus.artusmod.ui.gui.lib.helpers;

public class HoverHelper {

    public static double distanceTo(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public static boolean isRoundedRectHovered(int mouseX, int mouseY, int x, int y, int width, int height, int cornerRadius) {
        return distanceTo(mouseX, mouseY, x + cornerRadius, y + cornerRadius) <= cornerRadius ||
                distanceTo(mouseX, mouseY, x + width - cornerRadius, y + cornerRadius) <= cornerRadius ||
                distanceTo(mouseX, mouseY, x + width - cornerRadius, y + height - cornerRadius) <= cornerRadius ||
                distanceTo(mouseX, mouseY, x + cornerRadius, y + height - cornerRadius) <= cornerRadius ||

                (mouseX >= x + cornerRadius && mouseX <= x + width - cornerRadius && mouseY >= y && mouseY <= y + height) ||
                (mouseX >= x && mouseX <= x + width && mouseY >= y + cornerRadius && mouseY <= y + height - cornerRadius);
    }

}
