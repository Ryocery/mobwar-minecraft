package com.ryocery.mobwar.handlers;

import com.ryocery.mobwar.MobWar;
import com.ryocery.mobwar.mobs.Species;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = MobWar.MODID)
public class WarHandler {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (mob.level().isClientSide()) return;

        Species mySpecies = Species.getSpecies(mob);
        if (mySpecies == Species.UNKNOWN) return;

        // Endermen prioritize Skeletons first
        if (mob instanceof EnderMan enderman) {
            enderman.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(
                    enderman,
                    AbstractSkeleton.class,
                    10,
                    true,
                    false,
                    null
            ));
        }

        // General condition, attack anything
        mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(
            mob,
            LivingEntity.class,
            10,
            true,
            false,
            (potentialTarget) -> {
                if (potentialTarget instanceof Player) return false;
                return mySpecies.isHostileTowards(potentialTarget);
            }
        ));
    }
}