package dev.sim0n.modpack.ui.tab.item;

import dev.sim0n.modpack.binding.listener.KeyPressListener;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.helper.MCHelper;
import dev.sim0n.modpack.util.math.type.FVec2;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.math.MatrixStack;

/**
 * @author sim0n
 */
public interface TabItem extends KeyPressListener, MCHelper {

    /**
     * Draws the tab contents.
     * @param matrices The matrix stack.
     * @param x The x position.
     * @param y The y position.
     */
    void draw(MatrixStack matrices, float x, float y);

    
    default void think(int tick) {}

    /**
     * Draws the selection tab
     * @param pos The position of the tab.
     * @param size The size of the tab.
     */
    default void drawTab(FVec2 pos, FVec2 size) {
        OGLRenderer.filledRect(pos, size, Colors.ACCENT);
    }

    default boolean isVisible() {
        return true;
    }
}
