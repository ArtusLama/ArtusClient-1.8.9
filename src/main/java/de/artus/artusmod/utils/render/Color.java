package de.artus.artusmod.utils.render;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class Color {

    public static Color of(int r, int g, int b, int a) {
        return new Color(r, g, b, a);
    }
    public static Color of(int r, int g, int b) {
        return new Color(r, g, b, 255);
    }
    public static Color of(String hex) {
        if (hex.startsWith("#")) {
            hex = hex.substring(1);
        }
        if (hex.length() == 6) {
            hex += "FF";
        }
        return new Color(
                Integer.parseInt(hex.substring(0, 2), 16),
                Integer.parseInt(hex.substring(2, 4), 16),
                Integer.parseInt(hex.substring(4, 6), 16),
                Integer.parseInt(hex.substring(6, 8), 16)
        );
    }

    int r;
    int g;
    int b;
    int a;

    public String getHex() {
        return ColorUtil.toHex(r, g, b, a);
    }
    public int getInt() {
        return ColorUtil.toInt(r, g, b, a);
    }
    public Color getWithAlpha(int a) {
        return new Color(r, g, b, a);
    }
    public Color getWithAlphaPercent(int percent) {
        return getWithAlpha((int) (255 * (percent / 100f)));
    }
    public Color getWithHalfAlpha() {
        return getWithAlpha(a / 2);
    }


}
