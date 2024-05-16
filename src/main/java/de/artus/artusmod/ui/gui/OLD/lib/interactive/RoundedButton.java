package de.artus.artusmod.ui.gui.OLD.lib.interactive;

import de.artus.artusmod.ui.gui.OLD.lib.Clickable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

import java.util.function.Consumer;

public class RoundedButton extends HoverableRoundedRect implements Clickable {

    @Getter @Setter
    private String text;

    @Accessors(chain = true)
    @Getter @Setter
    private Consumer<Integer> onClick = (mouseButton) -> {};

    public RoundedButton(int x, int y, int width, int height, String text) {
        super(x, y, width, height, 6);
        setText(text);
        setBackgroundColor(getTheme().getButtonBackgroundColor());
    }
    public RoundedButton(int x, int y, int width, int height) {
        super(x, y, width, height, 6);
        setText("");
        setBackgroundColor(getTheme().getButtonBackgroundColor());
    }

    @Override
    public void draw(int mouseX, int mouseY) {
        super.draw(mouseX, mouseY);

        FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;
        drawCenteredString(fr, getText(), getX() + getWidth() / 2, getY() + getHeight() / 2 - fr.FONT_HEIGHT / 2, getTheme().getTextColor());
    }


    @Override
    public void onClicked(int mouseX, int mouseY, int mouseButton) {
        getOnClick().accept(mouseButton);
    }
}
