package dev.sim0n.modpack.mod;

import dev.sim0n.modpack.ModPack;
import dev.sim0n.modpack.event.Event;
import dev.sim0n.modpack.event.bus.handler.impl.DefaultEventHandler;
import dev.sim0n.modpack.event.bus.handler.EventHandler;
import dev.sim0n.modpack.binding.Binding;
import dev.sim0n.modpack.property.repository.DefaultPropertyRepository;
import dev.sim0n.modpack.trait.Labeled;
import dev.sim0n.modpack.trait.Toggleable;
import dev.sim0n.modpack.util.Observable;
import dev.sim0n.modpack.util.helper.MCHelper;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.world.World;
import org.lwjgl.glfw.GLFW;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author sim0n
 */
@Getter
public class Mod extends DefaultPropertyRepository implements Labeled, Toggleable, MCHelper {
    protected final ModPack modPack = ModPack.getInstance();

    private final List<EventHandler> eventHandlers = new ArrayList<>();

    private final String label;
    private final ModCategory category;

    @Setter
    protected ClientPlayerEntity player;
    @Setter
    protected World world;

    private final Binding binding = modPack.getBindingSystem().makeBinding(this::toggle, new HashSet<>(Collections.singletonList(GLFW.GLFW_KEY_UNKNOWN)));

    private final Observable<Boolean> running = new Observable<>(false);

    public Mod() {
        Class<?> clazz = this.getClass();

        if (!clazz.isAnnotationPresent(ModManifest.class)) {
            throw new IllegalArgumentException("Mod class " + clazz.getName() + " is not annotated with @ModManifest");
        }

        ModManifest manifest = clazz.getAnnotation(ModManifest.class);

        this.label = manifest.label();
        this.category = manifest.category();

        this.running.addObserver(value -> {
            if (value) {
                this.onEnable();
            } else {
                this.onDisable();
            }
        });
    }

    @SafeVarargs
    public final <T extends Event> void registerEvent(Class<T> type, Consumer<T> consumer, int priority, Predicate<T>... filters) {
        this.eventHandlers.add(new DefaultEventHandler<>(type, consumer, priority, filters));
    }

    @SafeVarargs
    public final <T extends Event> void registerEvent(Class<T> type, Consumer<T> consumer, Predicate<T>... filters) {
        this.eventHandlers.add(new DefaultEventHandler<>(type, consumer, filters));
    }

    public boolean isRunning() {
        return this.running.get();
    }

    @Override
    public void onEnable() {
        this.eventHandlers.forEach(this.modPack.getEventBus()::subscribe);
    }

    @Override
    public void toggle() {
        this.running.set(!this.running.get());
    }

    @Override
    public void onDisable() {
        this.eventHandlers.forEach(this.modPack.getEventBus()::unsubscribe);
    }
}
