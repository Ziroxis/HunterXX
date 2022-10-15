package com.yuanno.hunterxx.commands;

import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.init.ModAbilities;
import net.minecraftforge.common.IExtensibleEnum;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public enum AbilityCommandGroup implements IExtensibleEnum
{

    BASIC_NEN(() -> ModAbilities.BASIC_NEN),
    ADVANCED_NEN(() -> ModAbilities.ADVANCED_NEN),
    BASIC_HATSU(() -> ModAbilities.BASIC_HATSU);

    private Supplier<Ability[]> abilities;

    private AbilityCommandGroup(Supplier<Ability[]> abilities)
    {
        this.abilities = abilities;
    }

    public List<Ability> getAbilities()
    {
        return Arrays.asList(this.abilities.get());
    }

    public static AbilityCommandGroup create(String name, Supplier<Ability[]> abilities)
    {
        throw new IllegalStateException("Enum not extended");
    }
}
