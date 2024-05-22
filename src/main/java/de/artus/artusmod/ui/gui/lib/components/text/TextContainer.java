package de.artus.artusmod.ui.gui.lib.components.text;

import de.artus.artusmod.ui.gui.lib.helpers.DrawHelper;
import de.artus.artusmod.ui.gui.lib.Drawable;
import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

@Accessors(chain = true)
public class TextContainer extends Drawable {

    @Getter @Setter
    private String text;
    @Getter @Setter
    private boolean clipToWidth = false;

    @Getter @Setter
    private int linePadding = 2;

    @Getter @Setter
    private Color color = Color.of("#FFFFFF"); // Default text color

    @Getter @Setter
    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj; // Default font renderer


    public TextContainer(int x, int y, int width, int height, String text) {
        super(x, y, width, height);
        setText(text);
    }

    @Override
    public void draw() {
        if (isClipToWidth()) {
            DrawHelper.drawTextBlock(getFontRenderer(), getText(), getLinePadding(), getX(), getY(), getWidth(), getHeight(), getColor());
        } else {
            DrawHelper.drawString(getFontRenderer(), getText(), getX(), getY(), getColor());
        }

    }


}
