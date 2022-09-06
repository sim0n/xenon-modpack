package dev.sim0n.modpack.ui.tab.item;

import dev.sim0n.modpack.ui.tab.item.container.TabItemContainer;
import dev.sim0n.modpack.ui.tab.item.container.TextContainer;
import dev.sim0n.modpack.ui.tab.item.action.KeyPressAction;
import dev.sim0n.modpack.util.Colors;
import dev.sim0n.modpack.util.animation.LinearlyUpdatingInt;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.util.math.MatrixStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sim0n
 */
@Getter @Setter
public abstract class AbstractTabItem implements TabItem, TextContainer {
    protected final LinearlyUpdatingInt hoverAnimation = new LinearlyUpdatingInt(0, 8, 1);
    protected final List<KeyPressAction> keyPressActions = new ArrayList<>();

    protected TabItemContainer parent;

    protected boolean focused;

    private boolean hovered;

    @Override
    public void draw(MatrixStack matrices, float x, float y) {
        mc.textRenderer.drawWithShadow(matrices, this.getText(), x + 3, y + 2, Colors.WHITE.getRGB());
    }

    @Override
    public void think(int tick) {
        this.hoverAnimation.update(this.hovered);
    }

    public void registerKeyPressAction(KeyPressAction action) {
        this.keyPressActions.add(action);
    }

}
