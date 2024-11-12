package com.solegendary.reignofnether.alliance;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.multiplayer.PlayerInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.Collection;

public class AllianceScreen extends Screen {
    AllianceEntryList allianceEntryList;
    public AllianceScreen() {
        super(Component.literal("AllianceScreen"));
    }

    @Override
    protected void init() {
        Collection<PlayerInfo> playerInfoCollection = this.minecraft.getConnection().getOnlinePlayers();
        this.allianceEntryList = new AllianceEntryList(this.minecraft, playerInfoCollection,this.width, this.height, 80, 360, 24 + 8);
        this.addWidget(allianceEntryList);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pPoseStack);
        this.allianceEntryList.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        //test code for rendering player faces
        /*
        PlayerInfo info = this.minecraft.player.connection.getPlayerInfo("D00dlenoodles");
        ResourceLocation skin = info.getSkinLocation();
        RenderSystem.setShaderTexture(0, skin);
        PlayerFaceRenderer.draw(pPoseStack, this.width / 2, this.height / 2, 64);
         */
    }

}
