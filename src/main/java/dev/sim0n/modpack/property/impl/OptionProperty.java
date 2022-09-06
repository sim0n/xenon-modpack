package dev.sim0n.modpack.property.impl;

import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;

/**
 * @author sim0n
 */
public class OptionProperty extends AbstractProperty<Integer> {
    @Getter
    private final int size;
    private final String[] options;

    public OptionProperty(String label, Integer value, String... options) {
        super(label, value);

        this.options = options;
        this.size = options.length;
    }

    public String getCurrentOption() {
        return this.options[this.getValue()];
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        jsonObject.addProperty("value", this.getValue());

        return jsonObject;
    }
}
