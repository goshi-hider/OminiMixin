package dev.goshi.omnimixin.internal.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import dev.goshi.omnimixin.api.common.Cancellable;
import dev.goshi.omnimixin.api.event.entity.GoalRegistrationContext;
import dev.goshi.omnimixin.api.event.entity.PlayGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GoalSelector.class)
abstract class GoalSelectorAddMixin {

    @WrapMethod(method = "add(ILnet/minecraft/entity/ai/goal/Goal;)V")
    private void omnimixin$wrapAdd(int priority, Goal goal, Operation<Void> original) {
        GoalSelector self = (GoalSelector) (Object) this;
        Cancellable cancel = new Cancellable();

        PlayGoal.REGISTER_GOALS.invoker().registerGoal(new GoalRegistrationContext(self, priority, goal, cancel));
        if (cancel.isCancelled()) {
            return;
        }

        original.call(priority, goal);
    }
}
