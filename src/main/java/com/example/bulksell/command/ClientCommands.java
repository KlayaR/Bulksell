package com.example.bulksell.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import com.example.bulksell.client.SellUIScreen;
import net.minecraft.client.Minecraft;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.GAME)
public class ClientCommands {
    @SubscribeEvent
    public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> disp = event.getDispatcher();
        disp.register(Commands.literal("sellui").executes(ctx -> {
            Minecraft.getInstance().setScreen(new SellUIScreen());
            return 1;
        }));
    }

    public static void register() { /* trigger class load */ }
}
