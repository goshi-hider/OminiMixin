package dev.goshi.omnimixin.api.event.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public record ScreenContext(Screen screen) {
}
