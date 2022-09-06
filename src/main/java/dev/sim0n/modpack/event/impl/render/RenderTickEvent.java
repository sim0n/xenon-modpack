package dev.sim0n.modpack.event.impl.render;

import dev.sim0n.modpack.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public class RenderTickEvent extends Event {
    private final int tick;
}
