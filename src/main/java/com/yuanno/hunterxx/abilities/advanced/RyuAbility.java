package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import org.w3c.dom.Attr;

import java.util.UUID;

public class RyuAbility extends ContinuousAbility {
    public static final RyuAbility INSTANCE = new RyuAbility();

    public static final UUID ryuUUIDOffense = UUID.fromString("7b975efc-44e5-11ed-b878-0242ac120002");
    public static final UUID getRyuUUIDDefense = UUID.fromString("02f9b372-44e6-11ed-b878-0242ac120002");
    public RyuAbility()
    {
        super("Kyu", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Mainly focuses ones aura in the fist.\nRest of the aura is used to protect yourself");
        this.setMaxCooldown(3);
        this.setauraCost(15);
        this.setExperience(15);
        this.setExperienceGainLevelCap(50);

    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        AttributeModifier ryuModifierOffense = new AttributeModifier(ryuUUIDOffense, "Ryu_offense", 16 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        AttributeModifier ryuModifierDefense = new AttributeModifier(getRyuUUIDDefense, "Ryu_defense", 4 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(ryuModifierOffense);
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).addTransientModifier(ryuModifierDefense);
        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ryuUUIDOffense);
        player.getAttribute(ModAttributes.DAMAGE_REDUCTION.get()).removeModifier(getRyuUUIDDefense);
        return true;
    }
}
