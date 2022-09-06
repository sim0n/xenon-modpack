package dev.sim0n.modpack.property.impl;

import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;

/**
 * @author sim0n
 */
@Getter
public class FloatProperty extends AbstractProperty<Float> {
    private final float min;
    private final float max;

    private float step = 1F;

    private String format = "%.1f";

    public FloatProperty(String label, float value, float min, float max) {
        super(label, value);

        this.min = min;
        this.max = max;
    }

    public FloatProperty(String label, float value, float min, float max, float step) {
        super(label, value);

        this.min = min;
        this.max = max;
        this.step = step;
    }

    public void increase(int amount) {
        this.value += this.step * amount;

        this.value = Math.min(Math.max(this.value, this.min), this.max);
    }

    public void decrease(int amount) {
        this.value -= this.step * amount;

        this.value = Math.min(Math.max(this.value, this.min), this.max);
    }

    public FloatProperty setFormat(String format) {
        this.format = format;

        return this;
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        jsonObject.addProperty("value", this.getValue());

        return jsonObject;
    }
}
