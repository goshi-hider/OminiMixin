package dev.goshi;

import dev.goshi.omnimixin.internal.network.OmniNetworkSync;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OmniMixin implements ModInitializer {
	public static final String MOD_ID = "omnimixin";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		OmniNetworkSync.init();

		LOGGER.info("OmniMixin loaded - movement, combat & synced-value hooks are live.");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
