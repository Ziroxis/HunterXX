package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.nen.EntityNenTeacher;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class UnlockingNenEntityRenderer extends MobRenderer<EntityNenTeacher, HumanoidModel<EntityNenTeacher>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/unlockingnen_texture.png");

    public UnlockingNenEntityRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityNenTeacher p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityNenTeacher> {

        @Override
        public EntityRenderer<? super EntityNenTeacher> createRenderFor(EntityRendererManager manager) {
            return new UnlockingNenEntityRenderer(manager);
        }
    }
}
