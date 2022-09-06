package dev.sim0n.modpack.property.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sim0n
 */
@Getter
public class MultiOptionProperty extends AbstractProperty<int[]> {
    private final int size;
    private final String[] options;

    private final List<String> selectedOptions = new ArrayList<>();

    public MultiOptionProperty(String label, String... options) {
        super(label, new int[options.length]);

        this.options = options;
        this.size = options.length;
    }

    @Override
    public void setValue(int[] values) {
        super.setValue(values);

        this.selectedOptions.clear();

        Arrays.stream(this.getValue())
                .forEach(i -> this.selectedOptions.add(this.options[i]));
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        JsonArray array = new JsonArray();

        Arrays.stream(this.getValue()).forEach(array::add);

        jsonObject.add("selected", array);

        return jsonObject;
    }
}

