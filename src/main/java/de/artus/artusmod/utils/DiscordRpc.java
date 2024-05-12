package de.artus.artusmod.utils;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRichPresence;
import lombok.Getter;
import club.minnced.discord.rpc.DiscordRPC;
import lombok.Setter;


public class DiscordRpc {

    @Getter
    private static final String clientId = "1238971518898405467";


    @Getter
    private DiscordRPC lib = DiscordRPC.INSTANCE;

    @Getter
    private DiscordRichPresence presence = new DiscordRichPresence();

    @Getter
    private DiscordEventHandlers handlers = new DiscordEventHandlers();

    public enum GameState {
        IDLE,
        SINGLEPLAYER,
        MULTIPLAYER,
        UNKNOWN
    }
    @Getter @Setter
    private GameState gameState = GameState.UNKNOWN;

    public void setup() {
        getLib().Discord_Initialize(getClientId(), getHandlers(), true, null);
        setIdling();
    }

    public void shutdown() {
        getLib().Discord_Shutdown();
    }

    public void updatePresence() {
        getLib().Discord_UpdatePresence(getPresence());
    }

    public void resetTimestamp() {
        getPresence().startTimestamp = System.currentTimeMillis() / 1000;
    }

    private void setSmallImg(String img, String tooltip) {
        getPresence().smallImageKey = img;
        getPresence().smallImageText = tooltip;
    }

    private void useLogo() {
        getPresence().largeImageKey = "logo";
        getPresence().largeImageText = "Artus Client";
    }
    private void useMinecraftVersion() {
        getPresence().details = "Minecraft 1.8.9";
    }
    private void setState(String state) {
        getPresence().state = state;
    }
    public void setIdling() {
        setGameState(GameState.IDLE);
        resetTimestamp();
        useMinecraftVersion();
        useLogo();
        setState("Idling...");
        setSmallImg("", "");
        updatePresence();
    }
    public void setSingleplayer() {
        setGameState(GameState.SINGLEPLAYER);
        resetTimestamp();
        useMinecraftVersion();
        useLogo();
        setState("Playing Singleplayer");
        setSmallImg("singleplayer", "Singleplayer");
        updatePresence();
    }
    public void setMultiplayer(String serverAddress) {
        setGameState(GameState.MULTIPLAYER);
        resetTimestamp();
        useMinecraftVersion();
        useLogo();
        setState("Playing on a server");
        setSmallImg("multiplayer", serverAddress);
        updatePresence();
    }

}
