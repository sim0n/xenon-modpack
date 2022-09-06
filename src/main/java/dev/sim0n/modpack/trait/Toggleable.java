package dev.sim0n.modpack.trait;

/**
 * @author sim0n
 */
public interface Toggleable extends Enableable, Disableable {
    void toggle();
}
