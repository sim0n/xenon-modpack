package dev.sim0n.modpack.event.bus.registry;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.handler.EventHandler;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author sim0n
 */
@Getter
public abstract class AbstractSubscriptionRegistry implements SubscriptionRegistry {
    protected final Map<Class<?>, List<EventHandler>> subscribers = new ConcurrentHashMap<>();

    @Override
    public void sort() {
        this.subscribers.values().forEach(set -> set.sort(Comparator.comparingInt(EventHandler::getPriority)));
    }

    @Override
    public <T extends Event> void subscribe(Class<?> clazz, EventHandler<T> handler) {
        this.subscribers.computeIfAbsent(clazz, k -> new CopyOnWriteArrayList<>()).add(handler);
        this.sort();
    }

    @Override
    public <T extends Event> void unsubscribe(Class<?> clazz, EventHandler<T> handler) {
        this.subscribers.get(clazz).remove(handler);
        this.sort();
    }
}
