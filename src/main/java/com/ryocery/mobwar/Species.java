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
            case Creeper c -> CREEPERS;
            case AbstractSkeleton s -> SKELETAL;
            case Zombie z -> ZOMBIES;
            case AbstractPiglin p -> ZOMBIES;
            case AbstractIllager i -> ILLAGERS;
            case Spider sp -> ARTHROPODS;
            case Silverfish sf -> ARTHROPODS;
            case EnderMan e -> ENDER;
            case Endermite em -> ENDER;
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