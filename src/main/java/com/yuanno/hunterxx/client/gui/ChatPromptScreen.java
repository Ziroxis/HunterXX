package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.SequencedString;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.init.ModQuests;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CRequestSyncQuestDataPacket;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
import com.yuanno.hunterxx.networking.client.CUpdateQuestStatePacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.HashSet;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
    private final ResourceLocation declineButton = new ResourceLocation(Main.MODID, "textures/gui/questdeclinebutton.png");
    private final ResourceLocation acceptButton = new ResourceLocation(Main.MODID, "textures/gui/questacceptbutton.png");

    private int state = 0;
    private int questInQuestion = 0;
    private QuesterEntity questerEntity;

    private IQuestData questData;
    private int guiLeft;
    private int guiTop;
    private final int xSize = 64;
    private final int ySize = 58;
    private PlayerEntity player;
    private SequencedString message = new SequencedString("", 0, 0);
    TexturedIconButton test;

    TexturedIconButton acceptbutton;
    TexturedIconButton declinebutton;

    public ChatPromptScreen(PlayerEntity player, QuesterEntity questerEntity)
    {
        super(new StringTextComponent(""));
        this.questerEntity = questerEntity;
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        assert this.player != null;
        this.questData = QuestDataCapability.get(player);

    }

    @Override
    public void init()
    {
        super.init();
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());
        PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
        Quest questHaveToDo = questerEntity.questPreviouslyDone;
        Quest npcQuest = questerEntity.questGiving;
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.loop(posX, posY);
        this.addButton(test);
        this.addButton(acceptbutton);
        this.addButton(declinebutton);

    }
    public void loop(int posX, int posY)
    {
        System.out.println(questData.getFinishedQuests());
        System.out.println(questerEntity.questList);
        long amount = questData.getFinishedQuests().stream().filter(element -> questerEntity.questList.contains(element)).count();

        Quest firstQuest = questerEntity.questList.iterator().next();
        if (!questData.getFinishedQuests().contains(firstQuest))
        {
            this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
            test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
            {
                //just a button to remove the giant black square
            });
            acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.questData.addInProgressQuest(firstQuest);
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(firstQuest));
                PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                this.state = 1;
                this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            }); // -> accepting the quest
            declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = -1;
            }); // -> declining the quest
            return;
        }
        else if (questerEntity.questList.size() > amount)
        {
            this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
            test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
            {
                //just a button to remove the giant black square
            });
            acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.questData.addInProgressQuest(questerEntity.questList.get((int) amount - 1));
                PacketHandler.sendToServer(new CUpdateQuestStatePacket(questerEntity.questList.get((int) amount - 1)));
                PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                this.state = 1;
                this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            }); // -> accepting the quest
            declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = -1;
            }); // -> declining the quest
            return;
        }
        else
        {
            this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.doneSpeech) / 2, 2000);
            return;
        }



        /*
        for (int i = 0; i < questerEntity.questList.size(); i++)
        {
            if (questData.getFinishedQuests().containsAll(questerEntity.questList))
            {
                this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.doneSpeech) / 2, 2000);
                return;
            }
            Quest quest = questerEntity.questList.get(i);
            if (questData.getFinishedQuests().size() == 0)
            {
                if (questData.countInProgressQuests() == 0)
                {
                    this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                    test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        //just a button to remove the giant black square
                    });
                    int finalI = i;
                    acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        this.questData.addInProgressQuest(questerEntity.questList.get(finalI));
                        PacketHandler.sendToServer(new CUpdateQuestStatePacket(questerEntity.questList.get(finalI)));
                        PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                        this.state = 1;
                        this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                    }); // -> accepting the quest
                    declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                        this.state = -1;
                    }); // -> declining the quest
                    return;
                }
            }
            for (int ic = 0; ic < questData.getInProgressQuests().length; ic++)
            {
                if (questData.getInProgressQuest(ic) != null && questData.getInProgressQuest(ic).getId().equals(questerEntity.questList.get(i).getId()))
                {
                    this.message = new SequencedString(questerEntity.ongoingSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                    break;
                }
            }
            for (int ia = 0; ia < questData.getFinishedQuests().size(); ia++)
            {
                System.out.println("Check 3");
                if (questData.getFinishedQuests().get(ia) != null && !(questData.getFinishedQuests().get(ia).getId().equals(questerEntity.questList.get(i).getId())))
                {
                    System.out.println("Check 4");
                    // if a quest in the list of the questerEntity is NOT done
                    if (questData.countInProgressQuests() > 0)
                    {
                        for (int ib = 0; ib < questData.countInProgressQuests(); ib++) {
                            {
                                System.out.println("Check 5");
                                if (questData.getInProgressQuest(ib) != null && !(questData.getInProgressQuest(ib).getId().equals(questerEntity.questList.get(i).getId()))) {
                                    System.out.println("Check 6");
                                    //if a quest in the list of the questerentity is NOT done and NOT in progress
                                    // start adding the button to accept or denie the quest
                                    this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                                    test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
                                    {
                                        //just a button to remove the giant black square
                                    });
                                    acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                                    {
                                        this.questData.addInProgressQuest(quest);
                                        PacketHandler.sendToServer(new CUpdateQuestStatePacket(quest));
                                        PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                                        this.state = 1;
                                        this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                                    }); // -> accepting the quest
                                    declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                                    {
                                        this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                                        this.state = -1;
                                    }); // -> declining the quest
                                    return;
                                }
                            }
                        }
                    }
                    else
                    {
                        this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                        test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
                        {
                            //just a button to remove the giant black square
                        });
                        acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                        {
                            this.questData.addInProgressQuest(quest);
                            PacketHandler.sendToServer(new CUpdateQuestStatePacket(quest));
                            PacketHandler.sendToServer(new CRequestSyncQuestDataPacket());
                            this.state = 1;
                            this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                        }); // -> accepting the quest
                        declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                        {
                            this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                            this.state = -1;
                        }); // -> declining the quest
                        return;
                    }
                }
            }
        }
        */

    }
    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f) {
        this.renderBackground(matrixStack);


        if (!(this.state == 0)) {
            this.buttons.remove(acceptbutton);
            this.buttons.remove(declinebutton);
        }


        int posX = this.width / 2;
        int posY = this.height / 2;
        minecraft.getTextureManager().bind(chatPrompt);
        GuiUtils.drawTexturedModalRect(posX - 128, posY - 125, 0, 0, 256, 256, 0);
        this.message.render(matrixStack, guiLeft - 88, guiTop + 115);
        super.render(matrixStack, x, y, f);
    }
}
