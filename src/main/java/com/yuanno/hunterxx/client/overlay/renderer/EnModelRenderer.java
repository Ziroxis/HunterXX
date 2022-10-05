package com.yuanno.hunterxx.client.overlay.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.abilities.advanced.InAbiltiy;
import com.yuanno.hunterxx.client.overlay.model.EnSphereModel;
import com.yuanno.hunterxx.data.ability.AbilityDataCapability;
import com.yuanno.hunterxx.data.ability.IAbilityData;
import com.yuanno.hunterxx.entity.EnEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class EnModelRenderer extends EntityRenderer<EnEntity>{

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID + ":textures/entity/overlay/en_texture.png");

    public EnModelRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    public void render(EnEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        IVertexBuilder vb = bufferIn.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entityIn)));
        matrixStackIn.pushPose();
        matrixStackIn.scale(20,20,20);
        matrixStackIn.translate(0, -1, 0);
        EntityModel model = new EnSphereModel();
        model.renderToBuffer(matrixStackIn, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.01f);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static class Factory implements IRenderFactory<EnEntity> {

        @Override
        public EntityRenderer<? super EnEntity> createRenderFor(EntityRendererManager manager) {
            return new EnModelRenderer(manager);
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EnEntity entity) {
        return TEXTURE;
    }
}
