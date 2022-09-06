package dev.sim0n.modpack.property.repository;

import dev.sim0n.modpack.property.Property;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sim0n
 */
@Getter
public class DefaultPropertyRepository implements PropertyRepository {
    private final List<Property<?>> properties = new ArrayList<>();
}
