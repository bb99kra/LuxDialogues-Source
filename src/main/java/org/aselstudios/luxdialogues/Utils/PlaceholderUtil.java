package org.aselstudios.luxdialogues.Utils;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderUtil extends PlaceholderExpansion {
   @NotNull
   public String getAuthor() {
      return "LuxDialogues";
   }

   @NotNull
   public String getIdentifier() {
      return "luxdialogues";
   }

   @NotNull
   public String getVersion() {
      return LuxDialogues.getInstance().getDescription().getVersion();
   }

   public String onRequest(OfflinePlayer player, String identifier) {
      if (identifier == null) {
         return "Null";
      } else if (identifier.equalsIgnoreCase("status")) {
         if (!player.isOnline()) {
            return "offline";
         } else {
            Player onlinePlayer = player.getPlayer();
            Dialogue dialogue = DataUtil.getPlayerDialogue(onlinePlayer);
            return dialogue == null ? "not_in_dialogue" : "in_dialogue";
         }
      } else if (identifier.equalsIgnoreCase("dialogue")) {
         if (!player.isOnline()) {
            return "Player Offline";
         } else {
            Player onlinePlayer = player.getPlayer();
            Dialogue dialogue = DataUtil.getPlayerDialogue(onlinePlayer);
            return dialogue == null ? "none" : dialogue.getDialogueID();
         }
      } else {
         return "Null";
      }
   }
}
