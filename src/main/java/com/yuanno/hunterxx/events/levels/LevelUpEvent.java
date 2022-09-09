package com.yuanno.hunterxx.events.levels;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class LevelUpEvent extends PlayerEvent
{
    public int level;

    public LevelUpEvent(PlayerEntity player, int level)
    {
        super(player);
        this.level = level;
    }
}
