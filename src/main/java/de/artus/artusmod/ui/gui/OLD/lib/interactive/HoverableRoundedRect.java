package de.artus.artusmod.ui.gui.OLD.lib.interactive;

import de.artus.artusmod.ui.gui.OLD.lib.Hoverable;
import de.artus.artusmod.ui.gui.OLD.lib.shapes.RoundedRectShape;
import de.artus.artusmod.utils.render.ColorUtil;


public class HoverableRoundedRect extends RoundedRectShape implements Hoverable {


    public HoverableRoundedRect(int x, int y, int width, int height, int radius) {
        super(x, y, width, height, radius);
    }


    private double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return distance(mouseX, mouseY, getX() + getCornerRadius(), getY() + getCornerRadius()) <= getCornerRadius() ||
                distance(mouseX, mouseY, getX() + getWidth() - getCornerRadius(), getY() + getCornerRadius()) <= getCornerRadius() ||
                distance(mouseX, mouseY, getX() + getWidth() - getCornerRadius(), getY() + getHeight() - getCornerRadius()) <= getCornerRadius() ||
                distance(mouseX, mouseY, getX() + getCornerRadius(), getY() + getHeight() - getCornerRadius()) <= getCornerRadius() ||

                (mouseX >= getX() + getCornerRadius() && mouseX <= getX() + getWidth() - getCornerRadius() && mouseY >= getY() && mouseY <= getY() + getHeight()) ||
                (mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() + getCornerRadius() && mouseY <= getY() + getHeight() - getCornerRadius());
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
