package com.yuanno.hunterxx.api.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.Cancelable;

@Cancelable
public class AbilityUseEvent extends AbilityEvent {
    public AbilityUseEvent(PlayerEntity player, Ability ability)
    {
        super(player, ability);
    }

}
