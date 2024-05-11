package de.artus.artusmod.ui.gui.screens.splashScreen;


import de.artus.artusmod.ArtusMod;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class SplashProgress {

    private final int totalSteps;
    private int currentStep = 0;
    private String currentStepName = "Loading...";

    public void setProgress(String stepName) {
        currentStep++;
        setCurrentStepName(stepName);
        ArtusMod.getCustomSplashScreen().update();
    }


}
