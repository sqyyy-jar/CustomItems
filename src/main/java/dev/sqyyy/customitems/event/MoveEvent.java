package dev.sqyyy.customitems.event;

import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface MoveEvent {
    public void onEvent(PlayerMoveEvent e, EquipmentSlot slot);
}
