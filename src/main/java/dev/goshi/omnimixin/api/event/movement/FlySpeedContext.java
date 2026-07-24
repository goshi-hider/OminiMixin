package dev.goshi.omnimixin.api.event.movement;

import dev.goshi.omnimixin.api.common.MutableFloat;
import net.minecraft.entity.player.PlayerAbilities;

public record FlySpeedContext(PlayerAbilities abilities, float baseFlySpeed, MutableFloat modifier) {
}
