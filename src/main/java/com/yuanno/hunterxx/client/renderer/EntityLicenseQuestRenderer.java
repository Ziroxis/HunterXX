package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.license.EntityLicenseQuest;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class EntityLicenseQuestRenderer extends MobRenderer<EntityLicenseQuest, HumanoidModel<EntityLicenseQuest>> {
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/licensequest_texture.png");

    public EntityLicenseQuestRenderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityLicenseQuest p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityLicenseQuest> {

        @Override
        public EntityRenderer<? super EntityLicenseQuest> createRenderFor(EntityRendererManager manager) {
            return new EntityLicenseQuestRenderer(manager);
        }
    }
}
