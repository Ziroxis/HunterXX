package com.yuanno.hunterxx.events.ability;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.events.levels.ExperienceUpEvent;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class AbilityProgressionEvents {

    @SubscribeEvent
    public static void onLevelGained(ExperienceUpEvent event)
    {
        IEntityStats statsProps = EntityStatsCapability.get(event.getPlayer());

    }

    private static void gainAbility(PlayerEntity player, int level, Ability ability)
    {
        IEntityStats props = EntityStatsCapability.get(player);
        IAbilityData abilityProps = AbilityDataCapability.get(player);

        if (props.getLevel() >= level && !abilityProps.hasUnlockedAbility(ability) )
        {
            abilityProps.addUnlockedAbility(ability);
        }
        if ((props.getLevel() < level && abilityProps.hasUnlockedAbility(ability)))
            abilityProps.removeUnlockedAbility(ability);

        PacketHandler.sendTo(new SSyncAbilityDataPacket(player.getId(), abilityProps), player);
    }
}
