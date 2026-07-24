package dev.goshi.omnimixin.api.event.render;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

@Environment(EnvType.CLIENT)
public record RenderContext(
        Entity entity,
        double x,
        double y,
        double z,
        float yaw,
        float tickDelta,
        MatrixStack matrices,
        VertexConsumerProvider vertexConsumers,
        int light,
        Cancellable cancel
) {
}
