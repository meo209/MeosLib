package io.github.meo209.meoslib.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.meo209.meoslib.MeosLib;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.apache.commons.compress.compressors.pack200.Pack200Utils;

import javax.swing.plaf.PanelUI;

public class AdvancedHandledScreen<T extends ScreenHandler> extends HandledScreen<T> {

    public AdvancedHandledScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    private static final Identifier CONTAINER_TEXTURE = new Identifier(MeosLib.MOD_ID, "textures/gui/slot_container.png");
    private static final Identifier SLOT_TEXTURE = new Identifier(MeosLib.MOD_ID, "textures/gui/slot.png");

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.setShaderTexture(0, CONTAINER_TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;

        context.drawTexture(CONTAINER_TEXTURE, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void renderSlots(DrawContext context) {
        handler.slots.forEach(slot -> {
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
            RenderSystem.setShaderTexture(0, SLOT_TEXTURE);

            context.drawTexture(SLOT_TEXTURE, slot.x, slot.y, 0, 0, 18, 18);
        });
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawBackground(context, delta, mouseX, mouseY);
        renderSlots(context);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
