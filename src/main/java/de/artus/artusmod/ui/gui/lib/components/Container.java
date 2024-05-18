package de.artus.artusmod.ui.gui.lib.components;

import de.artus.artusmod.ui.gui.lib.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.UiElement;
import de.artus.artusmod.ui.gui.lib.interfaces.Hoverable;
import de.artus.artusmod.utils.render.ScissorHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Container extends Drawable {

    @Getter @Setter
    private boolean clipContent = true;

    @Getter @Setter
    private List<UiElement> items = new ArrayList<>();

    public Container(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void draw() {
        // TODO: for testing
        DrawHelper.drawRect(getX(), getY(), getWidth(), getHeight(), getTheme().getBackground());
        if (isClipContent()) ScissorHelper.startScissor(getX(), getY(), getWidth(), getHeight());
        for (UiElement item : getItems()) {
            if (item instanceof Drawable) {
                ((Drawable) item).draw();
            }
        }
        if (isClipContent()) ScissorHelper.stopScissor();
    }

    public boolean isItemHovered(UiElement item, int mouseX, int mouseY) {
        if (item instanceof Hoverable) {
            Hoverable hoverable = (Hoverable) item;
            return hoverable.isHovered(mouseX, mouseY) && isMouseOver(mouseX, mouseY);
        }
        return false;
    }

    private boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= getX() && mouseX <= getX() + getWidth() && mouseY >= getY() && mouseY <= getY() + getHeight();
    }
}
