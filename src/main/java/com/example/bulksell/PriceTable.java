package com.example.bulksell;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import java.util.Map;
import java.util.Set;

public class PriceTable {
    // Price per item in "emerald shards" where 64 shards = 1 emerald
    public static final int EMERALD_SHARDS_PER_EMERALD = 64;

    public static final Map<Item, Integer> PRICE_PER_ITEM_SHARDS = Map.of(
            Items.COBBLESTONE, 1,
            Items.DEEPSLATE, 1,
            Items.DIRT, 1,
            Items.NETHERRACK, 1,
            Items.SAND, 1
    );

    public static boolean isSupported(Item item) {
        return PRICE_PER_ITEM_SHARDS.containsKey(item);
    }

    public static int shardsFor(Item item, int count) {
        return PRICE_PER_ITEM_SHARDS.getOrDefault(item, 0) * count;
    }

    public static Set<Item> supportedItems() {
        return PRICE_PER_ITEM_SHARDS.keySet();
    }
}
