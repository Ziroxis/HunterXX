package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.entity.hostile.FoxBearEntity;
import com.yuanno.hunterxx.entity.questers.basic.collecting.*;
import com.yuanno.hunterxx.entity.questers.basic.killing.*;
import com.yuanno.hunterxx.entity.questers.license.EntityLicenseQuest;
import com.yuanno.hunterxx.entity.questers.nen.AdvancedNenTeacher;
import com.yuanno.hunterxx.entity.questers.nen.EntityNenTeacher;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MODID);

    public static final RegistryObject<EntityType<EntityBasicCollectingQuest1>> BASICQUESTENTITY1 = ENTITIES
            .register("basicquestentity1",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest1::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity1").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest2>> BASICQUESTENTITY2 = ENTITIES
            .register("basicquestentity2",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest2::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity2").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest3>> BASICQUESTENTITY3 = ENTITIES
            .register("basicquestentity3",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest3::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity3").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest4>> BASICQUESTENTITY4 = ENTITIES
            .register("basicquestentity4",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest4::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity4").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest5>> BASICQUESTENTITY5 = ENTITIES
            .register("basicquestentity5",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest5::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity5").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest6>> BASICQUESTENTITY6 = ENTITIES
            .register("basicquestentity6",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest6::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity6").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest7>> BASICQUESTENTITY7 = ENTITIES
            .register("basicquestentity7",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest7::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity7").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest8>> BASICQUESTENTITY8 = ENTITIES
            .register("basicquestentity8",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest8::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity8").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest9>> BASICQUESTENTITY9 = ENTITIES
            .register("basicquestentity9",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest9::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity9").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest10>> BASICQUESTENTITY10 = ENTITIES
            .register("basicquestentity10",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest10::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity10").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest11>> BASICQUESTENTITY11 = ENTITIES
            .register("basicquestentity11",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest11::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity11").toString()));
    public static final RegistryObject<EntityType<EntityBasicCollectingQuest12>> BASICQUESTENTITY12 = ENTITIES
            .register("basicquestentity12",
                    () -> EntityType.Builder.of(EntityBasicCollectingQuest12::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity12").toString()));


    public static final RegistryObject<EntityType<EntityBasicKillingQuest1>> BASICKILLINGENTITY1 = ENTITIES
            .register("basickillingquestentity1",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest1::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity1").toString()));
    public static final RegistryObject<EntityType<EntityBasicKillingQuest2>> BASICKILLINGENTITY2 = ENTITIES
            .register("basickillingquestentity2",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest2::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basicquestentity2").toString()));
    public static final RegistryObject<EntityType<EntityBasicKillingQuest3>> BASICKILLINGENTITY3 = ENTITIES
            .register("basickillingquestentity3",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest3::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basickillingquestentity3").toString()));
    public static final RegistryObject<EntityType<EntityBasicKillingQuest4>> BASICKILLINGENTITY4 = ENTITIES
            .register("basickillingquestentity4",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest4::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basickillingquestentity4").toString()));
    public static final RegistryObject<EntityType<EntityBasicKillingQuest5>> BASICKILLINGENTITY5 = ENTITIES
            .register("basickillingquestentity5",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest5::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basickillingquestentity5").toString()));
    public static final RegistryObject<EntityType<EntityBasicKillingQuest6>> BASICKILLINGENTITY6 = ENTITIES
            .register("basickillingquestentity6",
                    () -> EntityType.Builder.of(EntityBasicKillingQuest6::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "basickillingquestentity6").toString()));
    public static final RegistryObject<EntityType<EntityLicenseQuest>> ENTITYLICENSEQUEST = ENTITIES
            .register("entitylicensequest",
                    () -> EntityType.Builder.of(EntityLicenseQuest::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "entitylicensequest").toString()));

    public static final RegistryObject<EntityType<EntityNenTeacher>> ENTITYNENTEACHER = ENTITIES
            .register("entitynenteacher",
                    () -> EntityType.Builder.of(EntityNenTeacher::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "entitynenteacher").toString()));

    public static final RegistryObject<EntityType<AdvancedNenTeacher>> ADVANCEDNENTEACHER = ENTITIES
            .register("advancednenteacher",
                    () -> EntityType.Builder.of(AdvancedNenTeacher::new, EntityClassification.CREATURE)
                            .sized(1f, 2f)
                            .setTrackingRange(5)
                            .build(new ResourceLocation(Main.MODID, "advancednenteacher").toString()));

    public static final RegistryObject<EntityType<FoxBearEntity>> FOXBEAR = ENTITIES
            .register("foxbear",
                    () -> EntityType.Builder.of(FoxBearEntity::new, EntityClassification.CREATURE)
                            .sized(0.7f, 3)
                            .setTrackingRange(10)
                            .build(new ResourceLocation(Main.MODID, "foxbear").toString()));

}
