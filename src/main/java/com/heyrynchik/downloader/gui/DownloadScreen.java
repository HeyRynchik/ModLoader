package com.heyrynchik.downloader.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;


public class DownloadScreen extends Screen {

    private EditBox editbox;
    public DownloadScreen(Component title) {
        super(title);
        init();
    }
    @Override
    protected void init() {
        super.init();

        editbox = this.addRenderableWidget(new EditBox(Minecraft.getInstance().font, 50, 50, 50, 50, Component.literal("HI")));
    }
    @Override
    public void tick() {
        super.tick();
        editbox.tick();
    }
    @Override
    public void render(@NotNull PoseStack pose, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(pose);
        editbox.render(pose, mouseX, mouseY, partialTicks);
        super.render(pose, mouseX, mouseY, partialTicks);
    }

}
