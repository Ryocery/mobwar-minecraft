package com.ryocery.mobwar.mobs.profiles;

import com.ryocery.mobwar.mobs.Species;
import net.minecraft.world.entity.Mob;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public interface SpeciesProfile {
    void addCustomGoals(Mob mob, Species species);
    void onAttack(LivingIncomingDamageEvent event);
}