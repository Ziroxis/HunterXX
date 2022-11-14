package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;

public interface ITakeHitsObjective {


    boolean checkHit(PlayerEntity player, LivingEntity target, DamageSource source, float amount);
}
