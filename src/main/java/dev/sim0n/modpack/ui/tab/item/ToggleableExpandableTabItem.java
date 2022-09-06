package dev.sim0n.modpack.ui.tab.item;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
public abstract class ToggleableExpandableTabItem extends ExpandingTabItem  {
    private boolean state;

    public void toggle() {
        this.state = !this.state;
    }
}
