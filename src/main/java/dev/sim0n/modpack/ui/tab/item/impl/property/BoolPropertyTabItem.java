package dev.sim0n.modpack.ui.tab.item.impl.property;

import dev.sim0n.modpack.property.impl.BoolProperty;
import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.ui.tab.item.action.impl.SimpleKeyPressAction;
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
public class BoolPropertyTabItem extends PropertyTabItem<BoolProperty> {
    private static final Color DISABLED_COL = Colors.GREY;
    private static final Color ENABLED_COL = Colors.WHITE;

    private final LinearlyUpdatingInt animation = new LinearlyUpdatingInt(0, 15, 1);

    public BoolPropertyTabItem(Mod mod, BoolProperty property) {
        super(mod, property);

        if (property.getValue()) {
            this.animation.setValue(this.animation.getMax());
        }

        this.registerKeyPressAction(new SimpleKeyPressAction(GLFW.GLFW_KEY_ENTER, () -> {
            this.property.setValue(!this.property.getValue());
        }));
    }

    @Override
    public String getText() {
        return this.property.getLabel();
    }

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        super.draw(matrices, x, y);

        var progress = this.animation.getEasedProgress(Easing.SLOW_TO_FAST);

        Color color = new Color(
                MathHelper.interpolate(DISABLED_COL.getRed(), ENABLED_COL.getRed(), progress),
                MathHelper.interpolate(DISABLED_COL.getGreen(), ENABLED_COL.getGreen(), progress),
                MathHelper.interpolate(DISABLED_COL.getBlue(), ENABLED_COL.getBlue(), progress)
        );

        OGLRenderer.drawStringWithShadow(matrices, this.getText(), x + 3, y + 3, color.getRGB());
    }

    @Override
    public void think(int tick) {
        super.think(tick);

        this.animation.update(this.property.getValue());
    }

}
