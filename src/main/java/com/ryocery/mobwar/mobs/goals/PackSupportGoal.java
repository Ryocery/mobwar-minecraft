package com.ryocery.mobwar.mobs.goals;

import com.ryocery.mobwar.mobs.Species;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;

import java.util.List;

public class PackSupportGoal extends TargetGoal {
    private final Species mySpecies;

    public PackSupportGoal(Mob mob, Species species) {
        super(mob, false);
        this.mySpecies = species;
    }

    @Override
    public boolean canUse() {
        if (this.mob.getTarget() != null) return false;

        List<Mob> nearbyFriends = this.mob.level().getEntitiesOfClass(
                Mob.class,
                this.mob.getBoundingBox().inflate(15.0D),
                (friend) -> Species.getSpecies(friend) == mySpecies && friend != this.mob
        );

        for (Mob friend : nearbyFriends) {
            LivingEntity enemy = friend.getTarget();
            if (enemy != null && enemy.isAlive()) {
                if (mySpecies.isHostileTowards(enemy)) {
                    this.targetMob = enemy;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void start() {
        this.mob.setTarget(this.targetMob);
        super.start();
    }
}