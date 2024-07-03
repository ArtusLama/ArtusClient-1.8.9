package de.artus.artusmod.ui.gui.lib.components.interactable;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.helpers.HoverHelper;
import de.artus.artusmod.ui.gui.lib.components.shapes.RoundedRectShape;
import de.artus.artusmod.ui.gui.lib.interfaces.Clickable;
import de.artus.artusmod.utils.mouse.MouseButton;
import de.artus.artusmod.utils.render.Color;
import de.artus.artusmod.utils.render.ColorTransition;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

public class BasicButton extends RoundedRectShape implements Clickable {

    @Accessors(chain = true)
    @Getter @Setter
    private Color backgroundColor = getTheme().getBackground();

    @Getter @Setter
    private boolean currentlyHovered = false;

    @Accessors(chain = true)
    @Getter @Setter
    private Runnable onClick = () -> {};


    public BasicButton(int x, int y, int width, int height) {
        super(x, y, width, height, 4);
    }





    @Override
    public boolean isHovered(int mouseX, int mouseY) {
        return HoverHelper.isRoundedRectHovered(mouseX, mouseY, getX(), getY(), getWidth(), getHeight(), getCornerRadius());
    }

    @Override
    public void onMouseEnter() {
        if (getBackgroundColor() instanceof ColorTransition) ((ColorTransition) getBackgroundColor()).stop();
        setBackgroundColor(new ColorTransition(getTheme().getBackground(), getTheme().getBackgroundLight()).setDuration(150).start());
    }

    @Override
    public void onMouseLeave() {
        if (getBackgroundColor() instanceof ColorTransition) ((ColorTransition) getBackgroundColor()).stop();
        setBackgroundColor(new ColorTransition(getBackgroundColor(), getTheme().getBackground()).setDuration(150).start());
    }


    @Override
    public void onClick(MouseButton btn) {
        getOnClick().run();
    }
}
