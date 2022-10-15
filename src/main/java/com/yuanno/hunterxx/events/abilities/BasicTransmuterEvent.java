package com.yuanno.hunterxx.events.abilities;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicTransmuterAbility;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class BasicTransmuterEvent {

    @SubscribeEvent
    public static void transmutationEvent(LivingHurtEvent event)
    {
        if (!(event.getEntityLiving() instanceof PlayerEntity) || !(event.getSource().getDirectEntity() instanceof LivingEntity))
            return;
        PlayerEntity player = (PlayerEntity) event.getEntityLiving();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        BasicTransmuterAbility basicTransmuterAbility = abilityData.getEquippedAbility(BasicTransmuterAbility.INSTANCE);
        if (basicTransmuterAbility != null && basicTransmuterAbility.isContinuous())
        {
            event.getSource().getEntity().hurt(DamageSource.thorns(player), (float) (Beapi.valueCategory(player, ModValues.TRANSMUTATION) * 6));
        }
    }
}
