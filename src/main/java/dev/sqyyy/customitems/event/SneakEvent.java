package dev.sqyyy.customitems.event;

import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface SneakEvent {
    public void onEvent(PlayerToggleSneakEvent e, EquipmentSlot slot);
}
