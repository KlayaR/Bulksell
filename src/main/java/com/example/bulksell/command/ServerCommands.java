package com.example.bulksell.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import com.example.bulksell.network.SellAllC2SPacket;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber
public class ServerCommands {
    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> disp = event.getDispatcher();
        disp.register(Commands.literal("sellbulk").requires(src -> src.hasPermission(0))
            .executes(ctx -> {
                if (ctx.getSource().getEntity() instanceof ServerPlayer sp) {
                    PacketDistributor.sendToServer(new SellAllC2SPacket());
                    return 1;
                }
                return 0;
            }));
    }

    public static void register() { /* trigger class load */ }
}
