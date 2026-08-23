package org.aselstudios.luxdialogues.Utils;

import com.github.retrooper.packetevents.event.PacketListenerCommon;
import java.util.HashMap;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.aselstudios.luxdialoguesapi.Builders.Page;
import org.bukkit.entity.Player;

public class DataUtil {
   public static HashMap<String, PacketListenerCommon> packetListeners = new HashMap<>();
   public static final HashMap<Player, Dialogue> playerDialogues = new HashMap<>();
   public static final HashMap<Player, Page> playerPage = new HashMap<>();
   public static final HashMap<String, Dialogue> loadedDialogues = new HashMap<>();
   private static final HashMap<Player, Boolean> dialogueStatus = new HashMap<>();
   private static final HashMap<Player, Integer> playerAnswer = new HashMap<>();
   private static HashMap<Player, Long> playerPageTimer = new HashMap<>();
   public static HashMap<Player, String> commandPrevent = new HashMap<>();
   public static HashMap<Player, Long> commandCooldown = new HashMap<>();
   public static HashMap<Player, Boolean> scrollCooldown = new HashMap<>();
   public static String packNamespace = "luxdialogues";

   public static void setPlayerDialogue(Player player, Dialogue dialogue, Page page) {
      playerDialogues.put(player, dialogue);
      playerPage.put(player, page);
   }

   public static void setDialogueStatus(Player player, boolean statusBoolean) {
      dialogueStatus.put(player, statusBoolean);
   }

   public static void setPlayerAnswer(Player player, int answerNumber) {
      playerAnswer.put(player, answerNumber);
   }

   public static void setDialoguePage(Player player, String page) {
      if (playerDialogues.containsKey(player) && playerDialogues.get(player) != null) {
         playerPage.put(player, playerDialogues.get(player).getPages().get(page));
      } else {
         playerPage.put(player, null);
      }
   }

   public static void setPlayerPageTimer(Player player, long time) {
      playerPageTimer.put(player, time);
   }

   public static long getPlayerPageTimer(Player player) {
      return playerPageTimer.get(player);
   }

   public static boolean isDialogueTyping(Player player) {
      return dialogueStatus.getOrDefault(player, false);
   }

   public static Dialogue getPlayerDialogue(Player player) {
      return playerDialogues.get(player);
   }

   public static Page getPlayerPage(Player player) {
      return playerPage.getOrDefault(player, null);
   }

   public static Integer getPlayerAnswer(Player player) {
      return playerAnswer.getOrDefault(player, 1);
   }

   public static Dialogue getDialogue(String dialogueID) {
      return loadedDialogues.get(dialogueID);
   }
}
