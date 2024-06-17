package de.artus.artusmod.utils.mouse;

import de.artus.artusmod.ArtusMod;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
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
    private static final Cursor defaultCursor = Mouse.getNativeCursor();

    @AllArgsConstructor
    public enum CursorType {
        DEFAULT(""),
        HAND("cursors/hand.png");

        @Getter
        private final String path;

        // Do not use on Default -> else it will cause an error
        public ResourceLocation getResourceLocation() {
            return new ResourceLocation(ArtusMod.MODID, getPath());
        }
        public Cursor getCursor() {
            switch (this) {
                case DEFAULT:
                    return getDefaultCursor();
                case HAND:
                    return loadCursor(getImageFromResource(getResourceLocation()), 6, 31);
            }

            // Fallback hotspot
            return loadCursor(getImageFromResource(getResourceLocation()), 0, 0);
        }
    }

    @Getter @Setter(AccessLevel.PRIVATE)
    private static CursorType currentCursor = CursorType.DEFAULT;

    public static void setCursor(CursorType cursor) {
        try {
            Mouse.setNativeCursor(cursor.getCursor());
            setCurrentCursor(cursor);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }


    public static void useDefaultCursor() {
        if (getCurrentCursor() != CursorType.DEFAULT)
            setCursor(CursorType.DEFAULT);
    }
    public static void useHandCursor() {
        if (getCurrentCursor() != CursorType.HAND)
            setCursor(CursorType.HAND);
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
