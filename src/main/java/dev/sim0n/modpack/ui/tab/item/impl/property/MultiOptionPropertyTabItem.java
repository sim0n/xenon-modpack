package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.impl.MultiOptionProperty;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

/**
 * @author sim0n
 */
public class MultiOptionPropertyTabItem extends PropertyTabItem<MultiOptionProperty> {
    private final int index;

    public MultiOptionPropertyTabItem(Mod mod, MultiOptionProperty property, int index) {
        super(mod, property);

        this.index = index;
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var text = this.getProperty().getOptions()[this.index];
        var drawX = OGLRenderer.drawStringWithShadow(matrices, text, x + 3, y + 3, Color.WHITE.getRGB());
        var color = this.focused ? new Color(7, 90, 240).getRGB() : Colors.GREY.getRGB();

        OGLRenderer.drawStringWithShadow(matrices, text, drawX, y + 3, color);
    }
}
