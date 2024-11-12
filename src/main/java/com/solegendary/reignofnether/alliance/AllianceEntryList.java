package com.solegendary.reignofnether.alliance;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;

import java.util.Collection;
import java.util.Iterator;

public class AllianceEntryList extends ContainerObjectSelectionList<AllianceEntry> {

    private Collection<PlayerInfo> playerInfoCollection;
    public AllianceEntryList(Minecraft pMinecraft, Collection<PlayerInfo> pPlayerInfoCollection, int pWidth, int pHeight, int pY0, int pY1, int pItemHeight) {
        super(pMinecraft, pWidth, pHeight, pY0, pY1, pItemHeight);
        Iterator<PlayerInfo> i = pPlayerInfoCollection.iterator();
        while(i.hasNext()) {
            PlayerInfo playerInfo = i.next();
            this.addEntry(new AllianceEntry(playerInfo));
        }
    }
}
