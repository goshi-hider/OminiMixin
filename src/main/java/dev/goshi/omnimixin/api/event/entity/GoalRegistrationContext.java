package dev.goshi.omnimixin.api.event.entity;

import dev.goshi.omnimixin.api.common.Cancellable;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;

public record GoalRegistrationContext(GoalSelector selector, int priority, Goal goal, Cancellable cancel) {
}
