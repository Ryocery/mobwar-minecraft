package com.ryocery.mobwar.mobs.profiles.species;

import com.ryocery.mobwar.mobs.Species;
import com.ryocery.mobwar.mobs.profiles.DefaultProfile;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.raid.Raider;

public class IllagerProfile extends DefaultProfile {
    @Override
    public void addCustomGoals(Mob mob, Species species) {
        super.addCustomGoals(mob, species);

        if (mob instanceof Raider) {
            mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    mob,
                    Villager.class,
                    10, true, false, null
            ));

            mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    mob,
                    IronGolem.class,
                    10, true, false, null
            ));
        }
    }
}