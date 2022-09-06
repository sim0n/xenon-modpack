package dev.sim0n.modpack.event.bus.registry;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.handler.EventHandler;

import java.util.List;
import java.util.Map;

/**
 * @author sim0n
 */
public interface SubscriptionRegistry {
    Map<Class<?>, List<EventHandler>> getSubscribers();

    /**
     * Subscribe a handler to the event bus.
     * @param <T> The event type.
     * @param handler The handler to subscribe.
     */
    <T extends Event> void subscribe(Class<?> clazz, EventHandler<T> handler);

    /**
     * Unsubscribe a handler from the event bus.
     * @param <T> The event type.
     * @param handler The handler to unsubscribe.
     */
    <T extends Event> void unsubscribe(Class<?> clazz, EventHandler<T> handler);

    /**
     * Sorts the event handlers by priority.
     */
    void sort();
}
