package dev.goshi.omnimixin.api.event.render;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;

@Environment(EnvType.CLIENT)
public record HudContext(DrawContext context) {
}
