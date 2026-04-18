package com.ct4l.mod;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ct4lMod implements ModInitializer {
    public static final String MOD_ID = "ct4l_lagfree";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[CT4L Optimizer] Architecting performance protocols...");
        LOGGER.info("[CT4L Optimizer] Fast culling and rendering subsystems engaged!");
        // Engine is initialized. Mixins handle the extreme performance processing.
    }
}