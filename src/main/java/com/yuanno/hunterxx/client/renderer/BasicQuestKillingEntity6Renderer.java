package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.killing.EntityBasicKillingQuest6;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;

@OnlyIn(Dist.CLIENT)
public class BasicQuestKillingEntity6Renderer extends MobRenderer<EntityBasicKillingQuest6, HumanoidModel<EntityBasicKillingQuest6>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitykilling6_texture.png");

    public BasicQuestKillingEntity6Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicKillingQuest6 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicKillingQuest6> {

        @Override
        public EntityRenderer<? super EntityBasicKillingQuest6> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestKillingEntity6Renderer(manager);
        }
    }
}
