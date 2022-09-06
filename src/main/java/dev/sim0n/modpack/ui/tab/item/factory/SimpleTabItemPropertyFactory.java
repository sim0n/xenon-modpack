package dev.sim0n.modpack.ui.tab.item.factory;

import dev.sim0n.modpack.property.*;
import dev.sim0n.modpack.property.impl.*;
import dev.sim0n.modpack.property.impl.range.IntRangeProperty;
import dev.sim0n.modpack.mod.Mod;
import dev.sim0n.modpack.ui.tab.item.PropertyTabItem;
import dev.sim0n.modpack.ui.tab.item.impl.property.*;

import java.util.ArrayList;
import java.util.List;

public enum SimpleTabItemPropertyFactory implements TabItemPropertyFactory {
    INSTANCE;

    SimpleTabItemPropertyFactory() {

    }

    @Override
    public List<PropertyTabItem<?>> makeTabItems(Property<?> property, Mod mod) {
        List<PropertyTabItem<?>> tabItems = new ArrayList<>();

        if (property instanceof BoolProperty) {
            tabItems.add(new BoolPropertyTabItem(mod, (BoolProperty) property));
        } else if (property instanceof FloatProperty) {
            tabItems.add(new FloatPropertyTabItem(mod, (FloatProperty) property));
        } else if (property instanceof OptionProperty) {
            tabItems.add(new OptionPropertyTabItem(mod, (OptionProperty) property));
        } else if (property instanceof IntProperty) {
            tabItems.add(new IntPropertyTabItem(mod, (IntProperty) property));
        } else if (property instanceof IntRangeProperty) {
            tabItems.add(new IntRangePropertyTabItem(mod, (IntRangeProperty) property));
        } else if (property instanceof MultiOptionProperty) {
            // TODO: finish this
            /*MultiOptionProperty multiOptionProperty = (MultiOptionProperty) property;

            for (int i = 0; i < multiOptionProperty.getSize(); i++) {
                tabItems.add(new MultiOptionPropertyTabItem(mod, multiOptionProperty, i));
            }*/
        }

        return tabItems;
    }

    @Override
    public List<PropertyTabItem<?>> makeChildTabItems(Property<?> property, Mod mod) {
        return null;
    }
}
