package de.artus.artusmod.ui.gui.lib.interactive;

import de.artus.artusmod.ui.gui.lib.Hoverable;
import de.artus.artusmod.ui.gui.lib.shapes.CircleShape;
import de.artus.artusmod.utils.ColorUtil;


public class HoverableCircle extends CircleShape implements Hoverable {


    public HoverableCircle(int x, int y, int radius) {
        super(x, y, radius);
    }


    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return distance(mouseX, mouseY, getX() + getRadius(), getY() + getRadius()) <= getRadius();
    }

    @Override
    public void onHovered(int mouseX, int mouseY) {
        setCurrentBackgroundColor(ColorUtil.calculateHoverBackgroundColor(getBackgroundColor()));
    }

    @Override
    public void whileNotHovered() {
        setCurrentBackgroundColor(getBackgroundColor());
    }
}
