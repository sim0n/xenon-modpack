package dev.sim0n.modpack.event.bus.dispatcher;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.handler.EventHandler;

import java.util.List;

/**
 * @author sim0n
 */
public interface Dispatcher {

    default <T extends Event> void internalDispatch(T event, List<EventHandler> handlers) {
        if (handlers == null)
            return;

        for (EventHandler<T> handler : handlers) {
            if (handler.getFilters().stream().anyMatch(filter -> !filter.test(event))) {
                continue;
            }

            handler.invoke(event);
        }
    }

    /**
     * Dispatch an event to all handlers.
     * @param event The event to dispatch.
     * @param handlers The handlers to dispatch to.
     * @param <T> The event type.
     */
    <T extends Event> void dispatch(T event, List<EventHandler> handlers);
}
