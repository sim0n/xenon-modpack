package dev.sim0n.modpack.property;

import dev.sim0n.modpack.property.repository.PropertyRepository;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author sim0n
 */

public abstract class AbstractProperty<T> implements Property<T> {
    @Getter
    private final List<AbstractProperty<?>> children = new ArrayList<>();

    private final List<Consumer<T>> changeListeners = new ArrayList<>();

    @Getter
    private String description;

    private Supplier<Boolean> visible = () -> true;

    @Getter @Setter
    private String label;

    @Getter
    protected T value;

    public AbstractProperty(String label, T value) {
        this.label = label;
        this.value = value;
    }

    @Override
    public void setValue(T value) {
        this.value = value;

        this.changeListeners.forEach(listener -> listener.accept(value));
    }

    @Override
    public boolean isVisible() {
        return this.visible.get();
    }

    public AbstractProperty<T> description(String description) {
        this.description = description;
        return this;
    }

    public AbstractProperty<T> addChangeListener(Consumer<T> consumer) {
        this.changeListeners.add(consumer);
        return this;
    }

    public AbstractProperty<T> visible(Supplier<Boolean> visible) {
        this.visible = visible;
        return this;
    }

    public AbstractProperty<T> addChild(AbstractProperty<?> child) {
        this.children.add(child);

        return this;
    }

    public <U> U buildAndBind(PropertyRepository repo) {
        repo.getProperties().add(this);

        return (U) this;
    }
}
