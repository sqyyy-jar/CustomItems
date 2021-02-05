package dev.sqyyy.customitems;

import dev.sqyyy.customitems.event.*;

import java.util.HashMap;
import java.util.Map;

public class EventHandler {
    /*public static Map<String, Event> getEventMap() {
        return eventMap;
    }*/
    public static Map<String, InteractEvent> getInteractEventMap() {
        return interactEventMap;
    }
    public static Map<String, DamageEvent> getDamageEventMap() {
        return damageEventMap;
    }
    public static Map<String, DamageTakeEvent> getDamageTakeEventMap() {
        return damageTakeEventMap;
    }
    public static Map<String, DeathEvent> getDeathEventMap() {
        return deathEventMap;
    }
    public static Map<String, KillEvent> getKillEventMap() {
        return killEventMap;
    }
    public static Map<String, MoveEvent> getMoveEventMap() {
        return moveEventMap;
    }

    //private static final Map<String, Event> eventMap = new HashMap<>();
    private static final Map<String, InteractEvent> interactEventMap = new HashMap<>();
    private static final Map<String, DamageEvent> damageEventMap = new HashMap<>();
    private static final Map<String, DamageTakeEvent> damageTakeEventMap = new HashMap<>();
    private static final Map<String, DeathEvent> deathEventMap = new HashMap<>();
    private static final Map<String, KillEvent> killEventMap = new HashMap<>();
    private static final Map<String, MoveEvent> moveEventMap = new HashMap<>();

    /*public static void setEvent(String key, Event event) {
        eventMap.put(key, event);
    }*/
    public static void setInteractEvent(String key, InteractEvent event) {
        interactEventMap.put(key, event);
    }
    public static void setDamageEvent(String key, DamageEvent event) {
        damageEventMap.put(key, event);
    }
    public static void setDamageTakeEvent(String key, DamageTakeEvent event) {
        damageTakeEventMap.put(key, event);
    }
    public static void setDeathEvent(String key, DeathEvent event) {
        deathEventMap.put(key, event);
    }
    public static void setKillEvent(String key, KillEvent event) {
        killEventMap.put(key, event);
    }
    public static void setMoveEvent(String key, MoveEvent event) {
        moveEventMap.put(key, event);
    }
}
