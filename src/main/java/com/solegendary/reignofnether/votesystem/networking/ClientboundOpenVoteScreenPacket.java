package com.solegendary.reignofnether.votesystem.networking;

import com.google.common.collect.Lists;
import com.solegendary.reignofnether.votesystem.MapData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;


public class ClientboundOpenVoteScreenPacket {


    private final List<MapData> maps;

    public ClientboundOpenVoteScreenPacket(List<MapData> maps) {
        this.maps = Lists.newArrayList(maps);
    }

    public ClientboundOpenVoteScreenPacket(FriendlyByteBuf buf) {
        this.maps = buf.readList(mapBuffer -> {
            String name = mapBuffer.readUtf();
            String description = mapBuffer.readUtf();
            int players = mapBuffer.readInt();
            String image = mapBuffer.readUtf();

            return new MapData(name, players, description, null, image);
        });
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeCollection(this.maps, (mapBuffer, collection) -> {
            String name = collection.getName();
            String description = collection.getDescription();
            int players = collection.getPlayers();
            String image = collection.getImage();
            mapBuffer.writeUtf(name);
            mapBuffer.writeUtf(description);
            mapBuffer.writeInt(players);
            mapBuffer.writeUtf(image);
        });
    }

    public static boolean handle(ClientboundOpenVoteScreenPacket msg, Supplier<NetworkEvent.Context> ctx) {
        final var success = new AtomicBoolean(false);
        NetworkEvent.Context context = ctx.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> VoteClientEvents.loadVoteScreen(msg, ctx));
            success.set(true);
        });
        context.setPacketHandled(true);
        return success.get();
    }

    public static ClientboundOpenVoteScreenPacket decode(FriendlyByteBuf buf) {
        return new ClientboundOpenVoteScreenPacket(buf);
    }

    public List<MapData> getMaps() {return this.maps;}

}
