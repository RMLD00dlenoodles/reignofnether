package com.solegendary.reignofnether.alliance;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.PlayerFaceRenderer;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class AllianceEntryList extends ContainerObjectSelectionList<AllianceEntryList.AllianceEntry> {

    private Collection<PlayerInfo> playerInfoCollection;
    public AllianceEntryList(Minecraft pMinecraft, Collection<PlayerInfo> pPlayerInfoCollection, int pWidth, int pHeight, int pY0, int pY1, int pItemHeight) {
        super(pMinecraft, pWidth, pHeight, pY0, pY1, pItemHeight);
        Iterator<PlayerInfo> i = pPlayerInfoCollection.iterator();
        while(i.hasNext()) {
            PlayerInfo playerInfo = i.next();
            this.addEntry(new AllianceEntry(playerInfo));
        }
        this.setRenderBackground(false);
        this.setRenderTopAndBottom(false);
    }

    protected int getScrollbarPosition() {
        return super.getScrollbarPosition() + 20;
    }

    public class AllianceEntry extends ContainerObjectSelectionList.Entry<AllianceEntry>{
        private static final int BUTTON_WIDTH = 48;
        private static final int PROFILE_SIZE = 24;
        private final List<AbstractWidget> children;

        @Nullable
        private Button testButton;

        private PlayerInfo playerInfo;
        public AllianceEntry(PlayerInfo pPlayerInfo) {
            this.playerInfo = pPlayerInfo;
            this.testButton = new Button(
                    0,
                    0,
                    BUTTON_WIDTH,
                    20,
                    Component.literal("Ally"),
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
            int topOffset = pTop + (pHeight - 24) / 2;

            ResourceLocation skin = playerInfo.getSkinLocation();
            RenderSystem.setShaderTexture(0, skin);
            PlayerFaceRenderer.draw(pPoseStack, pLeft + 24, topOffset, PROFILE_SIZE);
            drawCenteredString(pPoseStack, minecraft.font, playerInfo.getProfile().getName(), pLeft + pWidth / 2, topOffset + 8,0xFFFFFF);
            this.testButton.x = pLeft + pWidth - (this.testButton.getWidth()) - 12;
            this.testButton.y = pTop + (pHeight - this.testButton.getHeight()) / 2;
            this.testButton.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.children;
        }
    }

}
