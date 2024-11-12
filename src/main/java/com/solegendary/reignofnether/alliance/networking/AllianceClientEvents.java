package com.solegendary.reignofnether.alliance.networking;

import com.solegendary.reignofnether.alliance.AllianceScreen;
import com.solegendary.reignofnether.votesystem.MapSelectionScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AllianceClientEvents {
    private static final Minecraft MC = Minecraft.getInstance();
    public static void loadAllianceScreen(ClientboundOpenAllianceScreenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        MC.setScreen(new AllianceScreen());
    }
}
