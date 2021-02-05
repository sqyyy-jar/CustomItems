package dev.sqyyy.customitems;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
    /*@EventHandler
    public void onEvent(PlayerEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInOffHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getBoots() != null)
        if (e.getPlayer().getInventory().getBoots().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getBoots();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                }
            }
        }
        if (e.getPlayer().getInventory().getLeggings() != null)
            if (e.getPlayer().getInventory().getLeggings().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getLeggings();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                    }
                }
            }
        if (e.getPlayer().getInventory().getChestplate() != null)
            if (e.getPlayer().getInventory().getChestplate().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getChestplate();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                    }
                }
            }
        if (e.getPlayer().getInventory().getHelmet() != null)
            if (e.getPlayer().getInventory().getHelmet().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getHelmet();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                    }
                }
            }
    }*/

    @EventHandler
    public void onInteractEvent(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInOffHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getBoots() != null)
        if (e.getPlayer().getInventory().getBoots().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getBoots();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                }
            }
        }
        if (e.getPlayer().getInventory().getLeggings() != null)
            if (e.getPlayer().getInventory().getLeggings().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getLeggings();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                    }
                }
            }
        if (e.getPlayer().getInventory().getChestplate() != null)
            if (e.getPlayer().getInventory().getChestplate().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getChestplate();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                    }
                }
            }
        if (e.getPlayer().getInventory().getHelmet() != null)
            if (e.getPlayer().getInventory().getHelmet().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getHelmet();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getInteractEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getInteractEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                    }
                }
            }
    }

    @EventHandler
    public void onMoveEvent(PlayerMoveEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getItemInOffHand().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getItemInOffHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                }
            }
        }
        if (e.getPlayer().getInventory().getBoots() != null)
        if (e.getPlayer().getInventory().getBoots().getType() != Material.AIR) {
            ItemStack item = e.getPlayer().getInventory().getBoots();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                }
            }
        }
        if (e.getPlayer().getInventory().getLeggings() != null)
            if (e.getPlayer().getInventory().getLeggings().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getLeggings();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                    }
                }
            }
        if (e.getPlayer().getInventory().getChestplate() != null)
            if (e.getPlayer().getInventory().getChestplate().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getChestplate();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                    }
                }
            }
        if (e.getPlayer().getInventory().getHelmet() != null)
            if (e.getPlayer().getInventory().getHelmet().getType() != Material.AIR) {
                ItemStack item = e.getPlayer().getInventory().getHelmet();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getMoveEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getMoveEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                    }
                }
            }
    }

    @EventHandler
    public void onKillEvent(EntityDeathEvent e) {
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();
            if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInMainHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                    }
                }
            }
            if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInOffHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                    }
                }
            }
            if (p.getInventory().getBoots() != null)
            if (p.getInventory().getBoots().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getBoots();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                    }
                }
            }
            if (p.getInventory().getLeggings() != null)
                if (p.getInventory().getLeggings().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getLeggings();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                        }
                    }
                }
            if (p.getInventory().getChestplate() != null)
                if (p.getInventory().getChestplate().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getChestplate();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                        }
                    }
                }
            if (p.getInventory().getHelmet() != null)
                if (p.getInventory().getHelmet().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getHelmet();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getKillEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getKillEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                        }
                    }
                }
        }
    }

    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            ItemStack item = p.getInventory().getItemInMainHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                }
            }
        }
        if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            ItemStack item = p.getInventory().getItemInOffHand();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                }
            }
        }
        if (p.getInventory().getBoots() != null)
        if (p.getInventory().getBoots().getType() != Material.AIR) {
            ItemStack item = p.getInventory().getBoots();
            net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound nbt = nmsItem.getTag();
            if (nbt != null) {
                String key = nbt.getString("key");
                if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                    dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                }
            }
        }
        if (p.getInventory().getLeggings() != null)
            if (p.getInventory().getLeggings().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getLeggings();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                    }
                }
            }
        if (p.getInventory().getChestplate() != null)
            if (p.getInventory().getChestplate().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getChestplate();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                    }
                }
            }
        if (p.getInventory().getHelmet() != null)
            if (p.getInventory().getHelmet().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getHelmet();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDeathEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDeathEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                    }
                }
            }
    }

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player)e.getDamager();
            if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInMainHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                    }
                }
            }
            if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInOffHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                    }
                }
            }
            if (p.getInventory().getBoots() != null)
            if (p.getInventory().getBoots().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getBoots();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                    }
                }
            }
            if (p.getInventory().getLeggings() != null)
                if (p.getInventory().getLeggings().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getLeggings();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                        }
                    }
                }
            if (p.getInventory().getChestplate() != null)
                if (p.getInventory().getChestplate().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getChestplate();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                        }
                    }
                }
            if (p.getInventory().getHelmet() != null)
                if (p.getInventory().getHelmet().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getHelmet();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                        }
                    }
                }
        }
    }

    @EventHandler
    public void onDamageTakeEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInMainHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.HAND);
                    }
                }
            }
            if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getItemInOffHand();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.OFF_HAND);
                    }
                }
            }
            if (p.getInventory().getBoots() != null)
            if (p.getInventory().getBoots().getType() != Material.AIR) {
                ItemStack item = p.getInventory().getBoots();
                net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                NBTTagCompound nbt = nmsItem.getTag();
                if (nbt != null) {
                    String key = nbt.getString("key");
                    if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                        dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.FEET);
                    }
                }
            }
            if (p.getInventory().getLeggings() != null)
                if (p.getInventory().getLeggings().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getLeggings();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.LEGS);
                        }
                    }
                }
            if (p.getInventory().getChestplate() != null)
                if (p.getInventory().getChestplate().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getChestplate();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.CHEST);
                        }
                    }
                }
            if (p.getInventory().getHelmet() != null)
                if (p.getInventory().getHelmet().getType() != Material.AIR) {
                    ItemStack item = p.getInventory().getHelmet();
                    net.minecraft.server.v1_16_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
                    NBTTagCompound nbt = nmsItem.getTag();
                    if (nbt != null) {
                        String key = nbt.getString("key");
                        if (dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().containsKey(key)) {
                            dev.sqyyy.customitems.EventHandler.getDamageTakeEventMap().get(key).onEvent(e, EquipmentSlot.HEAD);
                        }
                    }
                }
        }
    }
}