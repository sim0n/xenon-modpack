package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.ui.tab.item.ExpandingTabItem;
import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class LabeledPropertyTabItem extends ExpandingTabItem {
    private final String label;

    @Override
    public String getText() {
        return label;
    }

    @Override
    public TabItemContainer open() {
        return null;
    }
}
