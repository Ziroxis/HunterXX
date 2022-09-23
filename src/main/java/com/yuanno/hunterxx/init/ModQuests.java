package com.yuanno.hunterxx.init;

import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.Quest.Objective;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.Quest.QuestRegistry;
import com.yuanno.hunterxx.quests.basic.collecting.*;
import com.yuanno.hunterxx.quests.basic.killing.*;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;

public class ModQuests {

    private static HashMap<String, String> langMap = new HashMap<String, String>();

    public static HashMap<String, String> getLangMap()
    {
        return langMap;
    }
    public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(QuestRegistry.QUESTS, Main.MODID);

    public static final Quest BASICCOLLECTINGQUEST1 = new BasicCollectingQuest01();
    public static final Quest BASICCOLLECTINGQUEST2 = new BasicCollectingQuest02();
    public static final Quest BASICCOLLECTINGQUEST3 = new BasicCollectingQuest03();
    public static final Quest BASICCOLLECTINGQUEST4 = new BasicCollectingQuest04();
    public static final Quest BASICCOLLECTINGQUEST5 = new BasicCollectingQuest05();
    public static final Quest BASICCOLLECTINGQUEST6 = new BasicCollectingQuest06();
    public static final Quest BASICCOLLECTINGQUEST7 = new BasicCollectingQuest07();
    public static final Quest BASICCOLLECTINGQUEST8 = new BasicCollectingQuest08();
    public static final Quest BASICCOLLECTINGQUEST9 = new BasicCollectingQuest09();
    public static final Quest BASICCOLLECTINGQUEST10 = new BasicCollectingQuest10();
    public static final Quest BASICCOLLECTINGQUEST11 = new BasicCollectingQuest11();
    public static final Quest BASICCOLLECTINGQUEST12 = new BasicCollectingQuest12();

    public static final Quest[] BASICCOLLECTINGQUEST = new Quest[] {BASICCOLLECTINGQUEST1, BASICCOLLECTINGQUEST2, BASICCOLLECTINGQUEST3, BASICCOLLECTINGQUEST4, BASICCOLLECTINGQUEST5, BASICCOLLECTINGQUEST6, BASICCOLLECTINGQUEST7,
    BASICCOLLECTINGQUEST8, BASICCOLLECTINGQUEST9, BASICCOLLECTINGQUEST10, BASICCOLLECTINGQUEST11, BASICCOLLECTINGQUEST12};

    public static final Quest BASICKILLINGQUEST1 = new BasicKillingQuest01();
    public static final Quest BASICKILLINGQUEST2 = new BasicKillingQuest02();
    public static final Quest BASICKILLINGQUEST3 = new BasicKillingQuest03();
    public static final Quest BASICKILLINGQUEST4 = new BasicKillingQuest04();
    public static final Quest BASICKILLINGQUEST5 = new BasicKillingQuest05();
    public static final Quest BASICKILLINGQUEST6 = new BasicKillingQuest06();

    public static final Quest[] BASICKILLINGQUEST = new Quest[] {BASICKILLINGQUEST1, BASICKILLINGQUEST2, BASICKILLINGQUEST3, BASICKILLINGQUEST4, BASICKILLINGQUEST5, BASICKILLINGQUEST6};

    static
    {
        for (Quest quest : BASICCOLLECTINGQUEST)
        {
            String resourceName = Beapi.getResourceName(quest.getId());
            langMap.put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

            for(Objective obj : quest.getObjectives())
            {
                langMap.put("quest.objective." + Main.MODID + "." + obj.getId(), obj.getTitle());
            }

            QUESTS.register(resourceName, () -> quest);

        }

        for (Quest quest : BASICKILLINGQUEST)
        {
            String resourceName = Beapi.getResourceName(quest.getId());
            langMap.put("quest." + Main.MODID + "." + resourceName, quest.getTitle());

            for(Objective obj : quest.getObjectives())
            {
                langMap.put("quest.objective." + Main.MODID + "." + obj.getId(), obj.getTitle());
            }

            QUESTS.register(resourceName, () -> quest);

        }
    }

}
