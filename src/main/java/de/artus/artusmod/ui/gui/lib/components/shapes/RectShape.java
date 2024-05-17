package de.artus.artusmod.ui.gui.lib.components.shapes;


import de.artus.artusmod.ui.gui.lib.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class RectShape extends Drawable {

    @Accessors(chain = true)
    @Getter @Setter
    private Color backgroundColor = getTheme().getFallbackColor();

    public RectShape(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw() {
        DrawHelper.drawRect(getX(), getY(), getWidth(), getHeight(), getBackgroundColor());
    }

}
