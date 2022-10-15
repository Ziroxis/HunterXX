package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.PunchAbility;
import com.yuanno.hunterxx.init.ModEffects;
import com.yuanno.hunterxx.init.ModEntities;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;

public class BasicManipulationAbility extends PunchAbility {

    public static final BasicManipulationAbility INSTANCE = new BasicManipulationAbility();

    public BasicManipulationAbility() {
        super("Basic manipulation", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("Touches an enemy, rendering him unable to move for a few moments");
        this.setauraCost(10);
        this.setExperience(12);
        this.setExperienceGainLevelCap(40);

        this.onHitEntityEvent = this::onHitEvent;
    }

    private float onHitEvent(PlayerEntity player, LivingEntity target)
    {
        if (!target.hasEffect(ModEffects.MOVEMENT_BLOCKED.get()))
            target.addEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED.get(), (int) (160 * Beapi.valueCategory(player, ModValues.MANIPULATION)), 0));
        return 1;
    }
}
