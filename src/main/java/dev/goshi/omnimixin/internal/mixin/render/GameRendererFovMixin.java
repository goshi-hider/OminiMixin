package dev.goshi.omnimixin.internal.mixin.render;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.common.MutableDouble;
import dev.goshi.omnimixin.api.event.render.FovContext;
import dev.goshi.omnimixin.api.event.render.PlayCamera;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(GameRenderer.class)
abstract class GameRendererFovMixin {

    @ModifyReturnValue(method = "getFov(Lnet/minecraft/client/render/Camera;FZ)D", at = @At("RETURN"))
    private double omnimixin$modifyFov(double original, Camera camera) {
        MutableDouble modifier = new MutableDouble(original);
        PlayCamera.CALCULATE_FOV.invoker().calculate(new FovContext(camera, original, modifier));
        return modifier.get();
    }
}
