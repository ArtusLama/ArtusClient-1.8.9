package de.artus.artusmod.ui.gui.lib.components.interactable;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.utils.render.Color;
import de.artus.artusmod.utils.render.ColorTransition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


public class DefaultButton extends BasicButton {

    @Getter @Setter
    private double outlineThickness = 0.5;

    @Accessors(chain = true)
    @Getter @Setter
    private Color outlineColor = getTheme().getOutlineColor();


    public DefaultButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void draw() {
        super.draw();
        DrawHelper.drawRoundedRectOutline(getX(), getY(), getWidth(), getHeight(), getCornerRadius(), getOutlineThickness(), getOutlineColor());
    }

    @Override
    public void onMouseEnter() {
        super.onMouseEnter();
        if (getOutlineColor() instanceof ColorTransition) ((ColorTransition) getOutlineColor()).stop();
        setOutlineColor(new ColorTransition(getTheme().getOutlineColor(), getTheme().getOutlineColorLight()).setDuration(100).start());
    }

    @Override
    public void onMouseLeave() {
        super.onMouseLeave();
        if (getOutlineColor() instanceof ColorTransition) ((ColorTransition) getOutlineColor()).stop();
        setOutlineColor(new ColorTransition(getOutlineColor(), getTheme().getOutlineColor()).setDuration(100).start());
    }
}
