package dev.sim0n.modpack.ui.tab.item.cursor;

import dev.sim0n.modpack.ui.tab.item.AbstractTabItem;

public interface SelectableTabCursor {
    AbstractTabItem getSelectedItem();

    void setSelectedItem(AbstractTabItem item);
}
