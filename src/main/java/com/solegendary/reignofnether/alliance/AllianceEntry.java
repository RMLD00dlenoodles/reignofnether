package com.solegendary.reignofnether.alliance;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.multiplayer.PlayerInfo;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;
import java.util.UUID;

public class AllianceEntry extends ContainerObjectSelectionList.Entry<AllianceEntry>{
    private final List<AbstractWidget> children;

    @Nullable
    private Button testButton;

    private PlayerInfo playerInfo;
    public AllianceEntry(PlayerInfo pPlayerInfo) {
        this.playerInfo = pPlayerInfo;
        this.testButton = new Button(
                0,
                0,
                16,
                16,
                Component.literal("Test Button"),
                onPress -> {

                }
        );
        this.children = ImmutableList.of(this.testButton);
    }

    public List<? extends NarratableEntry> narratables() {
        return this.children;
    }

    @Override
    public void render(PoseStack pPoseStack, int pIndex, int pTop, int pLeft, int pWidth, int pHeight, int pMouseX, int pMouseY, boolean pIsMouseOver, float pPartialTick) {
        int leftOffset = pLeft + 4;
        int topOffset = pTop + (pHeight - 24) / 2;

        ResourceLocation skin = playerInfo.getSkinLocation();
        RenderSystem.setShaderTexture(0, skin);
        PlayerFaceRenderer.draw(pPoseStack, leftOffset, topOffset, 64);
        this.testButton.x = pLeft + (pWidth - this.testButton.getWidth() - 4) - 20 - 4;
        this.testButton.y = pTop + (pHeight - this.testButton.getHeight()) / 2;
        this.testButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public List<? extends GuiEventListener> children() {
        return this.children;
    }
}
