package dev.sim0n.modpack.ui.tab.item.cursor.impl;

import dev.sim0n.modpack.ui.tab.item.AbstractTabItem;
import dev.sim0n.modpack.ui.tab.item.cursor.TabCursor;
import dev.sim0n.modpack.ui.tab.item.cursor.updater.factory.SimpleTabCursorUpdaterFactory;
import dev.sim0n.modpack.ui.tab.item.cursor.updater.TabCursorUpdater;
import dev.sim0n.modpack.util.helper.MathHelper;
import lombok.Getter;
import lombok.Setter;
import org.lwjgl.glfw.GLFW;

import java.util.List;

/**
 * @author sim0n
 */
@Getter @Setter
public class AnimatedTabCursor implements TabCursor {
    private static final float CURSOR_SPEED = 50F;
    
    private final TabCursorUpdater updater = SimpleTabCursorUpdaterFactory.INSTANCE.makeTabCursorUpdater();

    private AbstractTabItem selectedItem;
    private float cursorPosition;
    private int cursorIndex;

    @Override
    public void updateCursorPosition(int destination) {
        this.cursorPosition = MathHelper.interpolate(this.cursorPosition, destination, this.updater.update() / CURSOR_SPEED);
    }

    @Override
    public void onKeyPress(int key, List<AbstractTabItem> items) {
        if ((key == GLFW.GLFW_KEY_UP || key == GLFW.GLFW_KEY_DOWN) && !this.selectedItem.isFocused()) {
            switch (key) {
                case GLFW.GLFW_KEY_UP -> --this.cursorIndex;
                case GLFW.GLFW_KEY_DOWN -> ++this.cursorIndex;
            }
        } else {
            this.selectedItem.onKeyPress(key);
        }

        int size = items.size();

        this.clampCursorIndex(size);

        this.selectedItem = items.get(this.cursorIndex);
    }

    @Override
    public void clampCursorIndex(int size) {
        this.cursorIndex %= size;

        if (this.cursorIndex < 0)
            this.cursorIndex = size - 1;
    }
}
