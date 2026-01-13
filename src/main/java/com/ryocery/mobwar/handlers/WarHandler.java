package com.ryocery.mobwar.handlers;

import com.ryocery.mobwar.MobWar;
import com.ryocery.mobwar.mobs.profiles.ProfileRegistry;
import com.ryocery.mobwar.mobs.Species;
import com.ryocery.mobwar.mobs.goals.PackSupportGoal;
import net.minecraft.world.entity.Mob;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = MobWar.MODID)
public class WarHandler {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof Mob mob)) return;
        if (mob.level().isClientSide()) return;

        Species species = Species.getSpecies(mob);
        if (species == Species.UNKNOWN) return;
        mob.targetSelector.addGoal(2, new PackSupportGoal(mob, species));
        ProfileRegistry.get(species).addCustomGoals(mob, species);
    }

    @SubscribeEvent
    public static void onDamage(LivingIncomingDamageEvent event) {
        if (event.getEntity().level().isClientSide()) return;

        if (event.getSource().getEntity() instanceof Mob attacker) {
            Species attackerSpecies = Species.getSpecies(attacker);
            Species victimSpecies = Species.getSpecies(event.getEntity());

            if (attackerSpecies == victimSpecies && attackerSpecies != Species.UNKNOWN) {
                event.setCanceled(true);
                return;
            }
            ProfileRegistry.get(attackerSpecies).onAttack(event);
        }
    }
}