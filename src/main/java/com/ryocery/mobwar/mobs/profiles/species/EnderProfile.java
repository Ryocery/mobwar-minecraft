package com.ryocery.mobwar.mobs.profiles.species;

import com.ryocery.mobwar.mobs.Species;
import com.ryocery.mobwar.mobs.profiles.DefaultProfile;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.EnderMan;

public class EnderProfile extends DefaultProfile {
    @Override
    public void addCustomGoals(Mob mob, Species species) {
        super.addCustomGoals(mob, species);

        if (mob instanceof EnderMan enderman) {
            enderman.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    enderman,
                    AbstractSkeleton.class,
                    10, true, false, null
            ));
        }
    }
}