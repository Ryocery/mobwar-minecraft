package com.ryocery.mobwar;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MobWar.MODID)
public class MobWar {
    public static final String MODID = "mobwar";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MobWar() {
        LOGGER.info("MobWar has loaded successfully.");
    }
}