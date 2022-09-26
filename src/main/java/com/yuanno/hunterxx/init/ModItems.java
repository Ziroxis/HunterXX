package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.items.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);
    public List<Supplier<Item>> items = new ArrayList<>();


    //SPAWN EGGS
    public static final RegistryObject<SpawnEggItem> FOXBEAR_EGG = ITEMS.register("foxbear_egg",
            () -> new ModSpawnEggItem(ModEntities.FOXBEAR, 0x879995, 0x576ABC, new Item.Properties().tab(ModItemGroup.MISC)));

}
