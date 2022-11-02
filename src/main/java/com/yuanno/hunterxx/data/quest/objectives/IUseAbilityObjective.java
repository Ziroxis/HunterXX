package com.yuanno.hunterxx.data.quest.objectives;

import com.yuanno.hunterxx.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

public interface IUseAbilityObjective
{
	boolean checkAbility(PlayerEntity player, Ability ability);
}
