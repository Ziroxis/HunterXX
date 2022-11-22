package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Quest.Quest;
import com.yuanno.hunterxx.api.SequencedString;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.quest.IQuestData;
import com.yuanno.hunterxx.data.quest.QuestDataCapability;
import com.yuanno.hunterxx.entity.questers.QuesterEntity;
import com.yuanno.hunterxx.networking.PacketHandler;
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

import java.util.ArrayList;
import java.util.Arrays;

@OnlyIn(Dist.CLIENT)
public class ChatPromptScreen extends Screen {

    private final ResourceLocation chatPrompt = new ResourceLocation(Main.MODID, "textures/gui/chatprompt.png");
    private final ResourceLocation declineButton = new ResourceLocation(Main.MODID, "textures/gui/questdeclinebutton.png");
    private final ResourceLocation acceptButton = new ResourceLocation(Main.MODID, "textures/gui/questacceptbutton.png");

    private int state = 0;
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
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.loop(posX, posY);
        if (this.test != null && this.acceptButton != null && this.declineButton != null)
        {
            this.addButton(test);
            this.addButton(acceptbutton);
            this.addButton(declinebutton);
        }

    }
    public void loop(int posX, int posY)
    {
        ArrayList<String> alreadyDoneQuestID = new ArrayList<String>();
        System.out.println(questData.getFinishedQuests());
        System.out.println(Arrays.toString(questData.getInProgressQuests()));
        int amount = 0;
        for (int i = 0; i < questData.getFinishedQuests().size(); i++)
        {
            for (int ia = 0; ia < questerEntity.questList.size(); ia++)
            {
                if (questData.getFinishedQuests().get(i).getId().equals(questerEntity.questList.get(ia).getId()) && !alreadyDoneQuestID.contains(questerEntity.questList.get(ia).getId()))
                {
                    amount++;
                    alreadyDoneQuestID.add(questerEntity.questList.get(ia).getId());
                }
            }
        }
        if (questerEntity.prerequisite)
        {
            ArrayList<String> alreadyDoneQuestIDPrerequisite = new ArrayList<String>();
            System.out.println(alreadyDoneQuestID);
            System.out.println(amount);
            for (int i = 0; i < questData.getFinishedQuests().size(); i++)
            {
                if (!alreadyDoneQuestIDPrerequisite.contains(questData.getFinishedQuests().get(i).getId()))
                {
                    alreadyDoneQuestIDPrerequisite.add(questData.getFinishedQuests().get(i).getId());
                }
            }
            if (!alreadyDoneQuestIDPrerequisite.contains(questerEntity.questPrerequisite.getId()))
            {
                this.message = new SequencedString(questerEntity.denialSpeechPreviousQuest + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000);
                return;

            }
        }
        for (int i = 0; i < questData.getInProgressQuests().length; i++) {
            if (questData.getInProgressQuest(i) != null)
            {
                for (int ia = 0; ia < questerEntity.questList.size(); ia++)
                {
                    if (questerEntity.questList.get(ia) != null && questData.getInProgressQuest(i).getId().equals(questerEntity.questList.get(ia).getId()))
                    {
                        if (questerEntity.questList.get(ia) != null && !questData.hasFinishedQuest(questerEntity.questList.get(ia)) && questerEntity.questList.get(ia).isComplete())
                        {
                            questData.addFinishedQuest(questerEntity.questList.get(ia));
                            questData.removeFinishedQuest(questerEntity.questList.get(ia));
                            questData.removeInProgressQuest(questerEntity.questList.get(ia));
                            PacketHandler.sendToServer(new CUpdateQuestStatePacket(questerEntity.questList.get(ia)));
                            this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                            return;
                        }
                        else {
                            this.message = new SequencedString(questerEntity.ongoingSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                            return;
                        }
                    }
                }
            }
            else break;
        }

        int amountPermanent = amount;
        System.out.println(amountPermanent);
        if (amountPermanent >= questerEntity.questList.size()) // TODO check if you got the id of the latest quest
        {
            this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
            return;
        }
        Quest firstQuest = questerEntity.questList.iterator().next();
        if (questData.getFinishedQuests().size() == 0)
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
                this.state = 1;
                this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
            }); // -> accepting the quest
            declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
            {
                this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                this.state = -1;
            }); // -> declining the quest

        }
        for (int i = 0; i< questData.getFinishedQuests().size(); i++)
        {
            if (questData.getFinishedQuests().get(i) != null && questData.getFinishedQuests().get(i).getId().equals(firstQuest.getId()))
            {
                if (questerEntity.questList.size() > amount)
                {
                    this.message = new SequencedString(questerEntity.questSpeech + "", 245, this.font.width(questerEntity.questSpeech) / 2, 2000); // -> first time talking to the npc
                    test = new TexturedIconButton(acceptButton, posX + 800, posY + 800, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        //just a button to remove the giant black square
                    });
                    acceptbutton = new TexturedIconButton(acceptButton, posX + 10, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        this.questData.addInProgressQuest(questerEntity.questList.get((int) amountPermanent));
                        PacketHandler.sendToServer(new CUpdateQuestStatePacket(questerEntity.questList.get((int) amountPermanent)));
                        this.state = 1;
                        this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                    }); // -> accepting the quest
                    declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                    {
                        this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                        this.state = -1;
                    }); // -> declining the quest
                }
                else
                {
                    this.message = new SequencedString(questerEntity.doneSpeech + "", 245, this.font.width(questerEntity.doneSpeech) / 2, 2000);
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
                    this.questData.addInProgressQuest(firstQuest);
                    PacketHandler.sendToServer(new CUpdateQuestStatePacket(firstQuest));
                    this.state = 1;
                    this.message = new SequencedString(questerEntity.acceptanceSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                }); // -> accepting the quest
                declinebutton = new TexturedIconButton(declineButton, posX + 180, posY + 230, 32, 32, new TranslationTextComponent(""), b ->
                {
                    this.message = new SequencedString(questerEntity.decliningSpeech + "", 245, this.font.width(questerEntity.decliningSpeech) / 2, 2000);
                    this.state = -1;
                }); // -> declining the quest
            }
        }
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
