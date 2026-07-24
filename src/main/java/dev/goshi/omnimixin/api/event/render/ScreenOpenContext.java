package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public record ScreenOpenContext(Screen newScreen, Screen previousScreen, Cancellable cancel) {
}
