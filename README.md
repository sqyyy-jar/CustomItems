# CustomItems
Spigot Plugin to create 1.16+ Custom items with Json and Java

Examples:

Json:
```
{
  "DisplayName": "The displayname of the item", (use &char to colorize the name)
  "Material": "material of the item", (look up here to find the right materials: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html)
  "CustomModelData": 1, (set the items CustomModelData to this value - not necessary)
  "Unbreakable": true|false, (makes the item unbreakable - not necessary)
  "ItemFlags": ["itemflag1", "itemflag2", etc], (adds itemflags from https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/ItemFlag.html to the item - not necessary)
  "Lore": ["line1", "line2", etc], (adds a lore to the item - not necessary - use &char to colorize the lore)
  "Recipe": {
    "shape": [" R ", " R ", " S "], (The shape stands for the 3x3 grid in the craftingtable)
    "Items": [{"char":'R', "Material": "ROTTEN_FLESH"}, {"char":'S', "Material": "STICK"}] //replace the chars used before with the Materials from https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Material.html
  }, //not necessary
  "Attributes": [
    {
      "id": "GENERIC_MAX_HEALTH", (Attribute from https://hub.spigotmc.org/javadocs/spigot/org/bukkit/attribute/Attribute.html)
      "name": "generic.maxHealth", (The minecraftname of the attribute)
      "value": 6.5, (The value/amount - here the amount of max health added by the item)
      "slots": ["HAND"] (The slots the item is for from https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/EquipmentSlot.html - not necessary(default - all slots))
    }
  ], (all attributes - not necessary)
  "AllowBuild": true|false, (Allows or denies to place the item if it is a block - not necessary)
  "texture": "texture Base64 String" (Changes the skin of a playerhead to this value - not necessary - use skingenerators like mineskin.org or from https://sessionserver.mojang.com/session/minecraft/profile/ + uuid
     + ?unsigned=false and get the texture)
}
```

Java:
```java
dev.sqyyy.customitems.EventHandler.setEvent(String itemfilename, new dev.sqyyy.customitems.event.Event() { //use as Event any event in the given package
  @Override
  public void onEvent(Player_____Event e, EquipmentSlot slot) {
    //do something...
  }
}); //the code will execute if you execute the used event
```
