package dev.sim0n.modpack.ui.tab.item.cursor.factory;

import dev.sim0n.modpack.ui.tab.item.cursor.impl.AnimatedTabCursor;
import dev.sim0n.modpack.ui.tab.item.cursor.TabCursor;

public enum SimpleTabCursorFactory implements TabCursorFactory {
    INSTANCE;

    SimpleTabCursorFactory() {
        
    }
    
    @Override
    public TabCursor makeTabCursor() {
        return new AnimatedTabCursor();
    }
}

