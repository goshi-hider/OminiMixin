package dev.goshi.omnimixin.internal.mixin.render;

import dev.goshi.omnimixin.api.event.render.PlayScreen;
import dev.goshi.omnimixin.api.event.render.ScreenContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(Screen.class)
abstract class ScreenLifecycleMixin {

    @Inject(method = "clearAndInit()V", at = @At("TAIL"))
    private void omnimixin$onInit(CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        PlayScreen.INIT.invoker().init(new ScreenContext(self));
    }

    @Inject(method = "removed()V", at = @At("TAIL"))
    private void omnimixin$onRemoved(CallbackInfo ci) {
        Screen self = (Screen) (Object) this;
        PlayScreen.CLOSE.invoker().close(new ScreenContext(self));
    }
}
