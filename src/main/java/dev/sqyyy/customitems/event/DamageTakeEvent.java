package dev.sqyyy.customitems.event;

import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface DamageTakeEvent {
    public void onEvent(EntityDamageEvent e, EquipmentSlot slot);
}
