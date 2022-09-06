package dev.sim0n.modpack.ui.tab.item.impl;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.ui.tab.item.factory.SimpleTabItemPropertyFactory;
import dev.sim0n.modpack.ui.tab.item.ToggleableExpandableTabItem;
import dev.sim0n.modpack.ui.tab.item.action.impl.SimpleKeyPressAction;
import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.animation.Easing;
import dev.sim0n.modpack.util.animation.LinearlyUpdatingInt;
import dev.sim0n.modpack.util.helper.MathHelper;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

/**
 * @author sim0n
 */
public class ToggleableModExpandableTabItem extends ToggleableExpandableTabItem {
    private final LinearlyUpdatingInt animation = new LinearlyUpdatingInt(0, 12, 1);

    private static final Color DISABLED_COL = Colors.GREY;
    private static final Color ENABLED_COL = Colors.WHITE;

    private final Mod mod;

    public ToggleableModExpandableTabItem(Mod mod) {
        this.mod = mod;

        if (this.mod.isRunning()) {
            this.animation.setValue(this.animation.getMax());
        }

        this.registerKeyPressAction(new SimpleKeyPressAction(GLFW.GLFW_KEY_ENTER, this.mod::toggle));
    }

    @Override
    public TabItemContainer open() {
        var container = new TabItemContainer();

        this.mod.getProperties().forEach(property -> {
            var items = SimpleTabItemPropertyFactory.INSTANCE.makeTabItems(property, mod);

            if (items.isEmpty())
                return;

            items.forEach(container::add);
        });

        return container.getTabItems().isEmpty() ? null : container;
    }

    @Override
    public String getText() {
        return this.mod.getLabel();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var progress = this.animation.getEasedProgress(Easing.SLOW_TO_FAST);

        var color = new Color(
                MathHelper.interpolate(DISABLED_COL.getRed(), ENABLED_COL.getRed(), progress),
                MathHelper.interpolate(DISABLED_COL.getGreen(), ENABLED_COL.getGreen(), progress),
                MathHelper.interpolate(DISABLED_COL.getBlue(), ENABLED_COL.getBlue(), progress)
        );

        OGLRenderer.drawStringWithShadow(matrices, this.getText(), x + 3 + (3 * this.getHoverAnimation().getProgress()), y + 3F, color.getRGB());

        if (this.mod.getProperties().size() > 0) {
            OGLRenderer.drawStringWithShadow(matrices, ">", x + this.getParent().getWidth() - 7, y + 3F, Colors.GREY.getRGB());
        }
    }

    @Override
    public void think(int tick) {
        super.think(tick);

        this.animation.update(this.mod.isRunning());
    }
}
