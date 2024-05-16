package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.GuiConfiguration;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.UiElement;
import de.artus.artusmod.ui.gui.lib.interfaces.Clickable;
import de.artus.artusmod.ui.gui.lib.interfaces.Scrollable;
import de.artus.artusmod.utils.mouse.MouseButton;
import de.artus.artusmod.utils.mouse.MouseHelper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AScreen extends GuiScreen {

    @Getter
    private List<UiElement> content = new ArrayList<>();

    @Getter @Setter
    private Clickable selectedClickable = null;


    @Override
    public void initGui() {
        getContent().clear();
        init();
    }
    public abstract void init();

    public GuiConfiguration getGuiConfiguration() {
        return ArtusMod.getGuiConfiguration();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);
        for (UiElement element : getContent()) {
            if (element instanceof Drawable) {
                ((Drawable) element).draw();
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int x = MouseHelper.getMouseX();
        int y = MouseHelper.getMouseY();
        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            for (UiElement element : getContent()) {
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
        for (UiElement element : getContent()) {
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
        for (UiElement element : getContent()) {
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
