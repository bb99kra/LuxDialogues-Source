package org.aselstudios.luxdialogues.Dialogues;

import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;

public interface DialogueSender {
   void sendDialogue(Player var1, Dialogue var2, String var3);

   void sendDialogue(Player var1, String var2, String var3);

   void sendFullPage(Player var1, Dialogue var2, String var3);

   void nextPage(Player var1, Dialogue var2, String var3);

   void handleClick(Player var1, Dialogue var2, String var3);

   void redirectDialogue(Player var1, Dialogue var2, String var3);

   void redirectDialogue(Player var1, String var2, String var3);

   void clearDialogue(Player var1);
}
