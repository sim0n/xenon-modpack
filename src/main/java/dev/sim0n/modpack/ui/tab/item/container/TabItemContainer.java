package dev.sim0n.modpack.ui.tab.item.container;

import dev.sim0n.modpack.ui.tab.item.AbstractTabItem;
import dev.sim0n.modpack.ui.tab.item.TabItem;
import dev.sim0n.modpack.ui.tab.item.cursor.factory.SimpleTabCursorFactory;
import dev.sim0n.modpack.ui.tab.item.cursor.TabCursor;
import dev.sim0n.modpack.util.animation.LinearlyUpdatingInt;
import dev.sim0n.modpack.util.math.type.FVec2;
import dev.sim0n.modpack.util.render.OGLRenderer;
import lombok.Getter;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author sim0n
 */
@Getter
public class TabItemContainer implements TabItem {
    private static final int FONT_HEIGHT = 13;

    private final List<AbstractTabItem> tabItems = new ArrayList<>();
    private final LinearlyUpdatingInt animation = new LinearlyUpdatingInt(0, 10, 1);

    private final TabCursor tabCursor = SimpleTabCursorFactory.INSTANCE.makeTabCursor();

    private boolean closing;

    private float width = 55;
    private int height = 0;

    @Override
    public void onKeyPress(int key) {
        this.tabCursor.onKeyPress(key, this.tabItems);

        this.updateWidth();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var progress = this.animation.getProgress();
        var animating = progress != 1;

        if (animating) {
            OGLRenderer.prepareScissorBox(x, y, this.getWidth() * progress, height + 1);
        }

        OGLRenderer.filledRect(new FVec2(x, y), new FVec2(this.getWidth(), height), new Color(25, 25, 25, 150));

        this.tabCursor.updateCursorPosition(this.tabCursor.getCursorIndex() * FONT_HEIGHT);
        
        var selectedItem = this.tabCursor.getSelectedItem();

        selectedItem.setHovered(true);
        selectedItem.drawTab(new FVec2(x, y + this.tabCursor.getCursorPosition()), new FVec2(this.getWidth(), FONT_HEIGHT));

        var tabItemOffsetY = new AtomicReference<>(0F);

        this.tabItems.forEach(tabItem -> {
            tabItem.draw(matrices, x, y + tabItemOffsetY.get());

            tabItemOffsetY.getAndUpdate(v -> v + FONT_HEIGHT);

            if (selectedItem != tabItem)
                tabItem.setHovered(false);
        });

        if (animating) {
            OGLRenderer.endScissor();
        }
    }

    @Override
    public void think(int tick) {
        this.animation.update(!this.closing);

        this.tabItems.forEach(item -> item.think(tick));
    }

    public void close() {
        this.closing = true;

        this.animation.setAmount(2);
    }

    public void updateWidth() {
        this.width = 55;

        var recalculatedWidth = this.tabItems.stream()
                //.filter(TabItem::isVisible)
                .mapToInt(tab -> mc.textRenderer.getWidth(tab.getText()))
                .max()
                .orElse(0) + 20;

        if (recalculatedWidth > this.width) {
            this.width = recalculatedWidth;
        }
    }

    public void add(AbstractTabItem item) {
        if (this.tabItems.size() == 0) {
            this.tabCursor.setSelectedItem(item);
        }

        item.setParent(this);

        this.height += FONT_HEIGHT;
        this.tabItems.add(item);
        this.updateWidth();
    }
}
