package dev.sim0n.modpack.event.bus.dispatcher.impl;

import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.dispatcher.Dispatcher;
import dev.sim0n.modpack.event.bus.handler.EventHandler;

import java.util.List;

/**
 * @author sim0n
 */
public enum ImmediateDispatcher implements Dispatcher {
    INSTANCE;

    @Override
    public <T extends Event> void dispatch(T event, List<EventHandler> handlers) {
        this.internalDispatch(event, handlers);
    }
}
