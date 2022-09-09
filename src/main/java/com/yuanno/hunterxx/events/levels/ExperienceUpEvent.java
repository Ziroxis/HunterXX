package com.yuanno.hunterxx.events.levels;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ExperienceUpEvent extends PlayerEvent
{
    public int experience;

    public ExperienceUpEvent(PlayerEntity player, int experience) {
        super(player);
        this.experience = experience;
    }
}
