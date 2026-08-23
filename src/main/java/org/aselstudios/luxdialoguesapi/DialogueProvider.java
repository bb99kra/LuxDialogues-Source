package org.aselstudios.luxdialoguesapi;

import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;

public interface DialogueProvider {
   void sendDialogue(Player var1, Dialogue var2, String var3);

   void sendDialogue(Player var1, String var2, String var3);

   void redirectDialogue(Player var1, Dialogue var2, String var3);

   void redirectDialogue(Player var1, String var2, String var3);

   void clearDialogue(Player var1);

   void triggerInteraction(Player var1);

   Boolean isInDialogue(Player var1);

   Boolean isTyping(Player var1);

   Boolean isInCommandCooldown(Player var1);

   Boolean getPlayerPageTimer(Player var1);

   Dialogue getDialogue(Player var1);
}
