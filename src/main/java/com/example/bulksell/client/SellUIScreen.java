package com.example.bulksell.client;

import com.example.bulksell.PriceTable;
import com.example.bulksell.network.SellAllC2SPacket;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.PacketDistributor;

public class SellUIScreen extends Screen {
    private Button sellAllButton;

    public SellUIScreen() {
        super(Component.translatable("screen.bulksell.title"));
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int centerY = this.height / 2;

        sellAllButton = Button.builder(Component.translatable("screen.bulksell.sell_all"), btn -> {
            PacketDistributor.sendToServer(new SellAllC2SPacket());
            this.onClose();
        }).bounds(centerX - 60, centerY + 20, 120, 20).build();

        addRenderableWidget(sellAllButton);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        this.renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);

        int x = this.width / 2;
        int y = this.height / 2 - 40;
        graphics.drawCenteredString(this.font, this.title, x, y, 0xFFFFFF);
        graphics.drawCenteredString(this.font, Component.translatable("screen.bulksell.instructions").getString(), x, y + 14, 0xA0A0A0);
    }
}
