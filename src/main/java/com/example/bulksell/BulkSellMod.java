package com.example.bulksell;

import com.example.bulksell.network.NetworkHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(BulkSellMod.MOD_ID)
public class BulkSellMod {
    public static final String MOD_ID = "bulksell";

    // NeoForge injects the mod event bus into this constructor
    public BulkSellMod(IEventBus modEventBus) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::clientSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        NetworkHandler.init();
        // Server/Client commands are registered by their @EventBusSubscriber classes.
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        // Client-only init if you need it later.
    }
}
