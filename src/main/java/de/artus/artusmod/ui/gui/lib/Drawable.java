package de.artus.artusmod.ui.gui.lib;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.Theme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minecraft.client.gui.Gui;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public abstract class Drawable extends Gui {


    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;


    public Theme getTheme() {
        return ArtusMod.getGuiConfiguration().getTheme();
    }

    public abstract void draw(int mouseX, int mouseY);
    public void draw() {
        draw(0, 0);
    }




}
