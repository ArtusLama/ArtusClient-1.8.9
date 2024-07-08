package de.artus.artusmod.ui;

import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Theme {


    private int primaryColor = new Color(3, 175, 239, 255).getInt();
    private int backgroundColor = new Color(21, 21, 21, 200).getInt();
    private int textColorOLD = new Color(255, 255, 255, 255).getInt();

    private int buttonBackgroundColor = new Color(200, 200, 200, 75).getInt();
    private int modMenuModBackgroundColor = new Color(15, 15, 15, 210).getInt();
    private int modMenuModHoverBackgroundColor = new Color(10, 10, 10, 230).getInt();
    private int modMenuModSelectedBackgroundColor = new Color(8, 8, 8, 240).getInt();

    private int dangerColorOLD = new Color(222, 50, 50, 255).getInt();



    private Color fallbackColor = Color.of("#fc0a97"); // not final

    private Color primary = Color.of("29A8DE"); // not final
    private Color primaryUnfocused = getPrimary().getWithAlphaPercent(65); // not final
    private Color primaryFocused = getPrimary().getWithAlphaPercent(80); // not final

    private Color background = Color.of("C1C1C1").getWithAlphaPercent(60);
    private Color backgroundLight = Color.of("DDDDDD").getWithAlphaPercent(70);
    private Color darkBackground = Color.of("757575").getWithAlphaPercent(25); // not final
    private Color darkBackgroundLight = getDarkBackground().getWithAlphaPercent(50); // not final

    private Color outlineColor = Color.of("DDDDDD").getWithAlphaPercent(70); // not final
    private Color outlineColorLight = Color.of("FFFFFF").getWithAlphaPercent(80); // not final
    private Color darkOutlineColor = Color.of("8B8B8B").getWithAlphaPercent(70); // not final
    private Color darkOutlineColorLight = Color.of("ABABAB").getWithAlphaPercent(80); // not final

    private Color text = Color.of("FFFFFF").getWithAlphaPercent(75); // not final
    private Color textTransparent = Color.of("FFFFFF").getWithAlphaPercent(85);
    private Color textLight = getText().getWithAlphaPercent(100); // not final

    private Color dangerColor = Color.of("FF3C3C"); // not final
    private Color transparentDangerColor = getDangerColor().getWithAlphaPercent(25); // not final


    public static Theme getDefault() {
        return new Theme();
    }


}
