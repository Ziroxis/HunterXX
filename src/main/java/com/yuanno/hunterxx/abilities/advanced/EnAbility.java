package com.yuanno.hunterxx.abilities.advanced;

import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityCategories;
import com.yuanno.hunterxx.api.ability.interfaces.IParallelContinuousAbility;
import com.yuanno.hunterxx.api.ability.sorts.ContinuousAbility;
import com.yuanno.hunterxx.entity.EnEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.*;

public class EnAbility extends ContinuousAbility implements IParallelContinuousAbility {
    public static final EnAbility INSTANCE = new EnAbility();
    public int enSize = 0;
    public static final int MAX_ZONE_SIZE = 30;
    public static final int MAX_THRESHOLD = 2;
    private int chargingTicks = 0;
    boolean zoneSet = false;
    private EnEntity enEntity = null;

    List<LivingEntity> entitiesInEn = new ArrayList<LivingEntity>();

    public EnAbility()
    {
        super("En", AbilityCategories.AbilityCategory.ADVANCED_NEN);
        this.setDescription("Disperses your aura around you making a sphere.\nThe sphere detects entities in it");
        this.setMaxCooldown(0);
        this.setauraCost(0);
        this.setExperience(20);
        this.setExperienceGainLevelCap(30);

        this.onStartContinuityEvent = this::onStartContinuityEvent;
        this.duringContinuityEvent = this::duringContinuityEvent;
        this.onEndContinuityEvent = this::onEndContinuityEvent;
    }

    private boolean onStartContinuityEvent(PlayerEntity player)
    {
        this.enEntity = new EnEntity(player.level, player);
        this.enEntity.setPos(player.getX(), player.getY(), player.getZ());
        player.level.addFreshEntity(this.enEntity);

        return true;
    }
    private void duringContinuityEvent(PlayerEntity player, int timer)
    {
        this.enEntity.setPos(player.getX(), player.getY(), player.getZ());
        List<LivingEntity> entities = Beapi.getEntitiesAround(player.blockPosition(), player.level, 15);
        entities.removeIf((entity) -> {return !(entity instanceof MonsterEntity || entity instanceof PlayerEntity);});
        entities.remove(player);
        //entities.removeIf((entity) -> entitiesInEn.contains(entity));
        for (LivingEntity entity : entities) {
            if (!entitiesInEn.contains(entity)) {
                player.displayClientMessage(new StringTextComponent(entity.getDisplayName().getString() + " has entered your en."), false);
                entitiesInEn.add(entity);
            }
        }
        entitiesInEn.removeIf((entity) -> !(entities.contains(entity)));
    }
    private boolean onEndContinuityEvent(PlayerEntity player)
    {
        entitiesInEn.clear();
        this.enEntity.remove();
        return true;
    }
}
