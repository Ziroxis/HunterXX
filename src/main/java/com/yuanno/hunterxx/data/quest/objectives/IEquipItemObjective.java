package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.entity.player.PlayerEntity;

public interface IEquipItemObjective
{
	boolean checkEquippedItem(PlayerEntity player);
}
