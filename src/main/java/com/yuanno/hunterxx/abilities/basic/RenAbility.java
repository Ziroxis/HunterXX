package com.yuanno.hunterxx.abilities.basic;

import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;

public class RenAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final Ability INSTANCE = new RenAbility();
    public RenAbility() {
        super("Ren", AbilityCategories.AbilityCategory.BASIC_NEN);
        this.setDescription("Puts out a huge amount of aura");
        this.setMaxCooldown(3);
        this.setauraCost(8);
        this.setExperience(10);
        this.setExperienceGainLevelCap(20);

    }
}
