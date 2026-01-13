package com.ryocery.mobwar.mobs.profiles.species;

import com.ryocery.mobwar.mobs.Species;
import com.ryocery.mobwar.mobs.profiles.DefaultProfile;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.monster.AbstractSkeleton;

public class BeastProfile extends DefaultProfile {
    @Override
    public void addCustomGoals(Mob mob, Species species) {
        super.addCustomGoals(mob, species);

        if (mob instanceof Wolf wolf) {
            wolf.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    wolf,
                    AbstractSkeleton.class,
                    10, true, false, null
            ));
            wolf.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    wolf,
                    Sheep.class,
                    10, true, false, null
            ));
        }

        if (mob instanceof Fox fox) {
            fox.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    fox,
                    Chicken.class,
                    10, true, false, null
            ));
            fox.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    fox,
                    Rabbit.class,
                    10, true, false, null
            ));
        }

        if (mob instanceof Ocelot || mob instanceof Cat) {
            mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
                    mob,
                    net.minecraft.world.entity.monster.Creeper.class,
                    10, true, false, null
            ));
        }
    }
}