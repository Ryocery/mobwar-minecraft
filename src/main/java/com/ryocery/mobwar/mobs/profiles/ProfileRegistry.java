package com.ryocery.mobwar.mobs.profiles;

import com.ryocery.mobwar.mobs.Species;

import java.util.EnumMap;
import java.util.Map;

public class ProfileRegistry {
    private static final Map<Species, SpeciesProfile> profiles = new EnumMap<>(Species.class);
    private static final SpeciesProfile DEFAULT_PROFILE = new DefaultProfile();

    public static void register(Species species, SpeciesProfile profile) {
        profiles.put(species, profile);
    }

    public static SpeciesProfile get(Species species) {
        return profiles.getOrDefault(species, DEFAULT_PROFILE);
    }
}