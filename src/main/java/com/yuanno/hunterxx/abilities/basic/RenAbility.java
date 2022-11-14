package com.yuanno.hunterxx.abilities.basic;

import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.init.ModAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class RenAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final RenAbility INSTANCE = new RenAbility();

    private static final AttributeModifier REN_ABILITY = new AttributeModifier(UUID.fromString("9d5dcf38-428a-11ed-b878-0242ac120002"),
            "Ren", 5, AttributeModifier.Operation.ADDITION);

    public RenAbility() {
        super("Ren", AbilityCategories.AbilityCategory.BASIC_NEN);
        this.setDescription("Puts out a huge amount of aura");
        this.setMaxCooldown(3);
        this.setauraCost(8);
        this.setExperience(10);
        this.setExperienceGainLevelCap(20);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        IAbilityData abilityData = AbilityDataCapability.get(player);
        TenAbility tenAbility = abilityData.getEquippedAbility(TenAbility.INSTANCE);
        if (tenAbility != null && tenAbility.isContinuous())
            tenAbility.cooldown(player);
        ZetsuAbility zetsuAbility = abilityData.getEquippedAbility(ZetsuAbility.INSTANCE);
        if (zetsuAbility != null && zetsuAbility.isContinuous())
            return false;
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(REN_ABILITY);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(REN_ABILITY);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(REN_ABILITY);
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(REN_ABILITY);

        return true;
    }
}
