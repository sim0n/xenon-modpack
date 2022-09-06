package dev.sim0n.modpack.ui.tab;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.impl.game.key.KeyEvent;
import dev.sim0n.modpack.event.impl.game.key.KeyState;
import dev.sim0n.modpack.event.impl.render.RenderScreenEvent;
import dev.sim0n.modpack.event.impl.render.RenderTickEvent;
import dev.sim0n.modpack.mod.ModCategory;
import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import dev.sim0n.modpack.ui.tab.item.impl.CategoryExpandableTabItem;
import dev.sim0n.modpack.util.helper.MCHelper;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sim0n
 */
public enum TabUI implements MCHelper {
    INSTANCE;

    private final ModPack modPack = ModPack.getInstance();

    private final Stack<TabItemContainer> containers = new Stack<>();

    @Getter
    private final TabItemContainer mainContainer = new TabItemContainer();

    public void init() {
        Arrays.stream(ModCategory.values())
                .forEach(category -> this.mainContainer.add(new CategoryExpandableTabItem(category)));

        this.containers.add(this.mainContainer);

        this.modPack.getEventBus().subscribe(KeyEvent.class, event -> {
            var container = this.containers.peek();
            var key = event.getCode();

            if (container.isClosing()) {
                return;
            }

            if (key == GLFW.GLFW_KEY_LEFT && this.containers.size() > 1) {
                if (!container.getTabCursor().getSelectedItem().isFocused()) {
                    container.close();
                } else {
                    container.onKeyPress(key);
                }
            } else {
                container.onKeyPress(key);
            }
        }, event -> event.getState() == KeyState.PRESSED, event -> mc.currentScreen == null);

        this.modPack.getEventBus().subscribe(RenderScreenEvent.class, event -> {
            var x = new AtomicReference<>(0F);
            var y = new AtomicReference<>(0F);

            var topContainer = this.containers.peek();

            if (topContainer.isClosing() && topContainer.getAnimation().get() == topContainer.getAnimation().getMin()) {
                this.containers.pop();
            }

            this.containers.forEach(container -> {
                container.draw(event.getMatrices(), 2 + x.get(), 2 + y.get());

                x.updateAndGet(v -> v + container.getWidth());
                y.updateAndGet(v -> v + (container.getTabCursor().getCursorPosition()));
            });

        }, event -> !mc.options.debugEnabled);

        this.modPack.getEventBus().subscribe(RenderTickEvent.class, event -> {
            this.containers.forEach(container -> container.think(event.getTick()));
        });
    }

    public void addContainer(TabItemContainer container) {
        if (container == null)
            return;

        this.containers.add(container);
    }
}
