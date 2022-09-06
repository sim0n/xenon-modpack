package dev.sim0n.modpack.ui.tab.item;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.AbstractProperty;
import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import dev.sim0n.modpack.ui.tab.item.factory.SimpleTabItemPropertyFactory;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.render.OGLRenderer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.util.math.MatrixStack;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public abstract class PropertyTabItem<T extends AbstractProperty<?>> extends ExpandingTabItem {
    protected final Mod mod;
    protected final T property;

    @Override
    public TabItemContainer open() {
        var container = new TabItemContainer();

        var children = this.property.getChildren();

        if (children.isEmpty())
            return null;

        children.forEach(child -> {
            var tabItems = SimpleTabItemPropertyFactory.INSTANCE.makeTabItems(child, mod);

            tabItems.forEach(container::add);
        });

        return container;
    }

    @Override
    public String getText() {
        var character = "\247" + (this.focused ? "f" : "7");

        return property.getLabel() + ": " + character + property.getValue();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        if (this.property.getChildren().size() > 0) {
            //OGLRenderer.filledRect(new FVec2(x + this.getParent().getWidth() - 1.5F, y), new FVec2(1.5F, 13), Color.ACCENT);
             OGLRenderer.drawStringWithShadow(matrices, ">", x + this.getParent().getWidth() - 7, y + 3F, Colors.GREY.getRGB());
        }
    }

    @Override
    public boolean isVisible() {
        return this.property.isVisible();
    }
}
