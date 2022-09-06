package dev.sim0n.modpack.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author sim0n
 */
@AllArgsConstructor
public class Observable<T> {
    @Getter
    private final List<Consumer<T>> observers = new ArrayList<>();

    private T value;

    public void addObserver(Consumer<T> observer) {
        this.observers.add(observer);
    }

    public void set(T value) {
        this.value = value;
        this.observers.forEach(observer -> observer.accept(value));
    }

    public T get() {
        return this.value;
    }
}
