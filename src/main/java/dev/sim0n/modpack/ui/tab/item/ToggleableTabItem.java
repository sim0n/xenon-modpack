package dev.sim0n.modpack.ui.tab.item;

import dev.sim0n.modpack.trait.Toggleable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
public abstract class ToggleableTabItem extends AbstractTabItem implements Toggleable {
    private boolean state;

    @Override
    public void toggle() {
        this.state = !this.state;
    }
}
