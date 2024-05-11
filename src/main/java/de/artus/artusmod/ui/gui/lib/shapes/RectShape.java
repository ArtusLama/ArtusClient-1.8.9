package de.artus.artusmod.ui.gui.lib.shapes;

import de.artus.artusmod.ui.gui.lib.BackgroundDrawable;

public class RectShape extends BackgroundDrawable {


    public RectShape(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void draw(int mouseX, int mouseY) {
        drawRect(getX(), getY(), getX() + getWidth(), getY() + getHeight(), getCurrentBackgroundColor());
    }

}
