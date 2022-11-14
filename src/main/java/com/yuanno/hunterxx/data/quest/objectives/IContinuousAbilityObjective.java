package com.yuanno.hunterxx.data.quest.objectives;

import com.yuanno.hunterxx.api.ability.Ability;
import net.minecraft.entity.player.PlayerEntity;

import javax.swing.text.PlainDocument;

public interface IContinuousAbilityObjective {

    boolean checkContinuesTimeAbility(PlayerEntity player);
}
