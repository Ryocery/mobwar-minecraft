package com.ryocery.mobwar;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;

public enum Species {
    ZOMBIES,
    CREEPERS,
    SKELETAL,
    ARTHROPODS,
    ILLAGERS,
    ENDER,
    UNKNOWN;

    public static Species getSpecies(LivingEntity entity) {
        return switch (entity) {
            case Creeper            ignored -> CREEPERS;
            case AbstractSkeleton   ignored -> SKELETAL;
            case Zombie             ignored -> ZOMBIES;
            case AbstractPiglin     ignored -> ZOMBIES;
            case AbstractIllager    ignored -> ILLAGERS;
            case Spider             ignored -> ARTHROPODS;
            case Silverfish         ignored -> ARTHROPODS;
            case EnderMan           ignored -> ENDER;
            case Endermite          ignored -> ENDER;
            default -> UNKNOWN;
        };
    }


    public boolean isHostileTowards(LivingEntity target) {
        Species targetSpecies = getSpecies(target);

        // Filter for undefined species
        if (this == UNKNOWN) return false;
        if (targetSpecies == UNKNOWN) return false;

        return this != targetSpecies;
    }
}