package dev.sim0n.modpack.property.repository;

import dev.sim0n.modpack.property.Property;

import java.util.List;

/**
 * @author sim0n
 */
public interface PropertyRepository {
    List<Property<?>> getProperties();
}
