package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.impl.range.IntRangeProperty;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.ui.tab.item.action.impl.FilteredKeyPressAction;
import dev.sim0n.modpack.ui.tab.item.action.impl.SimpleKeyPressAction;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.math.type.FVec2;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.Arrays;
import java.util.function.Supplier;

/**
 * @author sim0n
 */
public class IntRangePropertyTabItem extends PropertyTabItem<IntRangeProperty> {
    private boolean upper;

    public IntRangePropertyTabItem(Mod mod, IntRangeProperty property) {
        super(mod, property);

        Supplier<Boolean> focusedSupplier = () -> this.focused;

        this.registerKeyPressAction(new SimpleKeyPressAction(GLFW.GLFW_KEY_ENTER, () -> {
            this.focused = !this.focused;
        }));

        Arrays.asList(GLFW.GLFW_KEY_UP, GLFW.GLFW_KEY_DOWN)
                .forEach(key -> {
                    this.registerKeyPressAction(new FilteredKeyPressAction(key, () -> {
                        this.upper = !this.upper;
                    }, focusedSupplier));
                });

        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_RIGHT, () -> {
            this.property.increase(upper);
        }, focusedSupplier));

        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_LEFT, () -> {
            this.property.decrease(upper);
        }, focusedSupplier));
    }

    @Override
    public String getText() {
        return this.property.getLabel();
    }
    
    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var width = this.parent.getWidth();

        if (this.focused)
            OGLRenderer.filledRect(new FVec2(x, y), new FVec2(width, 13), new Color(5, 5, 5));

        var value = upper ? this.property.getValue().getUpper() : this.property.getValue().getLower();
        String valueText = "" + value;

        matrices.scale(0.5F, 0.5F, 0F);
        mc.textRenderer.drawWithShadow(matrices, (upper ? "Max " : "Min ") + this.property.getLabel(), (x + 8) * 2, (y + 2) * 2, Color.WHITE.getRGB());
        mc.textRenderer.drawWithShadow(matrices, valueText, (x + width - 3 - mc.textRenderer.getWidth(valueText) / 2.f) * 2, (y + 2) * 2, Color.WHITE.getRGB());
        matrices.scale(2F, 2F, 2F);

        for (var i = 0; i < 4; i++) {
            float x1 = 4 - i * 0.5F;
            float y1 = 4 + i * 0.5F;
            float w1 = 0.5F + i;

            // draw upper triangle
            OGLRenderer.filledRect(new FVec2(x + x1, y + y1), new FVec2(w1, 0.5F), Color.WHITE);

            x1 = 2.5F + i * 0.5F;
            y1 = 7 + i * 0.5F;
            w1 = 3.5F - i;

            // draw lower triangle
            OGLRenderer.filledRect(new FVec2(x + x1, y + y1), new FVec2(w1, 0.5F), Color.WHITE);
        }

        OGLRenderer.filledRect(new FVec2(x + 8, y + 8), new FVec2(this.getParent().getWidth() - 11, 1), Colors.GREY);
        OGLRenderer.filledRect(new FVec2(x + 8, y + 8), new FVec2((this.getParent().getWidth() - 11) * (value / (float) this.getProperty().getMax()), 1), this.focused ? Colors.ACCENT : Color.WHITE);
    }
}
