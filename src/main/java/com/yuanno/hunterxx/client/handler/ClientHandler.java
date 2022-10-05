package com.yuanno.hunterxx.client.handler;

import com.yuanno.hunterxx.client.overlay.AuraLayer;
import com.yuanno.hunterxx.client.overlay.renderer.*;
import com.yuanno.hunterxx.client.renderer.*;
import com.yuanno.hunterxx.entity.questers.nen.EntityNenTeacher;
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
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY1.get(), new BasicQuestCollectingEntity1Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY2.get(), new BasicQuestCollectingEntity2Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY3.get(), new BasicQuestCollectingEntity3Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY4.get(), new BasicQuestCollectingEntity4Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY5.get(), new BasicQuestCollectingEntity5Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY6.get(), new BasicQuestCollectingEntity6Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY7.get(), new BasicQuestCollectingEntity7Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY8.get(), new BasicQuestCollectingEntity8Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY9.get(), new BasicQuestCollectingEntity9Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY10.get(), new BasicQuestCollectingEntity10Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY11.get(), new BasicQuestCollectingEntity11Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICQUESTENTITY12.get(), new BasicQuestCollectingEntity12Renderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY1.get(), new BasicQuestKillingEntity1Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY2.get(), new BasicQuestKillingEntity2Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY3.get(), new BasicQuestKillingEntity3Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY4.get(), new BasicQuestKillingEntity4Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY5.get(), new BasicQuestKillingEntity5Renderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.BASICKILLINGENTITY6.get(), new BasicQuestKillingEntity6Renderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITYLICENSEQUEST.get(), new EntityLicenseQuestRenderer.Factory());
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITYNENTEACHER.get(), new UnlockingNenEntityRenderer.Factory());

        RenderingRegistry.registerEntityRenderingHandler(ModEntities.FOXBEAR.get(), new FoxBearRenderer.Factory());
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
            layers.add(new TenModelRenderer<>(renderer));
            layers.add(new GyoModelRenderer<>(renderer));
            layers.add(new KenModelRenderer<>(renderer));
            layers.add(new KoModelRenderer<>(renderer));
        }
    }
}
