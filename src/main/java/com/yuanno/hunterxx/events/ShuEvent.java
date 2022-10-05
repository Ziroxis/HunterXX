package com.yuanno.hunterxx.events;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.advanced.ShuAbility;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.init.ModValues;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ToolItem;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ShuEvent {

    @SubscribeEvent
    public static void digSpeed(PlayerEvent.BreakSpeed event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ShuAbility shuAbility = abilityData.getEquippedAbility(ShuAbility.INSTANCE);
        if (shuAbility != null && shuAbility.isContinuous())
        {
            if (player.getMainHandItem().getItem() instanceof ToolItem)
            {
                event.setNewSpeed((float) (event.getOriginalSpeed() + 5 * Beapi.valueCategory(player, ModValues.ENHANCEMENT)));
            }
        }
    }

    @SubscribeEvent
    public static void digBreaking(BlockEvent.BreakEvent event)
    {
        PlayerEntity player = event.getPlayer();
        IAbilityData abilityData = AbilityDataCapability.get(player);
        ShuAbility shuAbility = abilityData.getEquippedAbility(ShuAbility.INSTANCE);
        if (shuAbility != null && shuAbility.isContinuous())
        {
            if (player.getMainHandItem().getItem() instanceof ToolItem)
            {
                if (player.getMainHandItem().isDamaged())
                {
                    System.out.println(player.getMainHandItem().getDamageValue());
                    player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() - 1);
                    System.out.println(player.getMainHandItem().getDamageValue());
                }

            }
        }
    }
}
