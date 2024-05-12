package de.artus.artusmod.mixins.betterWindow;


import de.artus.artusmod.ArtusMod;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.LoaderState;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LoadController.class)
public abstract class QuickQuitOnStart {


    @Inject(method = "transition(Lnet/minecraftforge/fml/common/LoaderState;Z)V", at = @At("HEAD"))
    private void quickQuit$earlyExit(LoaderState desiredState, boolean forceState, CallbackInfo ci) {
        if (Display.isCreated() && Display.isCloseRequested()) {
            FMLCommonHandler.instance().exitJava(0, false);
        }
    }


}
