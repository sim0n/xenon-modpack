package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.impl.IntProperty;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.ui.tab.item.action.impl.FilteredKeyPressAction;
import dev.sim0n.modpack.ui.tab.item.action.impl.SimpleKeyPressAction;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.math.type.FVec2;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.util.function.Supplier;

/**
 * @author sim0n
 */
public class IntPropertyTabItem extends PropertyTabItem<IntProperty> {
    public IntPropertyTabItem(Mod mod, IntProperty property) {
        super(mod, property);

        Supplier<Boolean> focusedSupplier = () -> this.focused;

        this.registerKeyPressAction(new SimpleKeyPressAction(GLFW.GLFW_KEY_ENTER, () -> this.focused = !this.focused));
        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_RIGHT, () -> {
            var handle = mc.getWindow().getHandle();
            var power = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_CONTROL) ? 10 : 1;

            this.property.increase(power);
        }, focusedSupplier));
        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_LEFT, () -> {
            var handle = mc.getWindow().getHandle();
            var power = InputUtil.isKeyPressed(handle, GLFW.GLFW_KEY_LEFT_CONTROL) ? 10 : 1;

            this.property.decrease(power);
        }, focusedSupplier));
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var width = this.getParent().getWidth();

        if (this.focused)
            OGLRenderer.filledRect(new FVec2(x, y), new FVec2(width, 13), new Color(5, 5, 5));

        matrices.push();
        matrices.scale(0.5F, 0.5F, 0F);
        mc.textRenderer.drawWithShadow(matrices, this.property.getLabel(), (x + 3) * 2, (y + 2) * 2, Colors.WHITE.getRGB());

        String valueText = "" + this.property.getValue();

        mc.textRenderer.drawWithShadow(matrices, valueText, (x + width - 3 - mc.textRenderer.getWidth(valueText) / 2.f) * 2, (y + 2) * 2, Colors.WHITE.getRGB());
        matrices.scale(2F, 2F, 2F);
        matrices.pop();

        OGLRenderer.filledRect(new FVec2(x + 3, y + 8), new FVec2((width - 6), 1), Colors.GREY);
        OGLRenderer.filledRect(new FVec2(x + 3, y + 8), new FVec2((width - 6) * (this.getProperty().getValue() / (float) this.getProperty().getMax()), 1), this.focused ? Colors.ACCENT : Color.WHITE);
    }
}
