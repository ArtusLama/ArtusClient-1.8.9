package de.artus.artusmod;

import de.artus.artusmod.mods.DiscordRpcMod;
import de.artus.artusmod.other.RangePreview;
import de.artus.artusmod.ui.GuiConfiguration;
import de.artus.artusmod.mods.ModManager;
import de.artus.artusmod.mods.fixes.FixHitDelayMod;
import de.artus.artusmod.mods.fixes.FixMouseInputMod;
import de.artus.artusmod.mods.fixes.SmoothSneakAnimationMod;
import de.artus.artusmod.ui.gui.screens.OLD.splashScreen.CustomSplashScreen;
import de.artus.artusmod.ui.gui.screens.OLD.menus.ModMenuScreenOLD;
import de.artus.artusmod.utils.DiscordRpc;
import de.artus.artusmod.utils.keybindings.KeybindHelper;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Mod(modid = ArtusMod.MODID, version = ArtusMod.VERSION)
public class ArtusMod {

    public static final String MODID = "artusmod";
    public static final String VERSION = "1.0";
    @Getter
    private static Logger logger;
    @Getter
    private static ArtusMod instance;

    @Getter
    private static ModManager modManager = new ModManager();
    @Getter
    private static GuiConfiguration guiConfiguration = new GuiConfiguration();

    @Getter @Setter(AccessLevel.PRIVATE)
    private static CustomSplashScreen customSplashScreen;
    @Getter
    private static final int splashProgressSteps = 2;

    @Getter
    private static ModMenuScreenOLD modMenuScreen = new ModMenuScreenOLD();

    @Getter
    private static DiscordRpc discordRpc = new DiscordRpc();

    public ArtusMod() {
        instance = this;
    }

    public static void loadSplashScreen() {
        setCustomSplashScreen(new CustomSplashScreen(getSplashProgressSteps()));
        getCustomSplashScreen().drawSplashScreen(Minecraft.getMinecraft().getTextureManager());
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // needed because the font renderer is null before loaded and needs to be updated after it has been loaded!
        getGuiConfiguration().getTheme().loadFontRenderer();
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -


        // - - - - - - - - - - - -   R E G I S T E R   M O D S   - - - - - - - - - - - - -
        getCustomSplashScreen().getSplashProgress().setProgress("Loading mods...");
        getModManager().registerMod(new FixHitDelayMod(), true);
        getModManager().registerMod(new FixMouseInputMod(), true);

        getModManager().registerMod(new SmoothSneakAnimationMod(), true);
        getModManager().registerMod(new DiscordRpcMod(), true);

        getModManager().loadMods();
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

        // TODO: for testing purposes
        //new RangePreview();


        getCustomSplashScreen().getSplashProgress().setProgress("Loading Minecraft Stuff...");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        Display.setTitle("ArtusClient - 1.8.9");

        // - - - - - - -  -   R E G I S T E R   K E Y   B I N D I N G S    - - - - - - - -
        KeybindHelper.init();
        KeybindHelper.registerKey(KeybindHelper.Keys.OPEN_MOD_MENU, () -> {
            Minecraft.getMinecraft().displayGuiScreen(getModMenuScreen());
        });
        // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
    }

    @EventHandler
    public void exit(FMLModDisabledEvent event) {
        getDiscordRpc().shutdown();
    }

}
