package com.yuanno.hunterxx.client.gui;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.yuanno.hunterxx.Main;
import com.yuanno.hunterxx.api.Beapi;
import com.yuanno.hunterxx.api.NoTextureButton;
import com.yuanno.hunterxx.data.entity.EntityStatsCapability;
import com.yuanno.hunterxx.data.entity.IEntityStats;
import com.yuanno.hunterxx.init.ModItems;
import com.yuanno.hunterxx.networking.PacketHandler;
import com.yuanno.hunterxx.networking.client.CGiveItemStackPacket;
import com.yuanno.hunterxx.networking.client.CStatsCCPacket;
import com.yuanno.hunterxx.networking.client.CSyncentityStatsPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;

import java.awt.*;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class ComputerScreen extends Screen {
    private PlayerEntity player;
    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/img.png");
    private NoTextureButton buyButtonDiamond;
    private NoTextureButton buyButtonIron;
    private NoTextureButton buyButtonCoal;
    ArrayList<NoTextureButton> buttons = new ArrayList<NoTextureButton>();

    int state = 0;
    public ComputerScreen(PlayerEntity player)
    {
        super(new StringTextComponent(""));
        this.minecraft = Minecraft.getInstance();
        this.player = player;
        assert this.player != null;
        this.entityStats = EntityStatsCapability.get(player);
    }



    public void renderItem(ItemStack stack, int posX, int posY)
    {
        Minecraft.getInstance().getItemRenderer().renderGuiItem(stack, posX, posY);
    }

    @Override
    public void init()
    {
        super.init();
        int posX = this.width / 2;
        int posY = this.height / 2;
        if (player.inventory.hasAnyOf(ImmutableSet.of(ModItems.HUNTER_LICENSE.get())))
            state = 1;
        else
            state = 0;

        buyButtonDiamond = new NoTextureButton(posX - 15, posY - 73, 16, 16, new TranslationTextComponent("Buy"), (btn) ->
        {
            entityStats.alterJenny(-buyButtonDiamond.number);
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(Items.DIAMOND)));
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        });
        buyButtonIron = new NoTextureButton(posX - 15, posY - 55, 16, 16, new TranslationTextComponent("Buy"), (btn) ->
        {
            entityStats.alterJenny(-buyButtonIron.number);
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(Items.IRON_INGOT)));
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        });
        buyButtonCoal = new NoTextureButton(posX - 15, posY - 37, 16, 16, new TranslationTextComponent("Buy"), (btn) ->
        {
            entityStats.alterJenny(-buyButtonCoal.number);
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(Items.COAL)));
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        });
        if (state == 1) {
            this.addButton(buyButtonDiamond);
            this.addButton(buyButtonIron);
            this.addButton(buyButtonCoal);
            buttons.add(buyButtonDiamond);
            buttons.add(buyButtonIron);
            buttons.add(buyButtonCoal);
            buyButtonDiamond.number = 150;
            buyButtonIron.number = 10;
            buyButtonCoal.number = 1;
        }
    }

    @Override
    public void render(MatrixStack matrixStack, int x, int y, float f)
    {
        this.renderBackground(matrixStack);
        int posX = this.width / 2;
        int posY = this.height / 2;
        Minecraft.getInstance().getTextureManager().bind(background);
        GuiUtils.drawTexturedModalRect(posX - 90, posY - 75, 0, 0, 256, 256, 0);

        if (state == 0)
        {
            Beapi.drawStringWithBorder(this.font, matrixStack, TextFormatting.BOLD + "NEED A HUNTER LICENSE TO ACCESS THE BLACK MARKET", posX - 149, posY - 20, -1);
        }
        else
        {

            // TODO add more stuff to get for computer
            this.renderItem(new ItemStack(Items.DIAMOND.asItem()), posX - 90, posY - 75);
            this.renderItem(new ItemStack(Items.IRON_INGOT.asItem()), posX - 90, posY - 57);
            this.renderItem(new ItemStack(Items.COAL.asItem()), posX - 90, posY - 39);
            //Beapi.drawStringWithBorder(this.font, matrixStack, "Price: 150", posX - 70, posY - 70, -1);
            for (int i = 0; i < buttons.size(); i++)
            {
                Beapi.drawStringWithBorder(this.font, matrixStack, "Price: " + buttons.get(i).number, posX - 70, posY - 70 + (i * 18), -1);
                if (buttons.get(i).number >= entityStats.getJenny())
                    buttons.get(i).active = false;
                else
                    buttons.get(i).active = true;
            }
        }
        super.render(matrixStack, x, y, f);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
