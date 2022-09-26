package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.killing.EntityBasicKillingQuest2;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BasicQuestKillingEntity2Renderer extends MobRenderer<EntityBasicKillingQuest2, HumanoidModel<EntityBasicKillingQuest2>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitykilling2_texture.png");

    public BasicQuestKillingEntity2Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicKillingQuest2 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicKillingQuest2> {

        @Override
        public EntityRenderer<? super EntityBasicKillingQuest2> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestKillingEntity2Renderer(manager);
        }
    }
}
