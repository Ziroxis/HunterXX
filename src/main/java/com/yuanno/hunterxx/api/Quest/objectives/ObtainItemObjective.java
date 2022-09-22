package com.yuanno.hunterxx.api.Quest.objectives;

import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.data.quest.objectives.IObtainItemObjective;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class ObtainItemObjective extends Objective implements IObtainItemObjective
{
    private Predicate<ItemStack> check = (itemStack) -> false;

    public ObtainItemObjective(String title, int count, Item itemTarget)
    {
        super(title);
        this.setMaxProgress(count);
        this.check = (itemStack) -> itemStack.getItem() == itemTarget;
    }

    public ObtainItemObjective(String title, int count, Predicate<ItemStack> check)
    {
        super(title);
        this.setMaxProgress(count);
        this.check = check;
    }

    @Override
    public boolean checkItem(ItemStack stack)
    {
        return this.check.test(stack);
    }
}
