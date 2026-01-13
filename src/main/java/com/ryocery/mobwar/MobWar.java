package com.ryocery.mobwar;

import com.mojang.logging.LogUtils;
import com.ryocery.mobwar.mobs.profiles.ProfileRegistry;
import com.ryocery.mobwar.mobs.Species;
import com.ryocery.mobwar.mobs.profiles.species.BeastProfile;
import com.ryocery.mobwar.mobs.profiles.species.EnderProfile;
import com.ryocery.mobwar.mobs.profiles.species.IllagerProfile;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(MobWar.MODID)
public class MobWar {
    public static final String MODID = "mobwar";
    public static final Logger LOGGER = LogUtils.getLogger();

    public MobWar() {
        LOGGER.info("MobWar has loaded successfully.");

        ProfileRegistry.register(Species.ENDER, new EnderProfile());
        ProfileRegistry.register(Species.BEASTS, new BeastProfile());
        ProfileRegistry.register(Species.ILLAGERS, new IllagerProfile());
    }
}