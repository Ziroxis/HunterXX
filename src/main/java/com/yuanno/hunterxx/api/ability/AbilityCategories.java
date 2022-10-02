package com.yuanno.hunterxx.api.ability;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IExtensibleEnum;

import javax.annotation.Nullable;
import java.util.function.Function;

public class AbilityCategories {


    private static final Function<PlayerEntity, ResourceLocation> GET_BASIC_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/icons/basic.png");

        return icon;
    };
    private static final Function<PlayerEntity, ResourceLocation> GET_ADVANCED_ICON = (player) ->
    {
        IEntityStats props = EntityStatsCapability.get(player);
        ResourceLocation icon = null;
        icon = new ResourceLocation(Main.MODID, "textures/gui/icons/advanced.png");

        return icon;
    };

    public static enum AbilityCategory implements IExtensibleEnum
    {
        HATSU,
        BASIC_NEN(GET_BASIC_ICON),
        ADVANCED_NEN(GET_ADVANCED_ICON),
        ALL;

        private Function<PlayerEntity, ResourceLocation> iconFunction;
        private Function<PlayerEntity, ResourceLocation> secondIconFunction;

        private AbilityCategory()
        {
            this.iconFunction = null;
            this.secondIconFunction = null;
        }

        private AbilityCategory(Function<PlayerEntity, ResourceLocation> function)
        {
            this.iconFunction = function;
        }


        private AbilityCategory(Function<PlayerEntity, ResourceLocation> getAttributeIcon, Function<PlayerEntity, ResourceLocation> getSecondAttributeIcon)
        {
            this.iconFunction = getAttributeIcon;
            this.secondIconFunction = getSecondAttributeIcon;
        }



        @Nullable
        public ResourceLocation getIcon(PlayerEntity player)
        {
            if(this.iconFunction == null)
                return null;
            return this.iconFunction.apply(player);
        }
        @Nullable
        public ResourceLocation getSecondIcon(PlayerEntity player)
        {

            if(this.secondIconFunction == null)
                return null;
            return this.secondIconFunction.apply(player);
        }

        public static AbilityCategory create(String name, Function<PlayerEntity, ResourceLocation> function)
        {
            throw new IllegalStateException("Enum not extended");
        }
    }


}
