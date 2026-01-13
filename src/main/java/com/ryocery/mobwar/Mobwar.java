package com.ryocery.mobwar;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(Mobwar.MODID)
public class Mobwar {
    public static final String MODID = "mobwar";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mobwar() {
        LOGGER.info("MobWar has loaded successfully.");
    }
}