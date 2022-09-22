package com.yuanno.hunterxx.data.quest.objectives;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public interface IObtainItemObjective
{
    boolean checkItem(ItemStack itemStack);

    int checkItems(ArrayList<ItemStack> stacks);

}
