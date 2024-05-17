package de.artus.artusmod.ui.gui.lib.components.shapes;

import de.artus.artusmod.ui.gui.lib.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class CircleShape extends Drawable {

    @Accessors(chain = true)
    @Getter @Setter
    private Color backgroundColor = getTheme().getFallbackColor();

    @Accessors(chain = true)
    @Getter @Setter
    private int radius = 0;

    public CircleShape(int x, int y, int radius) {
        super(x, y, radius, radius);
        setRadius(radius);
    }


    @Override
    public void draw() {
        DrawHelper.drawCircle(getX(), getY(), getRadius(), getBackgroundColor());
    }

}
