package de.artus.artusmod.mixins;

import de.artus.artusmod.ArtusMod;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Map;

public class MixinLoader implements IFMLLoadingPlugin {

    public MixinLoader() {
        System.out.println("Injecting Mixins from " + ArtusMod.MODID);
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins." + ArtusMod.MODID + ".json");
        MixinEnvironment.getDefaultEnvironment().setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {

    }


    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}