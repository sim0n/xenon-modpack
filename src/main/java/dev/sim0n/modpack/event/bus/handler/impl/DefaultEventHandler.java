package dev.sim0n.modpack.event.bus.handler.impl;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.EventPriority;
import dev.sim0n.modpack.event.bus.handler.EventHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author sim0n
 */
@Getter
@RequiredArgsConstructor
public class DefaultEventHandler<T extends Event> implements EventHandler<T> {
    private final Class<T> type;

    private final Consumer<T> consumer;

    private int priority = EventPriority.DEFAULT;

    private List<Predicate<T>> filters = new ArrayList<>();

    public DefaultEventHandler(Class<T> type, Consumer<T> consumer, int priority) {
        this.type = type;
        this.consumer = consumer;
        this.priority = priority;
    }

    @SafeVarargs
    public DefaultEventHandler(Class<T> type, Consumer<T> consumer, Predicate<T>... filters) {
        this.type = type;
        this.consumer = consumer;
        this.filters = Arrays.asList(filters);
    }

    @SafeVarargs
    public DefaultEventHandler(Class<T> type, Consumer<T> consumer, int priority, Predicate<T>... filters) {
        this.type = type;
        this.consumer = consumer;
        this.priority = priority;
        this.filters = Arrays.asList(filters);
    }

    @Override
    public void invoke(T event) {
        this.consumer.accept(event);
    }
}
