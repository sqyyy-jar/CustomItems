package dev.sqyyy.customitems.event;

import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;

public interface Event {
    public void onEvent(PlayerEvent e, EquipmentSlot slot);
}
