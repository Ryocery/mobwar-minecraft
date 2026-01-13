package com.ryocery.mobwar.handlers;

import com.ryocery.mobwar.MobWar;
import com.ryocery.mobwar.mobs.Species;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEvent;

import java.util.List;

@EventBusSubscriber(modid = MobWar.MODID)
public class HordeHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (mob.level().isClientSide()) return;

        if ((mob.tickCount + mob.getId()) % 100 != 0) return;

        Species species = Species.getSpecies(mob);
        if (species == Species.UNKNOWN) return;

        List<Mob> allies = mob.level().getEntitiesOfClass(
                Mob.class,
                mob.getBoundingBox().inflate(15.0D),
                (friend) -> Species.getSpecies(friend) == species && friend != mob
        );

        int groupSize = allies.size() + 1;
        if (groupSize < 3) return;

        applyHordeBuffs(mob, groupSize);
    }

    private static void applyHordeBuffs(Mob mob, int groupSize) {
        int duration = 120; // 6 seconds

        record BuffAmplifiers(int strength, int regeneration, int speed, int resistance) {}

        BuffAmplifiers amps = switch ((Integer) groupSize) {
            case Integer n when n >= 13 -> new BuffAmplifiers(1, 1, 1, 1);
            case Integer n when n >= 11 -> new BuffAmplifiers(1, 0, 0, 1);
            case Integer n when n >= 9  -> new BuffAmplifiers(1, 0, 0, -1);
            case Integer n when n >= 7  -> new BuffAmplifiers(0, 0, 0, -1);
            case Integer n when n >= 5  -> new BuffAmplifiers(0, 0, -1, -1);
            case Integer n when n >= 3  -> new BuffAmplifiers(0, -1, -1, -1);
            default                     -> new BuffAmplifiers(-1, -1, -1, -1);
        };

        if (amps.strength() > -1)     mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, amps.strength()));
        if (amps.regeneration() > -1) mob.addEffect(new MobEffectInstance(MobEffects.REGENERATION, duration, amps.regeneration()));
        if (amps.speed() > -1)        mob.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, duration, amps.speed()));
        if (amps.resistance() > -1)   mob.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, duration, amps.resistance()));
    }

}