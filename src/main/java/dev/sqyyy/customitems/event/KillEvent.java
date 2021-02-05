package dev.sqyyy.customitems.event;

import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface KillEvent {
    public void onEvent(EntityDeathEvent e, EquipmentSlot slot);
}
