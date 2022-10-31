package com.yuanno.hunterxx.abilities.hatsu.jajanken;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class JajankenScissorsAbility extends ContinuousAbility {

    public static final JajankenScissorsAbility INSTANCE = new JajankenScissorsAbility();

    public static final UUID jajankenscissorsDamageUUID = UUID.fromString("e53f5600-587e-11ed-9b6a-0242ac120002");
    public static final UUID jajankenscissorsReachUUID = UUID.fromString("e74ba052-587e-11ed-9b6a-0242ac120002");

    public JajankenScissorsAbility()
    {
        super("Jajanken scissors", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("Envelops your right fist with a blade like aura");
        this.setauraCost(10);
        this.setMaxCooldown(5);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        AttributeModifier jajankenscissorsDamageModifier = new AttributeModifier(jajankenscissorsDamageUUID, "jajankenscissors damage", 10 * Beapi.valueCategory(player, ModValues.TRANSMUTATION), AttributeModifier.Operation.ADDITION);
        AttributeModifier jajankenscissorsReachModifier = new AttributeModifier(jajankenscissorsReachUUID, "jajankenscissors reach", 8 * Beapi.valueCategory(player, ModValues.TRANSMUTATION), AttributeModifier.Operation.ADDITION);
        player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(jajankenscissorsDamageModifier);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).addTransientModifier(jajankenscissorsReachModifier);

        return true;
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(jajankenscissorsDamageUUID);
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(jajankenscissorsReachUUID);

        return true;
    }
}
