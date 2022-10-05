package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class KenAbility extends ContinuousAbility {

    public static final KenAbility INSTANCE = new KenAbility();

    public static final UUID kenUUID = UUID.fromString("40a61b18-44e4-11ed-b878-0242ac120002");

    public KenAbility() {
        super("Ko", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Enhvelops yourself with even more passive aura.\nAn evolved form of ten");
        this.setMaxCooldown(3);
        this.setauraCost(15);
        this.setExperience(15);
        this.setExperienceGainLevelCap(40);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        AttributeModifier kenModifier = new AttributeModifier(kenUUID, "Ken", 10 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(kenModifier);
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(kenModifier);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(kenUUID);
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(kenUUID);

        return true;
    }
}
