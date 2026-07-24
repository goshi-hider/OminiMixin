package dev.goshi.omnimixin.internal.mixin.render;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.render.PlayRender;
import dev.goshi.omnimixin.api.event.render.RenderContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderDispatcher.class)
abstract class EntityRenderMixin {

    @WrapMethod(
            method = "render(Lnet/minecraft/entity/Entity;DDDFFLnet/minecraft/client/util/math/MatrixStack;"
                    + "Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
    )
    private <E extends Entity> void omnimixin$wrapRender(
            E entity, double x, double y, double z, float yaw, float tickDelta,
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light,
            Operation<Void> original
    ) {
        Cancellable cancel = new Cancellable();
        RenderContext preContext = new RenderContext(
                entity, x, y, z, yaw, tickDelta, matrices, vertexConsumers, light, cancel
        );

        PlayRender.PRE_ENTITY.invoker().render(preContext);
        if (cancel.isCancelled()) {
            return;
        }

        original.call(entity, x, y, z, yaw, tickDelta, matrices, vertexConsumers, light);

        RenderContext postContext = new RenderContext(
                entity, x, y, z, yaw, tickDelta, matrices, vertexConsumers, light, new Cancellable()
        );
        PlayRender.POST_ENTITY.invoker().render(postContext);
    }
}
