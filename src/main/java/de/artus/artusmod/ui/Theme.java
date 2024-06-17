package de.artus.artusmod.ui;

import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;


@Getter @Setter
public class Theme {

    public void loadFontRenderer() {
        setDefaultFontRenderer(Minecraft.getMinecraft().fontRendererObj);
    }
    private FontRenderer defaultFontRenderer;

    private int primaryColor = new Color(3, 175, 239, 255).getInt();
    private int backgroundColor = new Color(21, 21, 21, 200).getInt();
    private int textColorOLD = new Color(255, 255, 255, 255).getInt();

    private int buttonBackgroundColor = new Color(200, 200, 200, 75).getInt();
    private int modMenuModBackgroundColor = new Color(15, 15, 15, 210).getInt();
    private int modMenuModHoverBackgroundColor = new Color(10, 10, 10, 230).getInt();
    private int modMenuModSelectedBackgroundColor = new Color(8, 8, 8, 240).getInt();

    private int dangerColorOLD = new Color(222, 50, 50, 255).getInt();



    private Color fallbackColor = Color.of("#fc0a97");

    private Color primary = Color.of("29A8DE");
    private Color primaryUnfocused = getPrimary().getWithAlphaPercent(65);
    private Color primaryFocused = getPrimary().getWithAlphaPercent(80);

    private Color background = Color.of("B0B0B0").getWithAlphaPercent(25);
    private Color backgroundLight = getBackground().getWithAlphaPercent(50);
    private Color darkBackground = Color.of("757575").getWithAlphaPercent(25);
    private Color darkBackgroundLight = getDarkBackground().getWithAlphaPercent(50);

    private Color text = Color.of("FFFFFF").getWithAlphaPercent(75);
    private Color textLight = getText().getWithAlphaPercent(100);

    private Color dangerColor = Color.of("FF3C3C");
    private Color transparentDangerColor = getDangerColor().getWithAlphaPercent(25);


    public static Theme getDefault() {
        return new Theme();
    }


}
