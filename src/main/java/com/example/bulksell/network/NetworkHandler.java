package com.example.bulksell.network;

import com.example.bulksell.BulkSellMod;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = BulkSellMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NetworkHandler {
    public static final ResourceLocation SELL_ALL_ID = ResourceLocation.fromNamespaceAndPath(BulkSellMod.MOD_ID, "sell_all");

    @SubscribeEvent
    public static void onRegisterPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(BulkSellMod.MOD_ID).versioned("1");
        registrar.playToServer(SellAllC2SPacket.TYPE, SellAllC2SPacket.STREAM_CODEC, SellAllC2SPacket::handle);
    }

    public static void init() { /* trigger class load */ }
}
