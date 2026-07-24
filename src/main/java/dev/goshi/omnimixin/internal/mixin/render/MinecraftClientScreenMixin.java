package dev.goshi.omnimixin.internal.mixin.render;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.render.PlayScreen;
import dev.goshi.omnimixin.api.event.render.ScreenOpenContext;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
abstract class MinecraftClientScreenMixin {

    @Shadow
    public Screen currentScreen;

    @WrapMethod(method = "setScreen(Lnet/minecraft/client/gui/screen/Screen;)V")
    private void omnimixin$wrapSetScreen(Screen screen, Operation<Void> original) {
        if (screen == null) {

            original.call((Screen) null);
            return;
        }

        Cancellable cancel = new Cancellable();
        PlayScreen.OPEN.invoker().open(new ScreenOpenContext(screen, this.currentScreen, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        original.call(screen);
    }
}
