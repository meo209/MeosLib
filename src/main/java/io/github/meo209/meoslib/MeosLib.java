package io.github.meo209.meoslib;

import io.github.meo209.meoslib.registry.RegistryHelper;
import io.github.meo209.meoslib.tests.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MeosLib implements ModInitializer {

	public static final String MOD_ID = "meoslib", VERSION = "0.1.0";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing MeosLib version " + VERSION);

		System.out.println(RegistryHelper.toRegistryName("entity type"));
		try {
			RegistryHelper.register(ModItems.class, MOD_ID);
		} catch (IllegalAccessException | NoSuchFieldException e) {
			throw new RuntimeException(e);
		}
    }


}