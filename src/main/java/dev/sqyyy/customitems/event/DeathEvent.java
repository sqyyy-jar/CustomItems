package dev.sqyyy.customitems.event;

import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface DeathEvent {
    public void onEvent(PlayerDeathEvent e, EquipmentSlot slot);
}
