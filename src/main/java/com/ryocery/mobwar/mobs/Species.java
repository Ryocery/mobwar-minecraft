package com.ryocery.mobwar.mobs;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.breeze.Breeze;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;

public enum Species {
    ZOMBIES,
    SKELETAL,
    CREEPERS,
    ARTHROPODS,
    ILLAGERS,
    ENDER,
    PIGLINS,
    GOLEMS,
    BEASTS,
    WATER_MOBS,
    SLIMES,
    ELEMENTALS,
    WITHER,
    UNKNOWN;

    public static Species getSpecies(LivingEntity entity) {
        return switch (entity) {
            case Zombie           ignored -> ZOMBIES;

            case AbstractSkeleton ignored -> SKELETAL;

            case Creeper          ignored -> CREEPERS;

            case Spider           ignored -> ARTHROPODS;
            case Silverfish       ignored -> ARTHROPODS;
            case Endermite        ignored -> ARTHROPODS;
            case Bee              ignored -> ARTHROPODS;

            case AbstractIllager  ignored -> ILLAGERS;
            case Ravager          ignored -> ILLAGERS;
            case Vex              ignored -> ILLAGERS;
            case Witch            ignored -> ILLAGERS;

            case EnderMan         ignored -> ENDER;
            case Shulker          ignored -> ENDER;

            case AbstractPiglin   ignored -> PIGLINS;

            case IronGolem        ignored -> GOLEMS;
            case SnowGolem        ignored -> GOLEMS;

            case Wolf             ignored -> BEASTS;
            case Fox              ignored -> BEASTS;
            case PolarBear        ignored -> BEASTS;
            case Panda            ignored -> BEASTS;
            case Llama            ignored -> BEASTS;
            case Ocelot           ignored -> BEASTS;

            case Guardian         ignored -> WATER_MOBS;

            case Slime            ignored -> SLIMES;

            case Blaze            ignored -> ELEMENTALS;
            case Ghast            ignored -> ELEMENTALS;
            case Breeze           ignored -> ELEMENTALS;

            case WitherBoss       ignored -> WITHER;

            default -> UNKNOWN;
        };
    }

    public boolean isHostileTowards(LivingEntity target) {
        Species targetSpecies = getSpecies(target);
        if (this == UNKNOWN) return false;
        return this != targetSpecies;
    }
}