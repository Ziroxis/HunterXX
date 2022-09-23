package com.yuanno.hunterxx.client.renderer;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.collecting.EntityBasicCollectingQuest6;
import com.yuanno.hunterxx.models.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.IRenderFactory;
@OnlyIn(Dist.CLIENT)
public class BasicQuestCollectingEntity6Renderer extends MobRenderer<EntityBasicCollectingQuest6, HumanoidModel<EntityBasicCollectingQuest6>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(Main.MODID, "textures/entity/npc/basicquestentitycollecting6_texture.png");

    public BasicQuestCollectingEntity6Renderer(EntityRendererManager renderManager)
    {
        super(renderManager, new HumanoidModel<>(), 0.1F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBasicCollectingQuest6 p_110775_1_) {
        return TEXTURE;
    }

    public static class Factory implements IRenderFactory<EntityBasicCollectingQuest6> {

        @Override
        public EntityRenderer<? super EntityBasicCollectingQuest6> createRenderFor(EntityRendererManager manager) {
            return new BasicQuestCollectingEntity6Renderer(manager);
        }
    }
}
