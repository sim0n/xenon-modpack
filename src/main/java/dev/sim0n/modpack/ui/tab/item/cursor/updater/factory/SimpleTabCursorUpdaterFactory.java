package dev.sim0n.modpack.ui.tab.item.cursor.updater.factory;

import dev.sim0n.modpack.ui.tab.item.cursor.updater.TabCursorUpdater;
import dev.sim0n.modpack.ui.tab.item.cursor.updater.impl.FrameTimeDeltaTabCursorUpdater;

public enum SimpleTabCursorUpdaterFactory implements TabCursorUpdaterFactory {
    INSTANCE;

    @Override
    public TabCursorUpdater makeTabCursorUpdater() {
        return new FrameTimeDeltaTabCursorUpdater();
    }
}
