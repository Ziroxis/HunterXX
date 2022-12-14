package com.yuanno.hunterxx.api.ability.sorts;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

import java.io.Serializable;

public class DamagedPassiveAbility extends PassiveAbility
{
    protected IOnDamaged onDamagedEvent = (player, attacker) -> false;

    public DamagedPassiveAbility(String name, AbilityCategories.AbilityCategory category)
    {
        super(name, category);
    }

    public boolean damage(LivingEntity entity, DamageSource source)
    {    	
    	if(this.isPaused())
    		return true;
    	
        return this.onDamagedEvent.onDamage(entity, source);
    }

    public interface IOnDamaged extends Serializable
    {
        boolean onDamage(LivingEntity entity, DamageSource source);
    }
}
