package dev.sim0n.modpack.property.impl;


import com.google.gson.JsonObject;
import dev.sim0n.modpack.property.AbstractProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
public class BoolProperty extends AbstractProperty<Boolean> {
    public BoolProperty(String label, boolean value) {
        super(label, value);
    }

    @Override
    public JsonObject serialize() {
        var jsonObject = new JsonObject();

        jsonObject.addProperty("value", this.getValue());

        return jsonObject;
    }
}
