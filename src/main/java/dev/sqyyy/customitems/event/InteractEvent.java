package dev.sqyyy.customitems.event;

import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface InteractEvent {
    public void onEvent(PlayerInteractEvent e, EquipmentSlot slot);
}
