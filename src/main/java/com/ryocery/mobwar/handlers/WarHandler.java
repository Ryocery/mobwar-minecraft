package com.ryocery.mobwar.handlers;

import com.ryocery.mobwar.Mobwar;
import com.ryocery.mobwar.Species;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@EventBusSubscriber(modid = Mobwar.MODID)
public class WarHandler {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (mob.level().isClientSide()) return;

        Species mySpecies = Species.getSpecies(mob);
        if (mySpecies == Species.UNKNOWN) return;

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