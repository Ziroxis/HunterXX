package com.yuanno.hunterxx.client.overlay;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.hunterxx.abilities.advanced.*;
import com.yuanno.hunterxx.abilities.basic.TenAbility;
import com.yuanno.hunterxx.abilities.basic.ZetsuAbility;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.ability.Ability;
import com.yuanno.hunterxx.client.overlay.model.GyoModel;
import com.yuanno.hunterxx.client.overlay.model.KenModel;
import com.yuanno.hunterxx.client.overlay.model.KoModel;
import com.yuanno.hunterxx.client.overlay.model.TenModel;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.init.ModRenderTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.awt.*;

public class AuraLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
    private GyoModel gyoModel = new GyoModel();
    private KenModel kenModel = new KenModel();
    private KoModel koModel = new KoModel();
    private TenModel tenModel = new TenModel();




    public AuraLayer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }

    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        //TODO make it so gyo activated causes you to see all the stuff
        PlayerEntity player = Minecraft.getInstance().player;

        if (entity == player)
            return;

        IAbilityData abilityData = AbilityDataCapability.get(player);
        Ability gyoAbility = abilityData.getEquippedAbility(GyoAbility.INSTANCE);
        if (gyoAbility != null && gyoAbility.isContinuous())
        {
            if (entity.distanceTo(player) > 32)
                return;

            if (entity instanceof PlayerEntity)
            {
                PlayerEntity targetEntity = (PlayerEntity) entity;
                IAbilityData abilityDataTarget = AbilityDataCapability.get(targetEntity);
                ZetsuAbility zetsuAbility = abilityDataTarget.getEquippedAbility(ZetsuAbility.INSTANCE);

                if (zetsuAbility != null && zetsuAbility.isContinuous())
                    return;
            }

            matrixStack.pushPose();

            String color = "#5555FF";

            if (entity instanceof AnimalEntity)
                color = "#55FF55";
            else if (entity instanceof MonsterEntity)
                color = "#FF0000";
            else if (entity instanceof PlayerEntity)
                color = "#00FFFF";

            OutlineLayerBuffer outline = Minecraft.getInstance().renderBuffers().outlineBufferSource();
            Color rgbColor = Beapi.hexToRGB(color);
            float red = rgbColor.getRed() / 255.0f;
            float green = rgbColor.getGreen() / 255.0f;
            float blue = rgbColor.getBlue() / 255.0f;
            outline.setColor((int)(red * 255), (int)(green * 255), (int)(blue * 255), 200);
            IVertexBuilder vertex = outline.getBuffer(ModRenderTypes.getAuraRenderType(this.getTextureLocation(entity)));

            this.getParentModel().renderToBuffer(matrixStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, red, green, blue, 0.6f);

            matrixStack.popPose();

        }
    }
}
