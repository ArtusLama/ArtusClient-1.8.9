package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.GuiConfiguration;
import de.artus.artusmod.ui.gui.lib.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.UiElement;
import de.artus.artusmod.ui.gui.lib.interfaces.Clickable;
import de.artus.artusmod.ui.gui.lib.interfaces.Hoverable;
import de.artus.artusmod.ui.gui.lib.interfaces.Scrollable;
import de.artus.artusmod.utils.mouse.CursorHelper;
import de.artus.artusmod.utils.mouse.MouseButton;
import de.artus.artusmod.utils.mouse.MouseHelper;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.nio.IntBuffer;
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

                        CursorHelper.useHandCursor();
                    }
                } else {
                    if (hoverable.isCurrentlyHovered()) {
                        hoverable.setCurrentlyHovered(false);
                        hoverable.onMouseLeave();

                        CursorHelper.useDefaultCursor();
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
