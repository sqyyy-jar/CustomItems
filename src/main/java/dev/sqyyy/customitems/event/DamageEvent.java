package dev.sqyyy.customitems.event;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface DamageEvent {
    public void onEvent(EntityDamageByEntityEvent e, EquipmentSlot slot);
}
