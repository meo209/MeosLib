package com.github.meo209.meoslib.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

//This is a code snippet from the Renderer Library by 0x3C50
//Modrinth: https://modrinth.com/mod/renderer
public class TextureRenderer {

    public static void renderTexture(MatrixStack matrices, double x0, double y0, double width, double height, float u, float v, double regionWidth, double regionHeight, double textureWidth,
                                     double textureHeight) {
        double x1 = x0 + width;
        double y1 = y0 + height;
        double z = 0;
        renderTexturedQuad(
                matrices.peek().getPositionMatrix(),
                x0,
                x1,
                y0,
                y1,
                z,
                (u + 0.0F) / (float) textureWidth,
                (u + (float) regionWidth) / (float) textureWidth,
                (v + 0.0F) / (float) textureHeight,
                (v + (float) regionHeight) / (float) textureHeight
        );
    }

    private static void renderTexturedQuad(Matrix4f matrix, double x0, double x1, double y0, double y1, double z, float u0, float u1, float v0, float v1) {
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
        buffer.vertex(matrix, (float) x0, (float) y1, (float) z).texture(u0, v1).next();
        buffer.vertex(matrix, (float) x1, (float) y1, (float) z).texture(u1, v1).next();
        buffer.vertex(matrix, (float) x1, (float) y0, (float) z).texture(u1, v0).next();
        buffer.vertex(matrix, (float) x0, (float) y0, (float) z).texture(u0, v0).next();

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        BufferRenderer.drawWithGlobalProgram(buffer.end());
    }

    public static void renderTexture(MatrixStack matrices, double x, double y, double width, double height) {
        renderTexture(matrices, x, y, width, height, 0, 0, width, height, width, height);
    }

}
