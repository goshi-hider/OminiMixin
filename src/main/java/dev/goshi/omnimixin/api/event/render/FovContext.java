package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.api.common.MutableDouble;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Camera;

@Environment(EnvType.CLIENT)
public record FovContext(Camera camera, double baseFov, MutableDouble modifier) {
}
