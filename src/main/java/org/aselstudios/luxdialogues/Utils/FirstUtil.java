package org.aselstudios.luxdialogues.Utils;

import org.bukkit.configuration.file.FileConfiguration;

public class FirstUtil {
   public static Boolean isFirstInstall() {
      FileConfiguration configFile = YamlUtil.get("config.yml");
      return configFile.contains("First-Install") && configFile.getBoolean("First-Install");
   }

   public static void firstInstall() {
      FileConfiguration configFile = YamlUtil.get("config.yml");
      if (isFirstInstall()) {
         configFile.set("First-Install", false);
         ResourceUtil.saveResource("Pack/Images/answer.png", "Pack/Images/answer.png", false);
         ResourceUtil.saveResource("Pack/Images/character.png", "Pack/Images/character.png", false);
         ResourceUtil.saveResource("Pack/Images/dialogue.png", "Pack/Images/dialogue.png", false);
         ResourceUtil.saveResource("Pack/Images/fog.png", "Pack/Images/fog.png", false);
         ResourceUtil.saveResource("Pack/Images/hand.png", "Pack/Images/hand.png", false);
         ResourceUtil.saveResource("Pack/Images/name_end.png", "Pack/Images/name_end.png", false);
         ResourceUtil.saveResource("Pack/Images/name_mid.png", "Pack/Images/name_mid.png", false);
         ResourceUtil.saveResource("Pack/Images/name_start.png", "Pack/Images/name_start.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_answer.png", "Pack/Images/kingdom_answer.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_character.png", "Pack/Images/kingdom_character.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_dialogue.png", "Pack/Images/kingdom_dialogue.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_hand.png", "Pack/Images/kingdom_hand.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_name_end.png", "Pack/Images/kingdom_name_end.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_name_mid.png", "Pack/Images/kingdom_name_mid.png", false);
         ResourceUtil.saveResource("Pack/Images/kingdom_name_start.png", "Pack/Images/kingdom_name_start.png", false);
         ResourceUtil.saveResource("Pack/Sounds/beep.ogg", "Pack/Sounds/beep.ogg", false);
         ResourceUtil.saveResource("Pack/Sounds/ding.ogg", "Pack/Sounds/ding.ogg", false);
         ResourceUtil.saveResource("Pack/Sounds/selection.ogg", "Pack/Sounds/selection.ogg", false);
         ResourceUtil.saveResource("Pack/Sounds/typing.ogg", "Pack/Sounds/typing.ogg", false);
         ResourceUtil.saveResource("Pack/Sounds/kingdom_typing.ogg", "Pack/Sounds/kingdom_typing.ogg", false);
         ResourceUtil.saveResource("Dialogues/default_example.yml", "Dialogues/default_example.yml", false);
         ResourceUtil.saveResource("Dialogues/kingdom_example.yml", "Dialogues/kingdom_example.yml", false);
      }
   }
}
