package dev.sim0n.modpack.event.impl.game.key;

import dev.sim0n.modpack.event.Event;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public class KeyEvent extends Event {
    private final int code;

    private final KeyState state;
}
