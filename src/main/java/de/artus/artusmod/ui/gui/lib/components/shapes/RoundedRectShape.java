package de.artus.artusmod.ui.gui.lib.components.shapes;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class RoundedRectShape extends Drawable {


    @Accessors(chain = true)
    @Getter @Setter
    private Color backgroundColor = getTheme().getFallbackColor();

    @Accessors(chain = true)
    @Getter @Setter
    private int cornerRadius = 0;

    public RoundedRectShape(int x, int y, int width, int height, int cornerRadius) {
        super(x, y, width, height);
        setCornerRadius(cornerRadius);
        if (getCornerRadius() > getWidth() / 2) {
            setCornerRadius(getWidth() / 2);
        }
        if (getCornerRadius() > getHeight() / 2) {
            setCornerRadius(getHeight() / 2);
        }
    }

    @Override
    public void draw() {
        DrawHelper.drawRoundedRect(getX(), getY(), getWidth(), getHeight(), getCornerRadius(), getBackgroundColor());
    }
}
