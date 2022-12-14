package com.yuanno.hunterxx.client.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.TexturedIconButton;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CAttributePacket;
import com.yuanno.hunterxx.networking.client.CRequestSyncWorldDataPacket;
import com.yuanno.hunterxx.networking.client.CStatsCCPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.util.UUID;

@OnlyIn(Dist.CLIENT)
public class StatsScreen extends Screen {

    protected PlayerEntity player;
    private final ResourceLocation overviewScreen = new ResourceLocation(Main.MODID, "textures/gui/overview.png");
    private final ResourceLocation backButton = new ResourceLocation(Main.MODID, "textures/gui/test.png");
    private final ResourceLocation basicButton = new ResourceLocation(Main.MODID, "textures/gui/basic_button.png");

    private IEntityStats entityStats;
    private final int xSize = 64;
    private final int ySize = 58;
    private int guiLeft;
    private int guiTop;

    public static UUID attack_attributemodifier_uuid = UUID.fromString("aa18be1b-b1d0-4917-a168-42dd240d000d");
    public static UUID defense_attributemodifier_uuid = UUID.fromString("806281d3-bb17-4b3f-8142-f03f077ba2e2");
    public static UUID health_attributemodifier_uuid = UUID.fromString("135d510d-26c6-403e-8615-899862332e86");
    public static UUID speed_attributemodifier_uuid = UUID.fromString("69082b90-7357-407c-9e82-7852b6925932");


    protected StatsScreen(PlayerEntity player) {
        super(new StringTextComponent(""));
        this.player = player;
    }

    @Override
    public void init()
    {
        PacketHandler.sendToServer(new CRequestSyncWorldDataPacket());
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;

        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;
        this.entityStats = EntityStatsCapability.get(player);

        TexturedIconButton testButton = new TexturedIconButton(backButton, posX + 500, posY + 1700, 32, 32, new TranslationTextComponent(""), b ->
        {
            // empty button to not have the ugly big black box
        });
        TexturedIconButton statsButton = new TexturedIconButton(backButton, posX + 50, posY + 170, 32, 32, new TranslationTextComponent(""), b ->
        {
            Minecraft.getInstance().setScreen(new OverViewScreen());
        });
        this.addButton(testButton);
        this.addButton(statsButton);

    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        int posX = (this.width - 256) / 2;
        int posY = (this.height - 256) / 2;

        this.renderBackground(matrixStack);
        overViewRendering(matrixStack);
        super.render(matrixStack, x, y, f);
        this.entityStats = EntityStatsCapability.get(player);
        //TODO get points by doing other stuff too and add extra attributes to the thingies
        Button health = new Button(guiLeft + 67, guiTop - 50, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth() + 1, entityStats.getStrength(), entityStats.getDefense(), entityStats.getSpeed(), entityStats.getMaxAura(), entityStats.getAuraRegeneration()));
                PacketHandler.sendToServer(new CAttributePacket(1)); // health update
            }
        });
        Button strength = new Button(guiLeft + 67, guiTop - 40, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth(), entityStats.getStrength() + 1, entityStats.getDefense(), entityStats.getSpeed(), entityStats.getMaxAura(), entityStats.getAuraRegeneration()));
                PacketHandler.sendToServer(new CAttributePacket(2));
            }
        });
        Button defense = new Button(guiLeft + 67, guiTop - 30, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth(), entityStats.getStrength(), entityStats.getDefense() + 1, entityStats.getSpeed(), entityStats.getMaxAura(), entityStats.getAuraRegeneration()));
                PacketHandler.sendToServer(new CAttributePacket(3));
            }
        });
        Button speed = new Button(guiLeft + 67, guiTop - 20, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth(), entityStats.getStrength(), entityStats.getDefense(), entityStats.getSpeed() + 1, entityStats.getMaxAura(), entityStats.getAuraRegeneration()));
                PacketHandler.sendToServer(new CAttributePacket(4));
            }
        });
        Button maxAura = new Button(guiLeft + 67, guiTop + 10, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth(), entityStats.getStrength(), entityStats.getDefense(), entityStats.getSpeed(), entityStats.getMaxAura() + 50, entityStats.getAuraRegeneration()));
            }
        });
        Button auraRegen = new Button(guiLeft + 67, guiTop + 20, 9, 9, new TranslationTextComponent("+"), b ->
        {
            if (this.entityStats.getLevelPoints() > 0)
            {
                PacketHandler.sendToServer(new CStatsCCPacket(entityStats.getLevelPoints() - 1, entityStats.getHealth(), entityStats.getStrength(), entityStats.getDefense(), entityStats.getSpeed(), entityStats.getMaxAura(), entityStats.getAuraRegeneration() + 1));
            }
        });
        //statsButton = statsButton.setIconInfo(backButton, posX + 50, posY + 170, 1);
        //statsButton = statsButton.setTextInfo(posX + 50, posY + 180, 0.85);
        if (this.entityStats.getLevelPoints() <= 0) {
            health.active = false;
            strength.active = false;
            defense.active = false;
            speed.active = false;
            maxAura.active = false;
            auraRegen.active = false;
        }
        else
        {
            this.addButton(health);
            this.addButton(defense);
            this.addButton(strength);
            this.addButton(speed);
            if (entityStats.getHasNen())
            {
                this.addButton(maxAura);
                this.addButton(auraRegen);
            }
        }


    }

    public void overViewRendering(MatrixStack matrixStack) {
        int scaledWidth = this.getMinecraft().getWindow().getGuiScaledWidth();

        IEntityStats stats = EntityStatsCapability.get(player);
        int health = stats.getHealth();
        int strength = stats.getStrength();
        int defense = stats.getDefense();
        int speed = stats.getSpeed();
        int nen = stats.getNen();
        int auraMax = stats.getMaxAura();
        int auraRegeneration = stats.getAuraRegeneration();
        //int points = entityStats.getLevelPoints();

        minecraft.getTextureManager().bind(overviewScreen);
        GuiUtils.drawTexturedModalRect((int) ((scaledWidth / 2) - 128), 0, 0, 0, 256, 256, 0);
        drawString(matrixStack, font, TextFormatting.BOLD  + "INFO CARD" + TextFormatting.RESET, guiLeft + 12, guiTop - 70, 16777215);
        drawString(matrixStack, font, TextFormatting.BOLD + "POINTS: " + TextFormatting.RESET + stats.getLevelPoints(), guiLeft + 12, guiTop - 60, 16777215);
        drawString(matrixStack, font, TextFormatting.RED  + "Health:" , guiLeft - 40, guiTop - 50, 16777215);
        drawString(matrixStack, font, TextFormatting.LIGHT_PURPLE + "Strength:" , guiLeft - 40, guiTop - 40, 16777215);
        drawString(matrixStack, font, TextFormatting.BLUE + "Defense:" , guiLeft - 40, guiTop - 30, 16777215);
        drawString(matrixStack, font, TextFormatting.YELLOW + "Speed:" , guiLeft - 40, guiTop - 20, 16777215);
        drawString(matrixStack, font, ""  + health, guiLeft + 30, guiTop - 50, 16777215);
        drawString(matrixStack, font, "" + strength, guiLeft + 30, guiTop - 40, 16777215);
        drawString(matrixStack, font, "" + defense, guiLeft + 30, guiTop - 30, 16777215);
        drawString(matrixStack, font, "" + speed, guiLeft + 30, guiTop - 20, 16777215);

        if (stats.getHasNen()) // -> Hasn't unlocked Nen yet
        {
            drawString(matrixStack, font, TextFormatting.WHITE + "Max aura: " + TextFormatting.RESET + auraMax, guiLeft - 40, guiTop + 10, 16777215);
            drawString(matrixStack, font, TextFormatting.WHITE + "Aura regeneration: " + TextFormatting.RESET + auraRegeneration, guiLeft - 40, guiTop + 20, 16777215);
        }
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }


}
