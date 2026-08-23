package org.aselstudios.luxdialogues.Dialogues;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialoguesapi.DialogueProvider;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;

public class DialogueSenderAdapter implements DialogueProvider {
   private final DialogueSender dialogueSender = LuxDialogues.getDialogueSender();

   @Override
   public void sendDialogue(Player player, Dialogue dialogue, String pageID) {
      this.dialogueSender.sendDialogue(player, dialogue, pageID);
   }

   @Override
   public void sendDialogue(Player player, String dialogue, String pageID) {
      this.dialogueSender.sendDialogue(player, dialogue, pageID);
   }

   @Override
   public void redirectDialogue(Player player, Dialogue dialogue, String pageID) {
      this.dialogueSender.redirectDialogue(player, dialogue, pageID);
   }

   @Override
   public void redirectDialogue(Player player, String dialogue, String pageID) {
      this.dialogueSender.redirectDialogue(player, dialogue, pageID);
   }

   @Override
   public void clearDialogue(Player player) {
      this.dialogueSender.clearDialogue(player);
   }

   @Override
   public void triggerInteraction(Player player) {
      if (player.isOnline() && DataUtil.getPlayerDialogue(player) != null) {
         LuxDialogues.getDialogueSender().handleClick(player, DataUtil.getPlayerDialogue(player), DataUtil.getPlayerPage(player).getID());
      }
   }

   @Override
   public Boolean isInDialogue(Player player) {
      return DataUtil.getPlayerDialogue(player) != null;
   }

   @Override
   public Boolean isTyping(Player player) {
      return player.isOnline() && DataUtil.getPlayerDialogue(player) != null ? DataUtil.isDialogueTyping(player) : false;
   }

   @Override
   public Boolean isInCommandCooldown(Player player) {
      Long cooldownEnd = DataUtil.commandCooldown.get(player);
      if (cooldownEnd == null) {
         return false;
      } else if (System.currentTimeMillis() > cooldownEnd) {
         DataUtil.commandCooldown.remove(player);
         return false;
      } else {
         return true;
      }
   }

   @Override
   public Boolean getPlayerPageTimer(Player player) {
      return null;
   }

   @Override
   public Dialogue getDialogue(Player player) {
      return DataUtil.getPlayerDialogue(player);
   }
}
