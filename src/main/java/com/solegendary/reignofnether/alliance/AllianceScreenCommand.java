package com.solegendary.reignofnether.alliance;
import com.mojang.brigadier.CommandDispatcher;
import com.solegendary.reignofnether.alliance.networking.ClientboundOpenAllianceScreenPacket;
import com.solegendary.reignofnether.registrars.PacketHandler;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

@Mod.EventBusSubscriber
public class AllianceScreenCommand {

    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
        dispatcher.register(
                Commands.literal("alliances")
                        .executes(context -> {
                            Supplier<ServerPlayer> player = () -> context.getSource().getPlayer();
                            PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(player), new ClientboundOpenAllianceScreenPacket());
                            return 1;
                        })
        );
    }

}
