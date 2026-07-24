package dev.goshi.omnimixin.internal.mixin.movement;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.movement.FlySpeedContext;
import dev.goshi.omnimixin.api.event.movement.PlayFly;
import net.minecraft.entity.player.PlayerAbilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerAbilities.class)
abstract class PlayerAbilitiesFlySpeedMixin {

    @ModifyReturnValue(method = "getFlySpeed()F", at = @At("RETURN"))
    private float omnimixin$modifyFlySpeed(float original) {
        PlayerAbilities self = (PlayerAbilities) (Object) this;
        MutableFloat modifier = new MutableFloat(original);
        PlayFly.MODIFY_FLIGHT_SPEED.invoker().modify(new FlySpeedContext(self, original, modifier));
        return modifier.get();
    }
}
