package de.artus.artusmod.ui.gui.lib.shapes;

import de.artus.artusmod.ui.gui.lib.BackgroundDrawable;
import de.artus.artusmod.utils.ColorUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;


public class CircleShape extends BackgroundDrawable {


    @Accessors(chain = true)
    @Getter @Setter
    private double distanceBetweenPoints = 6; // Quality of the circle => every 2 degrees one point

    @Getter @Setter
    private int radius = 10;

    public CircleShape(int x, int y, int radius) {
        super(x, y, radius, radius);
        setRadius(radius);
    }


    @Override
    public void draw(int mouseX, int mouseY) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);

        float[] color = ColorUtil.getRGBAFloat(getCurrentBackgroundColor());
        GlStateManager.color(color[0], color[1], color[2], color[3]);

        worldRenderer.begin(GL11.GL_POLYGON, DefaultVertexFormats.POSITION);

        for (int i = 0; i < 360; i += getDistanceBetweenPoints()) {
            int deg = 360 - i;
            double x = Math.cos(Math.toRadians(deg)) * getRadius();
            double y = Math.sin(Math.toRadians(deg)) * getRadius();
            worldRenderer.pos(getX() + getRadius() + x, getY() + getRadius() + y, 0.0).endVertex();
        }



        tessellator.draw();

        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

}
