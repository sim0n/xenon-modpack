package dev.sim0n.modpack.event.impl.render;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.util.math.type.DVec2;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.util.math.MatrixStack;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public class RenderScreenEvent extends Event {
    private final MatrixStack matrices;
    private final DVec2 screenSize;
}
