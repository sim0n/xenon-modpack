package dev.sim0n.modpack.util.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.sim0n.modpack.util.helper.MCHelper;
import dev.sim0n.modpack.util.math.type.DVec2;
import dev.sim0n.modpack.util.math.type.FVec2;
import lombok.experimental.UtilityClass;
import net.minecraft.client.render.*;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * @author sim0n
 */
@UtilityClass
public class OGLRenderer implements MCHelper {
    private final int CIRCLE_POINTS = 64;
    private final DVec2[] RADIUS_LOOKUP_TABLE = new DVec2[CIRCLE_POINTS + 1];

    static {
        for (int i = 0; i <= CIRCLE_POINTS; i++) {
            RADIUS_LOOKUP_TABLE[i] = new DVec2(
                    Math.cos(2D * Math.PI * (i / (float) CIRCLE_POINTS)),
                    Math.sin(2D * Math.PI * (i / (float) CIRCLE_POINTS))
            );
        }
    }

    /**
     * Draws a filled rectangle with the given color.
     * @param pos The position of the rectangle.
     * @param size The size of the rectangle.
     * @param color The color of the rectangle.
     */
    public void filledRect(FVec2 pos, FVec2 size, Color color) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();

        var red = color.getRed();
        var green = color.getGreen();
        var blue = color.getBlue();
        var alpha = color.getAlpha();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        builder.vertex(pos.getX() + size.getX(), pos.getY(), 0)
                .color(red, green, blue, alpha)
                .next();

        builder.vertex(pos.getX(), pos.getY(), 0)
                .color(red, green, blue, alpha)
                .next();

        builder.vertex(pos.getX(), pos.getY() + size.getY(), 0)
                .color(red, green, blue, alpha)
                .next();

        builder.vertex(pos.getX() + size.getX(), pos.getY() + size.getY(), 0)
                .color(red, green, blue, alpha)
                .next();

        builder.clear();
        BufferRenderer.drawWithShader(builder.end());
        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
    }

    /**
     * Draws a filled gradient rectangle with the given color.
     * @param pos The position of the rectangle.
     * @param size The size of the rectangle.
     * @param col1 The first color of the gradient.
     * @param col2 The second color of the gradient.
     * @param vertical Whether the gradient is vertical or horizontal.
     */
    public void filledRectGradient(FVec2 pos, FVec2 size, Color col1, Color col2, boolean vertical) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.blendFuncSeparate(
                GlStateManager.SrcFactor.SRC_ALPHA,
                GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SrcFactor.ONE,
                GlStateManager.DstFactor.ZERO
        );

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder builder = tessellator.getBuffer();

        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        builder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);

        var col1Direct = col1.getRGB();
        var col2Direct = col2.getRGB();

        builder.vertex(pos.getX() + size.getX(), pos.getY(), 0)
                .color(vertical ? col1Direct : col2Direct)
                .next();

        builder.vertex(pos.getX(), pos.getY(), 0)
                .color(col1Direct)
                .next();

        builder.vertex(pos.getX(), pos.getY() + size.getY(), 0)
                .color(vertical ? col2Direct : col1Direct)
                .next();

        builder.vertex(pos.getX() + size.getX(), pos.getY() + size.getY(), 0)
                .color(col2Direct)
                .next();

        builder.clear();
        BufferRenderer.drawWithShader(builder.end());
        RenderSystem.disableBlend();
        RenderSystem.enableTexture();
    }

    /**
     * Draws a string with a shadow.
     * @param matrices The matrix stack.
     * @param text The text to draw.
     * @param x The x position.
     * @param y The y position.
     * @param color The color of the text.
     * @return The width of the text.
     */
    public int drawStringWithShadow(MatrixStack matrices, String text, float x, float y, int color) {
        mc.textRenderer.draw(matrices, text, x + 0.5F, y + 0.5F, 0xBB000000);

        return mc.textRenderer.draw(matrices, text, x, y, color);
    }

    public void prepareScissorBox(float x, float y, float width, float height) {
        Window window = mc.getWindow();

        int factor = (int) window.getScaleFactor();

        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glScissor((int) (x * factor),
                (int) ((window.getScaledHeight() - (y + height)) * factor),
                (int) (width * factor), (int) (height * factor));
    }

    public void endScissor() {
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
    }
}
