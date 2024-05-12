package de.artus.artusmod.ui;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter @Setter
public class Theme {


    private int primaryColor = new Color(3, 175, 239, 255).getRGB();
    private int backgroundColor = new Color(21, 21, 21, 200).getRGB();
    private int textColor = new Color(255, 255, 255, 255).getRGB();

    private int buttonBackgroundColor = new Color(200, 200, 200, 75).getRGB();
    private int modMenuModBackgroundColor = new Color(15, 15, 15, 210).getRGB();
    private int modMenuModHoverBackgroundColor = new Color(10, 10, 10, 230).getRGB();
    private int modMenuModSelectedBackgroundColor = new Color(8, 8, 8, 240).getRGB();

    private int dangerColor = new Color(222, 50, 50, 255).getRGB();



    public static Theme getDefault() {
        return new Theme();
    }


}
