package dev.sim0n.modpack.event.bus.handler;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.EventPriority;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author sim0n
 */
public interface EventHandler<T extends Event> {
    /**
     * @return The event type.
     */
    Class<? extends T> getType();

    /**
     * @return The priority of this handler.
     */
    default int getPriority() {
        return EventPriority.DEFAULT;
    }

    /**
     * @return The filters of this handler.
     */
    default List<Predicate<T>> getFilters() {
        return new ArrayList<>();
    }

    /**
     * Invokes the event to the handler.
     * @param event The event.
     */
    void invoke(T event);
}
