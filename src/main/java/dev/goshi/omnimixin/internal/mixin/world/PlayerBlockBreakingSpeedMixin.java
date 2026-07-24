package dev.goshi.omnimixin.internal.mixin.world;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.goshi.omnimixin.api.common.MutableFloat;
import dev.goshi.omnimixin.api.event.world.MiningSpeedContext;
import dev.goshi.omnimixin.api.event.world.PlayBreak;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PlayerEntity.class)
abstract class PlayerBlockBreakingSpeedMixin {

    @ModifyReturnValue(method = "getBlockBreakingSpeed(Lnet/minecraft/block/BlockState;)F", at = @At("RETURN"))
    private float omnimixin$modifyBreakingSpeed(float original, BlockState state) {
        PlayerEntity self = (PlayerEntity) (Object) this;
        MutableFloat speed = new MutableFloat(original);
        PlayBreak.CALCULATE_SPEED.invoker().calculate(new MiningSpeedContext(self, state, speed));
        return speed.get();
    }
}
