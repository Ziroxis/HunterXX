package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public interface ICureEffectObjective
{
	boolean checkEffect(PlayerEntity player, EffectInstance effectInstance);
}
