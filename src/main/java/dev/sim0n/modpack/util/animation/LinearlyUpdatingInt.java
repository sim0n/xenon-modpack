package dev.sim0n.modpack.util.animation;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sim0n
 */
@Setter
public class LinearlyUpdatingInt {

    private int value;

    @Getter
    private int min;
    @Getter
    private int max;

    private int amount;

    public LinearlyUpdatingInt(int min, int max, int amount) {
        this.min = min;
        this.max = max;

        this.amount = amount;

        this.value = this.min;
    }

    /**
     * Updates the value of this {@link LinearlyUpdatingInt}
     * @param direction The direction of the update
     */
    public void update(boolean direction) {
        if (this.value < this.max && direction) {
            this.value += this.amount;
        } else if (this.value > this.min && !direction) {
            this.value -= this.amount;
        }

        this.value = Math.min(Math.max(this.value, this.min), this.max);
    }

    /**
     * OGLRenderer.drawStringWithShadowThe progress of this {@link LinearlyUpdatingInt}
     */
    public float getProgress() {
        return this.value / (float) this.max;
    }

    /**
     * @param easing the easing function to use
     * @return the eased value
     */
    public float getEasedProgress(Easing easing) {
        return (float) easing.apply(this.value / (float) this.max);
    }

    public int get() {
        return this.value;
    }
}

