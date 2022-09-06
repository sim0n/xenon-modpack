package dev.sim0n.modpack;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sim0n
 */
public class ModPackInitializer implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("xenon");

    @Override
    public void onInitialize() {
        ModPack modPack = new ModPack();
        modPack.init();

        LOGGER.info("Started Xenon ModPack");
    }
}
