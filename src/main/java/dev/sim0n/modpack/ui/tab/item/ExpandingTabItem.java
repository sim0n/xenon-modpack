package dev.sim0n.modpack.ui.tab.item;

import dev.sim0n.modpack.ui.tab.TabUI;
import org.lwjgl.glfw.GLFW;

/**
 * @author sim0n
 */
public abstract class ExpandingTabItem extends AbstractTabItem implements ExpandableTabItem {
    @Override
    public void onKeyPress(int key) {
        this.keyPressActions.forEach(action -> action.handle(key));

        if (key == GLFW.GLFW_KEY_RIGHT) {
            TabUI.INSTANCE.addContainer(this.open());
        }
    }
}
