package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.impl.OptionProperty;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.ui.tab.item.action.impl.FilteredKeyPressAction;
import dev.sim0n.modpack.ui.tab.item.action.impl.SimpleKeyPressAction;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.math.type.FVec2;
import dev.sim0n.modpack.util.render.OGLRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.lwjgl.glfw.GLFW;

import java.awt.*;

/**
 * @author sim0n
 */
public class OptionPropertyTabItem extends PropertyTabItem<OptionProperty> {
    public OptionPropertyTabItem(Mod mod, OptionProperty property) {
        super(mod, property);

        this.registerKeyPressAction(new SimpleKeyPressAction(GLFW.GLFW_KEY_ENTER, () -> {
            this.focused = !this.focused;
        }));

        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_RIGHT, () -> {
            var value = (this.property.getValue() + 1) % this.property.getSize();

            this.property.setValue(value);
        }, () -> this.focused));
        this.registerKeyPressAction(new FilteredKeyPressAction(GLFW.GLFW_KEY_LEFT, () -> {
            var value = this.property.getValue() - 1;

            if (value < 0) {
                value = this.property.getSize() - 1;
            }

            this.property.setValue(value);
        }, () -> this.focused));
    }

    @Override
    public String getText() {
        return this.property.getLabel() + ": " + this.property.getCurrentOption();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        var text = this.getProperty().getLabel() + ": ";
        var drawX = OGLRenderer.drawStringWithShadow(matrices, text, x + 3, y + 3, Color.WHITE.getRGB());
        var color = this.focused ? new Color(7, 90, 240).getRGB() : Colors.GREY.getRGB();

        OGLRenderer.drawStringWithShadow(matrices, this.getProperty().getCurrentOption(), drawX, y + 3, color);
    }

    @Override
    public void drawTab(FVec2 pos, FVec2 size) {
        if (!this.focused) {
            super.drawTab(pos, size);
            return;
        }

        OGLRenderer.filledRect(pos, size, new Color(5, 5, 5));
        OGLRenderer.filledRect(
                new FVec2(pos.getX() + size.getX() - 2, pos.getY()),
                new FVec2(2F, size.getY()),
                new Color(7, 90, 240));

        //OGLRenderer.filledRect(pos, size, new Color(7, 90, 240));
    }
}
