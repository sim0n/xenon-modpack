package dev.sim0n.modpack.property.impl;

import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;

/**
 * @author sim0n
 */
@Getter
public class IntProperty extends AbstractProperty<Integer> {
    private final int min;
    private final int max;

    private int step = 1;

    public IntProperty(String label, int value, int min, int max) {
        super(label, value);

        this.min = min;
        this.max = max;
    }

    public IntProperty(String label, int value, int min, int max, int step) {
        super(label, value);

        this.min = min;
        this.max = max;
        this.step = step;
    }

    public void increase(int power) {
        this.value += this.step * power;

        this.value = Math.min(Math.max(this.value, this.min), this.max);
    }

    public void decrease(int power) {
        this.value -= this.step * power;

        this.value = Math.min(Math.max(this.value, this.min), this.max);
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        jsonObject.addProperty("value", this.getValue());

        return jsonObject;
    }
}
