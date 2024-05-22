package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.GuiConfiguration;
import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.UiElement;
import de.artus.artusmod.ui.gui.lib.interfaces.Clickable;
import de.artus.artusmod.ui.gui.lib.interfaces.Hoverable;
import de.artus.artusmod.ui.gui.lib.interfaces.Scrollable;
import de.artus.artusmod.ui.gui.lib.interfaces.Tooltip;
import de.artus.artusmod.utils.mouse.CursorHelper;
import de.artus.artusmod.utils.mouse.MouseButton;
import de.artus.artusmod.utils.mouse.MouseHelper;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AScreen extends GuiScreen {

    @Getter
    private List<UiElement> elements = new ArrayList<>();

    @Getter @Setter
    private Clickable selectedClickable = null;


    @Override
    public void initGui() {
        getElements().clear();
        init();
    }
    public abstract void init();

    public GuiConfiguration getGuiConfiguration() {
        return ArtusMod.getGuiConfiguration();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);
        DrawHelper.drawRect(0, 0, width, height, Color.of("#000000"));
        for (UiElement element : getElements()) {
            if (element instanceof Drawable) {
                ((Drawable) element).draw();
            }
        }

        checkForTooltips();
    }

    public void checkForTooltips() {
        int mouseX = MouseHelper.getMouseX();
        int mouseY = MouseHelper.getMouseY();

        for (UiElement element : getElements()) {
            if (element instanceof Tooltip) {
                Tooltip tooltip = (Tooltip) element;
                if (tooltip.isHovered(mouseX, mouseY)) {
                    tooltip.displayTooltip(mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int x = MouseHelper.getMouseX();
        int y = MouseHelper.getMouseY();

        for (UiElement element : getElements()) {
            if (element instanceof Hoverable) {
                Hoverable hoverable = (Hoverable) element;
                if (hoverable.isHovered(x, y)) {
                    if (!hoverable.isCurrentlyHovered()) {
                        hoverable.setCurrentlyHovered(true);
                        hoverable.onMouseEnter();

                        CursorHelper.useHandCursor(); // TODO just temporary
                    }
                } else {
                    if (hoverable.isCurrentlyHovered()) {
                        hoverable.setCurrentlyHovered(false);
                        hoverable.onMouseLeave();

                        CursorHelper.useDefaultCursor(); // TODO just temporary
                    }
                }
            }
        }


        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            for (UiElement element : getElements()) {
                if (element instanceof Scrollable) {
                    Scrollable scrollable = (Scrollable) element;
                    if (scrollable.isInScrollableArea(x, y)) {
                        scrollable.onScroll(scroll);
                    }
                }
            }
        }
    }



    @Override
    protected void mouseClicked(int mouseX, int mouseY, int btn) {
        setSelectedClickable(null);
        for (UiElement element : getElements()) {
            if (element instanceof Clickable) {
                Clickable clickable = (Clickable) element;
                if (clickable.isHovered(mouseX, mouseY)) {
                    clickable.onMouseDown(MouseButton.of(btn));
                    setSelectedClickable(clickable);
                }
            }
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int btn) {
        for (UiElement element : getElements()) {
            if (element instanceof Clickable) {
                Clickable clickable = (Clickable) element;
                if (clickable.isHovered(mouseX, mouseY)) {
                    clickable.onMouseUp(MouseButton.of(btn));
                    if (getSelectedClickable() == clickable) {
                        clickable.onClick(MouseButton.of(btn));
                    }
                }
            }
        }
    }
}
