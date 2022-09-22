package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;

public interface ISurviveObjective
{
	boolean checkTime(PlayerEntity player);
}
