package com.yuanno.hunterxx.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class StrengthUpEvent extends PlayerEvent {
    public int strength;

    public StrengthUpEvent(PlayerEntity player, int strength)
    {
        super(player);
        this.strength = strength;
    }
}
