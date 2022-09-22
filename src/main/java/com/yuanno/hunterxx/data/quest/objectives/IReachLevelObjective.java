package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;

public interface IReachLevelObjective
{
	boolean checkLevel(PlayerEntity player);
}
