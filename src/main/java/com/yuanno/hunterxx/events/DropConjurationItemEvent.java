package com.yuanno.hunterxx.events;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicConjurerAbility;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DropConjurationItemEvent {

    @SubscribeEvent
    public static void onConjuredItemDropEvent(ItemTossEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        BasicConjurerAbility basicConjurerAbility = abilityData.getEquippedAbility(BasicConjurerAbility.INSTANCE);

        System.out.println(event.getEntityItem().getItem().getTag());
        if (basicConjurerAbility != null && basicConjurerAbility.isContinuous()) {

            if (Objects.requireNonNull(event.getEntityItem().getItem().getTag()).contains("conjuration")) {
                basicConjurerAbility.startCooldown(player);
                event.getEntityItem().remove();
            }
        }
    }
}
