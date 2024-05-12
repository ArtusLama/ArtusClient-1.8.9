package de.artus.artusmod.ui.gui.lib.shapes;


import de.artus.artusmod.ui.gui.lib.BackgroundDrawable;
import de.artus.artusmod.utils.render.ColorUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;


public class RoundedRectShape extends BackgroundDrawable {

    @Getter @Setter
    private int cornerRadius;

    public RoundedRectShape(int x, int y, int width, int height, int cornerRadius) {
        super(x, y, width, height);
        setCornerRadius(cornerRadius);
        if (getCornerRadius() > getWidth() / 2) {
            setCornerRadius(getWidth() / 2);
        }
        if (getCornerRadius() > getHeight() / 2) {
            setCornerRadius(getHeight() / 2);
        }
    }


    @Override
    public void draw(int mouseX, int mouseY) {


        // main rect
        drawRect(getX(), getY() + getCornerRadius(), getX() + getWidth(), getY() + getHeight() - getCornerRadius(), getCurrentBackgroundColor());
        // top rect
        drawRect(getX() + getCornerRadius(), getY(), getX() + getWidth() - getCornerRadius(), getY() + getCornerRadius(), getCurrentBackgroundColor());
        // bottom rect
        drawRect(getX() + getCornerRadius(), getY() + getHeight() - getCornerRadius(), getX() + getWidth() - getCornerRadius(), getY() + getHeight(), getCurrentBackgroundColor());


        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        float[] color = ColorUtil.getRGBAFloat(getCurrentBackgroundColor());
        GlStateManager.color(color[0], color[1], color[2], color[3]);



        drawFragmentCircle(getX() + getCornerRadius(), getY() + getCornerRadius(), 270, 360, worldRenderer, getCornerRadius());
        drawFragmentCircle(getX() + getWidth() - getCornerRadius(), getY() + getCornerRadius(), 0, 90, worldRenderer, getCornerRadius());
        drawFragmentCircle(getX() + getWidth() - getCornerRadius(), getY() + getHeight() - getCornerRadius(), 90, 180, worldRenderer, getCornerRadius());
        drawFragmentCircle(getX() + getCornerRadius(), getY() + getHeight() - getCornerRadius(), 180, 270, worldRenderer, getCornerRadius());


        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }


    private void drawFragmentCircle(int xCenter, int yCenter, int startDeg, int stopDeg, WorldRenderer worldRenderer, int radius) {
        worldRenderer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION);
        worldRenderer.pos(xCenter, yCenter, 0.0).endVertex();

        // draw circle from stop to start
        for (int i = stopDeg; i >= startDeg; i -= 2) {
            double angle = Math.toRadians(i - 90); // -90 because of the screen coordinates
            double x = Math.cos(angle) * radius;
            double y = Math.sin(angle) * radius;
            worldRenderer.pos(xCenter + x, yCenter + y, 0.0).endVertex();
        }

        Tessellator.getInstance().draw();
    }

}
