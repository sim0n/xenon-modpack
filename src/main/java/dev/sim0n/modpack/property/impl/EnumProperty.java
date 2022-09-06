package dev.sim0n.modpack.property.impl;

import com.google.gson.JsonObject;

import dev.sim0n.modpack.property.AbstractProperty;

/**
 * @author sim0n
 */
public class EnumProperty extends AbstractProperty<Enum<?>> {
    public EnumProperty(String label, Enum<?> value) {
        super(label, value);
    }

    public Enum<?>[] getValues() {
        Class<? extends Enum<?>> clazz = (Class<? extends Enum<?>>) getValue().getClass();

        return clazz.getEnumConstants();
    }

    @Override
    public JsonObject serialize() {
        return new JsonObject();
    }
    
}
