package com.yuanno.hunterxx.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup MISC = new ItemGroup("hunterxx_miscTab") {

        @Override
        public ItemStack makeIcon()
        {
            return new ItemStack(ModItems.HUNTER_LICENSE.get());
        }
    };
}
