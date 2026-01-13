package com.ryocery.mobwar.mobs;

import com.ryocery.mobwar.MobWar;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.EnderMan;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;

@EventBusSubscriber(modid = MobWar.MODID)
public class EnderManMW {
    @SubscribeEvent
    public static void onDamage(LivingDamageEvent.Pre event) {
        if (!(event.getSource().getEntity() instanceof EnderMan)) return;

        if (event.getEntity() instanceof AbstractSkeleton) {
            float originalDamage = event.getOriginalDamage();
            event.setNewDamage(originalDamage * 3.0f);
        }
    }
}
