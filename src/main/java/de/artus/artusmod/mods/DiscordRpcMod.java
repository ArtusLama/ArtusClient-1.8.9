package de.artus.artusmod.mods;

import de.artus.artusmod.ArtusMod;
import de.artus.artusmod.ui.gui.screens.OLD.menus.MainMenuScreenOLD;
import de.artus.artusmod.utils.DiscordRpc;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class DiscordRpcMod extends Mod {

    public DiscordRpcMod() {
        super("discordrpc");
    }

    @Override
    public void init() {

    }

    @Override
    public void onEnable() {
        ArtusMod.getDiscordRpc().setup();

        checkSingleOrMultiplayer();
    }

    @Override
    public void onDisable() {
        ArtusMod.getDiscordRpc().shutdown();
    }



    private void checkSingleOrMultiplayer() {
        if (Minecraft.getMinecraft().isSingleplayer()) {
            ArtusMod.getDiscordRpc().setSingleplayer();
        } else if (Minecraft.getMinecraft().getCurrentServerData() != null) {
            String addr = Minecraft.getMinecraft().getCurrentServerData().serverIP;
            ArtusMod.getDiscordRpc().setMultiplayer(addr);
        }
    }
    @SubscribeEvent
    public void onLogin(PlayerEvent.PlayerLoggedInEvent e) {
        checkSingleOrMultiplayer();
    }
    @SubscribeEvent
    public void onServerLogin(FMLNetworkEvent.ClientConnectedToServerEvent e) {
        if (Minecraft.getMinecraft().getCurrentServerData() == null) return;
        String addr = Minecraft.getMinecraft().getCurrentServerData().serverIP;
        if (!e.isLocal) ArtusMod.getDiscordRpc().setMultiplayer(addr);
    }
    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent e) {
        if (ArtusMod.getDiscordRpc().getGameState() == DiscordRpc.GameState.IDLE) return;
        ArtusMod.getDiscordRpc().setIdling();
    }
    @SubscribeEvent
    public void onLogout(PlayerEvent.PlayerLoggedOutEvent e) {
        if (ArtusMod.getDiscordRpc().getGameState() == DiscordRpc.GameState.IDLE) return;
        ArtusMod.getDiscordRpc().setIdling();
    }

    @SubscribeEvent
    public void onMainMenu(GuiScreenEvent.InitGuiEvent e) {
        if (e.gui instanceof MainMenuScreenOLD || e.gui instanceof GuiMainMenu) {
            if (ArtusMod.getDiscordRpc().getGameState() == DiscordRpc.GameState.IDLE) return;
            ArtusMod.getDiscordRpc().setIdling();
        }
    }
}
