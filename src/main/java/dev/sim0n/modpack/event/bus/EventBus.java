package dev.sim0n.modpack.event.bus;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.dispatcher.Dispatcher;
import dev.sim0n.modpack.event.bus.handler.impl.DefaultEventHandler;
import dev.sim0n.modpack.event.bus.handler.EventHandler;
import dev.sim0n.modpack.event.bus.registry.SubscriptionRegistry;
import lombok.RequiredArgsConstructor;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class EventBus {
    private final SubscriptionRegistry subscriptionRegistry;
    private final Dispatcher dispatcher;

    @SafeVarargs
    public final <T extends Event> void subscribe(Class<T> type, Consumer<T> consumer, int priority, Predicate<T>... filters) {
        this.subscribe(new DefaultEventHandler<>(type, consumer, priority, filters));
    }

    @SafeVarargs
    public final <T extends Event> void subscribe(Class<T> type, Consumer<T> consumer, Predicate<T>... filters) {
        this.subscribe(new DefaultEventHandler<>(type, consumer, filters));
    }

    /**
     * Subscribe a handler to the event bus.
     * @param <T> The event type.
     * @param handler The handler to subscribe.
     */
    public <T extends Event> void subscribe(EventHandler<T> handler) {
        this.subscriptionRegistry.subscribe(handler.getType(), handler);
    }

    /**
     * Unsubscribe a handler from the event bus.
     * @param <T> The event type.
     * @param handler The handler to unsubscribe.
     */
    public <T extends Event> void unsubscribe(EventHandler<T> handler) {
        this.subscriptionRegistry.unsubscribe(handler.getType(), handler);
    }

    /**
     * @param <T> The type of event to publish
     * @param event The event to publish
     * @return The published event
     */
    public <T extends Event> T publish(T event) {
        this.dispatcher.dispatch(event, this.subscriptionRegistry.getSubscribers().get(event.getClass()));

        return event;
    }

    public static AddSubscriptionRegistry builder() {
        return registry -> dispatcher -> new EventBus(registry, dispatcher);
    }

    public interface AddSubscriptionRegistry {
        AddDispatcher withSubscriptionRegistry(SubscriptionRegistry subscriptionRegistry);
    }

    public interface AddDispatcher {
        EventBus withDispatcher(Dispatcher dispatcher);
    }
}
