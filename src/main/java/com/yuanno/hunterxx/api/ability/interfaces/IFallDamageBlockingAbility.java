package com.yuanno.hunterxx.api.ability.interfaces;

import net.minecraft.entity.LivingEntity;

public interface IFallDamageBlockingAbility
{
	public void resetFallDamage(LivingEntity entity);

	public boolean hasFallDamage();
}
