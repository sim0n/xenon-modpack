package dev.sim0n.modpack.ui.tab.item.impl;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.mod.ModCategory;
import dev.sim0n.modpack.ui.tab.item.ExpandingTabItem;
import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.render.OGLRenderer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.client.util.math.MatrixStack;

import java.util.Comparator;

/**
 * @author sim0n
 */
@Getter
@AllArgsConstructor
public class CategoryExpandableTabItem extends ExpandingTabItem {
    private ModCategory category;

    @Override
    public TabItemContainer open() {
        var container = new TabItemContainer();

        ModPack.getInstance().getModManager().getMods()
                .stream()
                .filter(mod -> mod.getCategory() == this.category)
                .sorted(Comparator.comparingInt(mod -> -mc.textRenderer.getWidth(mod.getLabel())))
                .forEach(mod -> container.add(new ToggleableModExpandableTabItem(mod)));

        return container;
    }

    @Override
    public String getText() {
        return this.category.getLabel();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        OGLRenderer.drawStringWithShadow(matrices, this.getText(), x + 3 + (3 * this.getHoverAnimation().getProgress()), y + 3F, Colors.WHITE.getRGB());
    }
}
