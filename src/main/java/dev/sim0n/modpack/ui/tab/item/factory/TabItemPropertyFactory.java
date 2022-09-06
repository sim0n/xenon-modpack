package dev.sim0n.modpack.ui.tab.item.factory;

import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.property.Property;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;

import java.util.List;

/**
 * @author sim0n
 */
public interface TabItemPropertyFactory {

    /**
     * Make a tab item for the given property.
     * @param property The property to create a tab item for
     * @param mod The mod the property belongs to
     * @return A tab item for the property
     */
    List<PropertyTabItem<?>> makeTabItems(Property<?> property, Mod mod);

    /**
     * Make a tab item for the given property.
     * @param property The property to create a tab item for
     * @param mod The mod the property belongs to
     * @return A tab item for the property
     */
    List<PropertyTabItem<?>> makeChildTabItems(Property<?> property, Mod mod);
}
