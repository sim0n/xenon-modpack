package dev.sim0n.modpack.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
public abstract class Event {
    private boolean cancelled;
}
