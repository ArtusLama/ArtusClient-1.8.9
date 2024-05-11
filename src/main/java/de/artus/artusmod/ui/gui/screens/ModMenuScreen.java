package de.artus.artusmod.ui.gui.screens;


import de.artus.artusmod.ui.gui.lib.interactive.HoverableCircle;
import de.artus.artusmod.ui.gui.lib.interactive.HoverableRoundedRect;


public class ModMenuScreen extends AScreen {


    @Override
    public void initGui() {
        //getDrawables().add(new CircleShape(100, 100, 60).setBackgroundColor(getGuiConfiguration().getTheme().getPrimaryColor()));
        getDrawables().add(new HoverableRoundedRect(100, 100, 200, 100, 30));
        getDrawables().add(new HoverableCircle(60, 60, 40));

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float particalTicks) {
        super.drawScreen(mouseX, mouseY, particalTicks);
    }
}
