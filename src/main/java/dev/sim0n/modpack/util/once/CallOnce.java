package dev.sim0n.modpack.util.once;

import lombok.RequiredArgsConstructor;

/**
 * @author sim0n
 */
@RequiredArgsConstructor
public class CallOnce {
    private final Runnable runnable;

    private boolean called;

    public void call() {
        if (called)
            return;

        called = true;

        runnable.run();
    }
}
