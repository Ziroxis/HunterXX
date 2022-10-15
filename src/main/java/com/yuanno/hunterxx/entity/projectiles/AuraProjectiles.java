package com.yuanno.hunterxx.entity.projectiles;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.AbilityProjectileRenderer;
import com.yuanno.hunterxx.models.projectiles.AuraBlastModel;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AuraProjectiles {

    public static final RegistryObject<EntityType<AuraBlastProjectile>> AURA_BLAST = Beapi.registerEntityType("Aura Blast",
            () -> Beapi.createEntityType(AuraBlastProjectile::new)
                    .sized(0.7f, 0.7f)
                    .build(Main.MODID + ":aura_blast"));

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void registerEntityRenderers(FMLClientSetupEvent event)
    {
        RenderingRegistry.registerEntityRenderingHandler(AURA_BLAST.get(), new AbilityProjectileRenderer.Factory(new AuraBlastModel())
                .setTexture(ResourceLocation.tryParse(Main.MODID + ":textures/entity/projectiles/aurablast_texture.png")));
    }
}
