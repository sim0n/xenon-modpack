package dev.sim0n.modpack.ui.tab.item.cursor;

import java.util.List;

import dev.sim0n.modpack.ui.tab.item.AbstractTabItem;

public interface TabCursor extends SelectableTabCursor {
    void updateCursorPosition(int destination);

    void onKeyPress(int key, List<AbstractTabItem> items);
    
    void clampCursorIndex(int size);

    /**
     * 
     * @return The cursor position.
     */
    float getCursorPosition();

    
    int getCursorIndex();
}
