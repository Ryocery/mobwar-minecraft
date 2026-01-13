package com.ryocery.mobwar;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Mobwar.MODID)
public class Mobwar {
    public static final String MODID = "mobwar";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Mobwar(IEventBus bus) {
        NeoForge.EVENT_BUS.register(this);
        LOGGER.info("MobWar has loaded successfully.");
    }
}