package de.artus.artusmod.ui.gui.OLD.lib.components.mods.modInfo;

import de.artus.artusmod.mods.Mod;
import de.artus.artusmod.ui.gui.OLD.lib.Drawable;
import de.artus.artusmod.ui.gui.lib.helpers.TextHelper;
import de.artus.artusmod.utils.render.ColorUtil;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.List;

public class ModInfoSection extends Drawable {

    @Getter
    private Mod mod;

    public ModInfoSection(int x, int y, int width, int height, Mod mod) {
        super(x, y, width, height);
        this.mod = mod;
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        int contentHeight = 0;

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        drawString(fr, "Description", getX(), getY() + contentHeight, ColorUtil.calculateHoverBackgroundColor(getTheme().getPrimaryColor()));
        contentHeight += fr.FONT_HEIGHT;
        contentHeight += 4;

        List<String> lines = TextHelper.getLinesWithMaxWidth(fr, getMod().getDescription(), getWidth());
        for (String line : lines) {
            drawString(fr, line, getX(), getY() + contentHeight, getTheme().getTextColorOLD());
            contentHeight += fr.FONT_HEIGHT;
            contentHeight += 1;
        }


        setHeight(contentHeight);
    }
}
