package com.yuanno.hunterxx.abilities.basic_hatsu;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.init.ModAttributes;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class BasicEnhancerAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final BasicEnhancerAbility INSTANCE = new BasicEnhancerAbility();

    public static final UUID enhancerAttackSpeedUUID = UUID.fromString("edd64264-4678-11ed-b878-0242ac120002");
    public static final UUID enhancerSpeedUUID = UUID.fromString("902da2d8-4678-11ed-b878-0242ac120002");
    public static final UUID enhancerJumpUUID = UUID.fromString("adfcf7b4-4678-11ed-b878-0242ac120002");
    public static final UUID enhancerSwimUUID = UUID.fromString("bdb0852c-4678-11ed-b878-0242ac120002");
    public BasicEnhancerAbility()
    {
        super("Basic enhancement", AbilityCategories.AbilityCategory.HATSU);
        this.setDescription("A basic hatsu that enhances your body");
        this.setauraCost(10);
        this.setExperience(12);
        this.setExperienceGainLevelCap(40);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEven;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        AttributeModifier enhancerAttackSpeedModifier = new AttributeModifier(enhancerAttackSpeedUUID, "enhancerattackspeed", 1 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        AttributeModifier enhancerSpeedModifier = new AttributeModifier(enhancerSpeedUUID, "enhancerspeed", 0.15 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        AttributeModifier enhancerJumpModifier = new AttributeModifier(enhancerJumpUUID, "enhancerjump", 1.25 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        AttributeModifier enhancerSwimModifier = new AttributeModifier(enhancerSwimUUID, "enhancerswim", 0.2 * Beapi.valueCategory(player, ModValues.ENHANCEMENT), AttributeModifier.Operation.ADDITION);
        player.getAttribute(Attributes.MOVEMENT_SPEED).addTransientModifier(enhancerSpeedModifier);
        player.getAttribute(ModAttributes.JUMP_HEIGHT.get()).addTransientModifier(enhancerJumpModifier);
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).addTransientModifier(enhancerSwimModifier);

        return true;
    }

    private boolean onEndContinuityEven(PlayerEntity player)
    {
        player.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(enhancerSpeedUUID);
        player.getAttribute(ModAttributes.JUMP_HEIGHT.get()).removeModifier(enhancerJumpUUID);
        player.getAttribute(ForgeMod.SWIM_SPEED.get()).removeModifier(enhancerSwimUUID);
        return true;
    }
}
