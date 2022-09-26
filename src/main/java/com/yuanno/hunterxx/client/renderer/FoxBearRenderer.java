package com.yuanno.hunterxx.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.hostile.FoxBearEntity;
import com.yuanno.hunterxx.models.entities.FoxBearModel;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class FoxBearRenderer extends MobRenderer<FoxBearEntity, FoxBearModel<FoxBearEntity>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/hostile/foxbear_texture.png");

    public FoxBearRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FoxBearModel<>(), 1f);
    }

    @Override
    public void render(FoxBearEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }


    public static class Factory implements IRenderFactory<FoxBearEntity> {

        @Override
        public EntityRenderer<? super FoxBearEntity> createRenderFor(EntityRendererManager manager) {
            return new FoxBearRenderer(manager);
        }
    }


    @Override
    public ResourceLocation getTextureLocation(FoxBearEntity entity) {
        return TEXTURE;
    }
}
