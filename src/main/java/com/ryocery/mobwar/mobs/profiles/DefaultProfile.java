package com.ryocery.mobwar.mobs.profiles;

import com.ryocery.mobwar.mobs.Species;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

public class DefaultProfile implements SpeciesProfile {

    @Override
    public void addCustomGoals(Mob mob, Species species) {
        mob.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(
                mob,
                LivingEntity.class,
                10, true, false,
                (target) -> {
                    if (target instanceof Player) return false;
                    return species.isHostileTowards(target);
                }
        ));
    }

    @Override
    public void onAttack(LivingIncomingDamageEvent event) {
        if (event.getEntity() instanceof AbstractSkeleton) {
            event.setAmount(event.getAmount() * 3.0f);
        }
    }
}