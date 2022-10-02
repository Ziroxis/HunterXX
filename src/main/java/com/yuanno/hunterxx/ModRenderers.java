package com.yuanno.hunterxx;

import com.yuanno.hunterxx.client.overlay.AuraLayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.entity.EntityType;

import java.util.Map;

public class ModRenderers {

    public static void registerRenderers()
    {
        for (Map.Entry<EntityType<?>, EntityRenderer<?>> entry : Minecraft.getInstance().getEntityRenderDispatcher().renderers.entrySet())
        {
            EntityRenderer entityRenderer = entry.getValue();
            if (entityRenderer instanceof LivingRenderer)
            {
                LivingRenderer renderer = (LivingRenderer) entityRenderer;
                renderer.addLayer(new AuraLayer(renderer));
            }
        }

        for (Map.Entry<String, PlayerRenderer> entry : Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap().entrySet())
        {
            PlayerRenderer renderer = entry.getValue();
            renderer.addLayer(new AuraLayer(renderer));
        }
    }
}
