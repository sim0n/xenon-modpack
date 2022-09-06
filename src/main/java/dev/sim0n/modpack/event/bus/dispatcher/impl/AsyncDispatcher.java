package dev.sim0n.modpack.event.bus.dispatcher.impl;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.dispatcher.Dispatcher;
import dev.sim0n.modpack.event.bus.handler.EventHandler;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author sim0n
 */
public class AsyncDispatcher implements Dispatcher {
    private final Executor executor = Executors.newSingleThreadExecutor();

    @Override
    public <T extends Event> void dispatch(T event, List<EventHandler> handlers) {
        this.executor.execute(() -> this.internalDispatch(event, handlers));
    }
}
