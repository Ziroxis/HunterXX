package com.yuanno.hunterxx.client.handler;

import com.yuanno.hunterxx.client.renderer.BasicQuestEntity1Renderer;
import com.yuanno.hunterxx.entity.questers.basic.EntityBasicQuest1;
import com.yuanno.hunterxx.init.ModEntities;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class ClientHandler {

    public static void onSetup()
    {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY1.get(), new BasicQuestEntity1Renderer.Factory());
        Map<String, PlayerRenderer> playerSkinMap = Minecraft.getInstance().getEntityRenderDispatcher().getSkinMap();
        ClientHandler.addPlayerLayers(playerSkinMap.get("default"));
        ClientHandler.addPlayerLayers(playerSkinMap.get("slim"));

    }

    @OnlyIn(Dist.CLIENT)
    public static void addPlayerLayers(PlayerRenderer renderer)
    {
        List<LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>> layers = ObfuscationReflectionHelper.getPrivateValue(LivingRenderer.class, renderer, "field_177097_h");
        if(layers != null)
        {

        }
    }
}
