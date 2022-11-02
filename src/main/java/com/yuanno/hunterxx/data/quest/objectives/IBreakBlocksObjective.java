package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;

public interface IBreakBlocksObjective {

    boolean checkBreakBlock(PlayerEntity player, Block block);
}
