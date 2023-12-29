package com.kneelawk.airbag;

import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class AirbagPreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch(ModContainer mod) {
        AirbagLog.LOG.info("Airbag v{} PreLaunch!", mod.metadata().version());
    }
}
