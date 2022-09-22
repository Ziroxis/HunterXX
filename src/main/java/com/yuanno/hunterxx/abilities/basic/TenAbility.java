package com.yuanno.hunterxx.abilities.basic;

import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class TenAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final TenAbility INSTANCE = new TenAbility();

    private static final AttributeModifier TEN_ABILITY = new AttributeModifier(UUID.fromString("50248abc-35d9-11ed-a261-0242ac120002"),
            "Ten resistance", 2, AttributeModifier.Operation.ADDITION);

    public TenAbility()
    {
        super("Ten", AbilityCategories.AbilityCategory.BASIC_NEN);
        this.setDescription("Envelops yourself with aura");
        this.setMaxCooldown(3);
        this.setauraCost(5);

        this.setExperience(5);
        this.setExperienceGainLevelCap(10);


        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;

    }



    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(TEN_ABILITY);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(TEN_ABILITY);
        return true;
    }


}
