package com.solegendary.reignofnether.alliance.networking;

import com.solegendary.reignofnether.votesystem.networking.VoteClientEvents;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ClientboundOpenAllianceScreenPacket {
    public ClientboundOpenAllianceScreenPacket() {
    }
    public ClientboundOpenAllianceScreenPacket(FriendlyByteBuf buf) {
    }
    public void encode(FriendlyByteBuf buf) {
    }

    public ClientboundOpenAllianceScreenPacket decode(FriendlyByteBuf buf) {
        return new ClientboundOpenAllianceScreenPacket(buf);
    }

    public static boolean handle(ClientboundOpenAllianceScreenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> AllianceClientEvents.loadAllianceScreen(msg, ctx));
            success.set(true);
        });
        context.setPacketHandled(true);
        return success.get();
    }
}
