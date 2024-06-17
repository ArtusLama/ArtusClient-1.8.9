package de.artus.artusmod.other;

import de.artus.artusmod.utils.render.Color;
import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class RangePreview {

    @Getter
    private static final float LINE_WIDTH = 3.0F;

    @Getter
    private static final Color normalLineColor = Color.of("#22FF99");
    @Getter
    private static final Color blockInRangeLineColor = Color.of("#FFCC66");
    @Getter
    private static final Color inRangeLineColor = Color.of("#FF5555");




    public RangePreview() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
        // for each player in the world:
        Minecraft mc = Minecraft.getMinecraft();
        if (mc.theWorld == null || mc.thePlayer == null) return;

        EntityPlayerSP player = mc.thePlayer;
        WorldClient world = mc.theWorld;

        //drawPlayerRange(player, event.partialTicks); // optional for yourself
        world.playerEntities.stream().filter(p -> p != player && !p.isInvisibleToPlayer(player)).forEach(p -> drawPlayerRange(p, event.partialTicks));
    }

    public void drawPlayerRange(EntityPlayer player, float partialTicks) {
        enableGL();

        float range = 3.0F;

        Vec3 eyePos = player.getPositionEyes(partialTicks);
        Vec3 lookDir = player.getLook(partialTicks);
        Vec3 endPos = eyePos.addVector(lookDir.xCoord * range, lookDir.yCoord * range, lookDir.zCoord * range);


        Color currentColor = getNormalLineColor();

        MovingObjectPosition result = player.rayTrace(range, partialTicks);
        if (result != null && result.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            currentColor = getBlockInRangeLineColor();
        }


        drawLine(eyePos.xCoord, eyePos.yCoord, eyePos.zCoord, endPos.xCoord, endPos.yCoord, endPos.zCoord, currentColor);


        disableGL();
    }

    @SubscribeEvent
    public void t(RenderLivingEvent.Pre<EntityLivingBase> e) {

        if (!(e.entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) e.entity;


        if (player.getLastAttacker() == null) return;
        //TODO not working with last attacker = null!
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(player.getLastAttacker().getName()));
        if (player.hurtTime > 0 && player.getLastAttacker() instanceof EntityPlayer) {
            onEntityHit((EntityPlayer) player.getLastAttacker(), player);
        }

    }


    public void onEntityHit(EntityPlayer source, EntityPlayer target) {
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("HIT"));
        if (Minecraft.getMinecraft().thePlayer == null) return;


        Vec3 eyePos = source.getPositionEyes(1.0F);
        Vec3 eyeLook = source.getLook(1.0F);

        float range = 10.0F;
        Vec3 endPos = eyePos.addVector(eyeLook.xCoord * range, eyeLook.yCoord * range, eyeLook.zCoord * range);

        float borderSize = source.getCollisionBorderSize();
        AxisAlignedBB playerBB = source.getEntityBoundingBox().expand(borderSize, borderSize, borderSize);
        MovingObjectPosition result = playerBB.calculateIntercept(eyePos, endPos);

        if (result == null) return;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Result NULL"));
        double distance = eyePos.distanceTo(result.hitVec);



        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(String.format("[%s] %.2f", target.getName(), distance)));

    }


    public void drawLine(double startX, double startY, double startZ, double endX, double endY, double endZ, Color color) {
        GL11.glColor4f(color.getFRed(), color.getFGreen(), color.getFBlue(), color.getFAlpha());
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION);
        worldrenderer.pos(startX, startY, startZ).endVertex();
        worldrenderer.pos(endX, endY, endZ).endVertex();
        tessellator.draw();
        GlStateManager.resetColor();
    }


    private void enableGL() {
        GL11.glPushMatrix();
        GL11.glTranslated(-TileEntityRendererDispatcher.staticPlayerX, -TileEntityRendererDispatcher.staticPlayerY, -TileEntityRendererDispatcher.staticPlayerZ);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glLineWidth(getLINE_WIDTH());
    }
    private void disableGL() {
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }



}
