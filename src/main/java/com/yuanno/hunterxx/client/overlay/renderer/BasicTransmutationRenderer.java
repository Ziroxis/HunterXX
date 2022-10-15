package com.yuanno.hunterxx.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.basic_hatsu.BasicTransmuterAbility;
import com.yuanno.hunterxx.client.overlay.model.BasicTransmutationModel;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

public class BasicTransmutationRenderer <T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entity/overlay/basictransmutation_texture.png");

    private BasicTransmutationModel model = new BasicTransmutationModel();
    
    public BasicTransmutationRenderer(IEntityRenderer<T, M> entityRenderer)
    {
        super(entityRenderer);
    }
    
    @Override
    public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, T entityLivingBase,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch)
    {
        IAbilityData abilityData = AbilityDataCapability.get(entityLivingBase);
        BasicTransmuterAbility basicTransmutationAbility = abilityData.getEquippedAbility(BasicTransmuterAbility.INSTANCE);
        if (basicTransmutationAbility != null && basicTransmutationAbility.isContinuous())
        {
            matrixStack.pushPose();
            this.getParentModel().copyPropertiesTo(this.model);
            this.model.setupAnim(entityLivingBase, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            IVertexBuilder ivertexbuilder = ItemRenderer.getFoilBuffer(buffer, this.model.renderType(TEXTURE), false, false);
            this.model.renderToBuffer(matrixStack, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.popPose();
        }
    }
}
