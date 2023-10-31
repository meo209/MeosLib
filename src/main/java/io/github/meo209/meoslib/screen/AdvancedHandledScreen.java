package io.github.meo209.meoslib.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.meo209.meoslib.MeosLib;
import io.github.meo209.meoslib.utils.TextureRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public abstract class AdvancedHandledScreen<T extends ScreenHandler> extends HandledScreen<T> implements ScreenHandlerProvider<T> {

    public final AdvancedScreenHandler screenHandler;

    public AdvancedHandledScreen(T handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.screenHandler = (AdvancedScreenHandler) handler;
    }

    @Override
    public T getScreenHandler() {
        return this.handler;
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

        renderSlots(context);
    }

    public void renderSlots(DrawContext context) {
        screenHandler.blockSlots.forEach(slot -> {
            int x = (width - backgroundWidth) / 2 + slot.x;
            int y = (height - backgroundHeight) / 2 + slot.y;

            RenderSystem.setShaderTexture(0, SLOT_TEXTURE);
            TextureRenderer.renderTexture(context.getMatrices(), x - 1, y - 1, 18, 18);
        });
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        drawBackground(context, delta, mouseX, mouseY);
        super.render(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context, mouseX, mouseY);
    }
}
