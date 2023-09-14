package com.kneelawk.airbag;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class AirbagInit implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        AirbagLog.LOG.info("Airbag v{} Initializing!", mod.metadata().version());
        AirbagLog.LOG.info("Airbag is a simple mod with mixins to prevent and help debug some specific crashes.");
    }
}
