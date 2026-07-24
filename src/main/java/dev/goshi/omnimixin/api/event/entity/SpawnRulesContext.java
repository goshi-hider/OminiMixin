package dev.goshi.omnimixin.api.event.entity;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.WorldView;

public record SpawnRulesContext(MobEntity entity, WorldView world, SpawnReason spawnReason) {
}
