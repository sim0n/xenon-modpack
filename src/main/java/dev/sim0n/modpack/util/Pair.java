package dev.sim0n.modpack.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author sim0n
 */
@Getter
@AllArgsConstructor
public class Pair<T> {
    private T first;
    private T second;
}
