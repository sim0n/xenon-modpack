package dev.sim0n.modpack.property;

import com.google.gson.JsonObject;
import dev.sim0n.modpack.trait.Describable;
import dev.sim0n.modpack.trait.Labeled;
import dev.sim0n.modpack.trait.Visible;

/**
 * @author sim0n
 */
public interface Property<T> extends Labeled, Describable, Visible {

    /**
     * @return The value of this property
     */
    T getValue();

    /**
     * @param value The value to set this property to
     */
    void setValue(T value);

    JsonObject serialize();
}
