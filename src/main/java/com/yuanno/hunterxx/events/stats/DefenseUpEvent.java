package com.yuanno.hunterxx.events.stats;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class DefenseUpEvent extends PlayerEvent {
    public int defense;

    public DefenseUpEvent(PlayerEntity player, int defense)
    {
        super(player);
        this.defense = defense;
    }
}
