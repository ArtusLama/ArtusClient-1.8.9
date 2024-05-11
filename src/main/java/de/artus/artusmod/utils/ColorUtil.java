package de.artus.artusmod.utils;

public class ColorUtil {


    public static float[] getRGBAFloat(int color) {
        int[] rgba = getRGBA(color);
        float r = rgba[0] / 255f;
        float g = rgba[1] / 255f;
        float b = rgba[2] / 255f;
        float a = rgba[3] / 255f;
        return new float[]{r, g, b, a};
    }
    public static int[] getRGBA(int color) {
        int a = (color >> 24 & 255);
        int r = (color >> 16 & 255);
        int g = (color >> 8 & 255);
        int b = (color & 255);
        return new int[]{r, g, b, a};
    }
    public static int toInt(int r, int g, int b, int a) {
        return (r << 16) + (g << 8) + b + (a << 24);
    }


    public static int calculateHoverBackgroundColor(int r, int g, int b, int a) {
        int colorChange = 20;
        r -= colorChange;
        g -= colorChange;
        b -= colorChange;
        a += colorChange;

        r = Math.max(0, Math.min(255, r));
        g = Math.max(0, Math.min(255, g));
        b = Math.max(0, Math.min(255, b));
        a = Math.max(0, Math.min(255, a));
        return toInt(r, g, b, a);
    }
    public static int calculateHoverBackgroundColor(int color) {
        int[] rgba = getRGBA(color);
        return calculateHoverBackgroundColor(rgba[0], rgba[1], rgba[2], rgba[3]);
    }

    public static int setAlpha(int color, int alpha) {
        int[] rgba = getRGBA(color);
        return toInt(rgba[0], rgba[1], rgba[2], alpha);
    }


}
