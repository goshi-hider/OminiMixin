package dev.goshi.omnimixin.internal.mixin.render;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.event.render.HudContext;
import dev.goshi.omnimixin.api.event.render.PlayHud;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;

@Environment(EnvType.CLIENT)
@Mixin(InGameHud.class)
abstract class InGameHudMixin {

    @WrapMethod(method = "render(Lnet/minecraft/client/gui/DrawContext;Lnet/minecraft/client/render/RenderTickCounter;)V")
    private void omnimixin$wrapRender(DrawContext context, RenderTickCounter tickCounter, Operation<Void> original) {
        HudContext hudContext = new HudContext(context);
        PlayHud.PRE_HUD.invoker().render(hudContext);
        original.call(context, tickCounter);
        PlayHud.POST_HUD.invoker().render(hudContext);
    }
}
