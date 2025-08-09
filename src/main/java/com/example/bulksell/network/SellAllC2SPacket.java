package com.example.bulksell.network;

import com.example.bulksell.PriceTable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record SellAllC2SPacket() implements CustomPacketPayload {
    public static final Type<SellAllC2SPacket> TYPE = new Type<>(ResourceLocation.parse("bulksell:sell_all"));

    public static final StreamCodec<FriendlyByteBuf, SellAllC2SPacket> STREAM_CODEC = StreamCodec.unit(new SellAllC2SPacket());

    @Override
    public Type<? extends CustomPacketPayload> type() { return TYPE; }

    public static void handle(final SellAllC2SPacket msg, final IPayloadContext ctx) {
        ctx.enqueueWork(() -> {
            Player player = ctx.player();
            if (!(player instanceof ServerPlayer sp)) return;

            Inventory inv = sp.getInventory();
            int emeraldShards = 0;

            for (int i = 0; i < inv.getContainerSize(); i++) {
                ItemStack stack = inv.getItem(i);
                if (stack.isEmpty()) continue;
                if (!PriceTable.isSupported(stack.getItem())) continue;

                int count = stack.getCount();
                emeraldShards += PriceTable.shardsFor(stack.getItem(), count);
                inv.setItem(i, ItemStack.EMPTY);
            }

            int emeralds = emeraldShards / PriceTable.EMERALD_SHARDS_PER_EMERALD;
            // remainder is dropped (could be stored for later payout)

            if (emeralds > 0) {
                sp.getInventory().placeItemBackInInventory(new ItemStack(Items.EMERALD, emeralds));
            }

            sp.displayClientMessage(net.minecraft.network.chat.Component.translatable(
                    "message.bulksell.sold", emeralds), true);
        });
    }
}
