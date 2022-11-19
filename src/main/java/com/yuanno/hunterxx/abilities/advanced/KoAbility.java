package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;

import java.util.UUID;

public class KoAbility extends ContinuousAbility {
    public static final KoAbility INSTANCE = new KoAbility();

    public static final UUID koUUID = UUID.fromString("63b16226-4416-11ed-b878-0242ac120002");
    //private static final AttributeModifier KO_ABILITY = new AttributeModifier(UUID.fromString("63b16226-4416-11ed-b878-0242ac120002"), "Ko", 20, AttributeModifier.Operation.ADDITION);

    public KoAbility()
    {
        super("Ko", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Focuses all your aura in your fist.\nLeaving you invulnerable for attacks.\nDealing massive damage");
        this.setMaxCooldown(3);
        this.setauraCost(10);
        this.setExperience(15);
        this.setExperienceGainLevelCap(30);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onStopContinuityEvent = this::onStopContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {

        AttributeModifier koModifier = new AttributeModifier(koUUID, "Ko", 20 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        if (player.getMainHandItem().isEmpty()) {
            if (!player.getAttribute(Attributes.ATTACK_DAMAGE).hasModifier(koModifier))
            {
                player.getAttribute(Attributes.ATTACK_DAMAGE).addTransientModifier(koModifier);
                return true;
            }
            else {
                player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(koModifier);
                return false;
            }
        }
        else
            return false;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        if (!player.getMainHandItem().isEmpty()) {
            player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(koUUID);
            this.startCooldown(player);
        }
    }


    public void onStopContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(ModAttributes.ATTACK_RANGE.get()).removeModifier(koUUID);
    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        player.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(koUUID);
        System.out.println("CHECK");
        return true;
    }
}
