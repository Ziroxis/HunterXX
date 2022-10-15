package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.effects.MovementBlockedEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects {

    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MODID);

    public static final RegistryObject<Effect> MOVEMENT_BLOCKED = EFFECTS.register("movement_blocked", MovementBlockedEffect::new);


}
