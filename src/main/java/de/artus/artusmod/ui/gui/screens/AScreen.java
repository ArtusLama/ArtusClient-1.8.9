package de.artus.artusmod.ui.gui.screens;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.GuiConfiguration;
import de.artus.artusmod.ui.gui.lib.Clickable;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.Hoverable;
import de.artus.artusmod.ui.gui.lib.ScrollDetection;
import de.artus.artusmod.utils.MouseHelper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AScreen extends GuiScreen {

    @Getter
    private List<Drawable> drawables = new ArrayList<>();


    @Override
    public void initGui() {
        getDrawables().clear();
        init();
    }

    public abstract void init();



    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);

        for (Drawable drawable : getDrawables()) {
            if (drawable instanceof Hoverable) {
                Hoverable hoverable = (Hoverable) drawable;
                if (hoverable.isHovered(mouseX, mouseY)) {
                    hoverable.onHovered(mouseX, mouseY);
                } else hoverable.whileNotHovered();
            }
            drawable.draw(mouseX, mouseY);
        }
    }

    public GuiConfiguration getGuiConfiguration() {
        return ArtusMod.getGuiConfiguration();
    }


    @Getter @Setter
    private Clickable clickSelectedClickable = null;

    @Override
    protected void mouseClicked(int x, int y, int btn) {
        getDrawables().forEach(d -> {
            if (d instanceof Clickable) {
                Clickable clickable = (Clickable) d;
                if (clickable.isHovered(x, y)) {
                    setClickSelectedClickable(clickable);
                }
            }
        });
    }

    @Override
    protected void mouseReleased(int x, int y, int btn) {
        if (getClickSelectedClickable() != null) {
            if (getClickSelectedClickable().isHovered(x, y)) {
                getClickSelectedClickable().onClicked(x, y, btn);
                setClickSelectedClickable(null);
            }
        }
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int scroll = Mouse.getEventDWheel();
        if (scroll != 0) {
            int mouseX = MouseHelper.getMouseX();
            int mouseY = MouseHelper.getMouseY();
            for (Drawable drawable : getDrawables()) {
                if (drawable instanceof ScrollDetection) {
                    ScrollDetection scrollable = (ScrollDetection) drawable;
                    if (scrollable.isInScrollArea(mouseX, mouseY)) {
                        scrollable.onScroll(scroll);
                    }
                }
            }
        }
    }

}
