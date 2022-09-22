package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.questers.basic.EntityBasicQuest1;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<EntityBasicQuest1>> BASICQUESTENTITY1 = ENTITIES
            .register("basicquestentity1",
                    () -> EntityType.Builder.of(EntityBasicQuest1::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity1").toString()));

}
