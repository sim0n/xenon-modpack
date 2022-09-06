package dev.sim0n.modpack.property.impl.range;

import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;

/**
 * @author sim0n
 */
@Getter
public class IntRangeProperty extends AbstractProperty<Range<Integer>> {
    private final int min;
    private final int max;

    private int step = 1;

    public IntRangeProperty(String label, int lower, int upper, int min, int max) {
        super(label, new Range<>(lower, upper));

        this.min = min;
        this.max = max;
    }

    public IntRangeProperty(String label, int lower, int upper, int min, int max, int step) {
        super(label, new Range<>(lower, upper));

        this.min = min;
        this.max = max;
        this.step = step;
    }

    public void increase(boolean upperbound) {
        var value = this.getValue();

        if (upperbound) {
            value.setUpper(value.getUpper() + step);
            value.setUpper(Math.min(Math.max(value.getUpper(), min), max));
        } else {
            value.setLower(value.getLower() + step);
            value.setLower(Math.min(Math.max(value.getLower(), min), value.getUpper()));
        }

        this.setValue(value);
    }

    public void decrease(boolean upperbound) {
        var value = this.getValue();

        if (upperbound) {
            value.setUpper(value.getUpper() - step);
            value.setUpper(Math.max(value.getUpper(), value.getLower()));
        } else {
            value.setLower(value.getLower() - step);
            value.setLower(Math.max(value.getLower(), min));
        }

        this.setValue(value);
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        var range = this.getValue();

        jsonObject.addProperty("lower", range.getLower());
        jsonObject.addProperty("upper", range.getUpper());

        return jsonObject;
    }
}
