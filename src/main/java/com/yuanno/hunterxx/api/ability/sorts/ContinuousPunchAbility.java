package com.yuanno.hunterxx.api.ability.sorts;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

/**
 * Specific class made for continuous enhanced punches with effect
 */
public abstract class ContinuousPunchAbility extends ContinuousAbility
{
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHitEntity onHitEntityEvent = (player, target) -> { return 0; };
	
	public ContinuousPunchAbility(String name, AbilityCategories.AbilityCategory category)
	{
		super(name, category);
	}
	
	
	/*
	 *  Methods 
	 */
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{


		//this.stopContinuity(player);
		return this.onHitEntityEvent.onHitEntity(player, target);
	}
	
	/*
	 *	Interfaces
	 */
	public interface IOnHitEntity extends Serializable
	{
		float onHitEntity(PlayerEntity player, LivingEntity target);
	}
}
