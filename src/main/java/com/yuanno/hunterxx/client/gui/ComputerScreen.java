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

@OnlyIn(Dist.CLIENT)
public class ComputerScreen extends Screen {
    private PlayerEntity player;
    private IEntityStats entityStats;
    private final ResourceLocation background = new ResourceLocation(Main.MODID, "textures/gui/img.png");
    private NoTextureButton buyButton;
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
        buyButton = new NoTextureButton(posX - 15, posY - 73, 16, 16, new TranslationTextComponent("Buy"), (btn) ->
        {
            System.out.println("You bought a diamond");
            entityStats.alterJenny(-150);
            PacketHandler.sendToServer(new CGiveItemStackPacket(new ItemStack(Items.DIAMOND)));
            PacketHandler.sendToServer(new CSyncentityStatsPacket(entityStats));
        });
        if (state == 1)
            this.addButton(buyButton);
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
            this.renderItem(new ItemStack(Items.DIAMOND.asItem()), posX - 90, posY - 75);
            Beapi.drawStringWithBorder(this.font, matrixStack, "Price: 150", posX - 70, posY - 70, -1);
            if (entityStats.getJenny() >= 100)
                buyButton.active = true;
            else
                buyButton.active = false;

        }
        super.render(matrixStack, x, y, f);
    }

    @Override
    public boolean isPauseScreen()
    {
        return false;
    }
}
