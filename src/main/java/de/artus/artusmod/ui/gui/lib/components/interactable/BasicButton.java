package de.artus.artusmod.ui.gui.lib.components.interactable;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.helpers.HoverHelper;
import de.artus.artusmod.ui.gui.lib.components.shapes.RoundedRectShape;
import de.artus.artusmod.ui.gui.lib.interfaces.Clickable;
import de.artus.artusmod.ui.gui.lib.interfaces.Tooltip;
import de.artus.artusmod.utils.mouse.MouseButton;
import de.artus.artusmod.utils.render.Color;
import de.artus.artusmod.utils.render.ColorTransition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class BasicButton extends RoundedRectShape implements Clickable, Tooltip {

    @Accessors(chain = true)
    @Getter @Setter
    private Color backgroundColor = getTheme().getBackground();

    @Getter @Setter
    private boolean currentlyHovered = false;

    // TODO: just for testing
    long clickCount = 1;

    public BasicButton(int x, int y, int width, int height) {
        super(x, y, width, height, 4);
    }
    @Override
    public String getTooltipText() {
        return "This is a button! Click Count: " + clickCount;
    }

    @Override
    public void draw() {
        DrawHelper.drawRoundedRect(getX(), getY(), getWidth(), getHeight(), getCornerRadius(), getBackgroundColor());
    }

    @Override
    public void onClick(MouseButton btn) {
        clickCount *= 2;
    }


    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return HoverHelper.isRoundedRectHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight(), getCornerRadius());
    }

    @Override
    public void onMouseEnter() {
        if (getBackgroundColor() instanceof ColorTransition) ((ColorTransition) getBackgroundColor()).stop();
        setBackgroundColor(new ColorTransition(getTheme().getBackground(), getTheme().getBackgroundLight()).setDuration(200).start());
    }

    @Override
    public void onMouseLeave() {
        if (getBackgroundColor() instanceof ColorTransition) ((ColorTransition) getBackgroundColor()).stop();
        setBackgroundColor(new ColorTransition(getBackgroundColor(), getTheme().getBackground()).setDuration(200).start());
    }


}
