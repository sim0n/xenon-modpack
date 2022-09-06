package dev.sim0n.modpack.ui.tab.item.cursor.updater.impl;

import dev.sim0n.modpack.ui.tab.item.cursor.updater.TabCursorUpdater;

/**
 * @author sim0n
 */
public class FrameTimeDeltaTabCursorUpdater implements TabCursorUpdater {
    private long lastFrameTime = System.currentTimeMillis();

    @Override
    public float update() {
        long now = System.currentTimeMillis();

        long timeDelta = now - this.lastFrameTime;

        this.lastFrameTime = now;

        return timeDelta;
    }
}
