package com.github.meo209.meoslib;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeosLib implements ModInitializer {

	public static final String MOD_ID = "meoslib", VERSION = "0.1.3";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MeosLib version " + VERSION);
    }


}