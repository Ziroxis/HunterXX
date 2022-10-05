package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolItem;

import java.util.UUID;

public class ShuAbility extends ContinuousAbility implements IParallelContinuousAbility {

    public static final ShuAbility INSTANCE = new ShuAbility();
    private static final AttributeModifier SHU_ABILITY = new AttributeModifier(UUID.fromString("3e1280a6-440a-11ed-b878-0242ac120002"),
            "Shu", 5, AttributeModifier.Operation.ADDITION);

    public ShuAbility()
    {
        super("Shu", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Envelops your tool with aura.\nReinforcing it");
        this.setMaxCooldown(3);
        this.setauraCost(10);
        this.setExperience(10);
        this.setExperience(10);
        this.setExperienceGainLevelCap(30);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }
    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        return player.getMainHandItem().getItem() instanceof ToolItem;
    }

    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        if (!(player.getMainHandItem().getItem() instanceof ToolItem))
            this.startCooldown(player);

    }

    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        return true;
    }
}
