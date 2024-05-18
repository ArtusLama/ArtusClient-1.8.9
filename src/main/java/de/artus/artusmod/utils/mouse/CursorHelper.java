package de.artus.artusmod.utils.mouse;

import de.artus.artusmod.ArtusMod;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.IntBuffer;

public class CursorHelper {

    @Getter
    private static ResourceLocation handCursor = new ResourceLocation(ArtusMod.MODID, "cursors/hand.png");


    @Getter
    public static final Cursor defaultCursor = Mouse.getNativeCursor();
    public static void setCursor(Cursor cursor) {
        try {
            Mouse.setNativeCursor(cursor);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    public static void useDefaultCursor() {
        setCursor(getDefaultCursor());
    }
    public static void useHandCursor() {
        setCursor(loadCursor(getImageFromResource(getHandCursor()), 6, 31));
    }
    private static BufferedImage getImageFromResource(ResourceLocation resourceLocation) {
        try {
            return ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(resourceLocation).getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cursor loadCursor(BufferedImage cursorImg, int xHotspot, int yHotspot) {
        int cursorSize = Cursor.getMaxCursorSize();
        if (cursorSize > 64) cursorSize = 64;

        IntBuffer intBuffer = BufferUtils.createIntBuffer(cursorSize * cursorSize);
        for (int i = 0; i < cursorSize * cursorSize; ++i) {
            int n4 = i % cursorSize;
            int n5 = i / cursorSize;
            if (n4 >= cursorImg.getWidth() || n5 >= cursorImg.getHeight()) {
                intBuffer.put(0);
                continue;
            }
            intBuffer.put(cursorImg.getRGB(n4, cursorImg.getHeight() - 1 - n5));
        }
        intBuffer.flip();
        try {
            return new Cursor(cursorSize, cursorSize, xHotspot, yHotspot, 1, intBuffer, null);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
