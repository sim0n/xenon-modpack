package dev.sim0n.modpack.property.impl.range;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Getter @Setter
@AllArgsConstructor
public class Range<T> {
    private T lower;
    private T upper;
}
