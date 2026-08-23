package org.aselstudios.luxdialogues.Listeners;

import java.time.Duration;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.ForkUtil;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import space.arim.morepaperlib.scheduling.ScheduledTask;

public class RangeListener {
   public static void startRangeChecker(Location startLoc, Player player, Dialogue dialogue) {
      if (ForkUtil.isUsingFolia()) {
         foliaRange(startLoc, player, dialogue);
      } else {
         spigotRange(startLoc, player, dialogue);
      }
   }

   public static void foliaRange(Location startLoc, Player player, Dialogue dialogue) {
      Double allowedRange = dialogue.getRange();
      if (!(allowedRange < 0.0)) {
         ScheduledTask taskID = LuxDialogues.getMorePaperLib().scheduling().asyncScheduler().runAtFixedRate(() -> {
            Location playerLoc = player.getLocation();
            if (playerLoc.distance(startLoc) > allowedRange || startLoc.getWorld() != playerLoc.getWorld()) {
               LuxDialogues.getDialogueSender().clearDialogue(player);
            }
         }, Duration.ZERO, Duration.ofMillis(25L));
         LuxDialogues.getTaskData().setRangeTask(player, taskID);
      }
   }

   public static void spigotRange(Location startLoc, Player player, Dialogue dialogue) {
      Double allowedRange = dialogue.getRange();
      if (!(allowedRange < 0.0)) {
         int taskID = Bukkit.getScheduler().runTaskTimerAsynchronously(LuxDialogues.getInstance(), () -> {
            Location playerLoc = player.getLocation();
            if (startLoc.getWorld() != playerLoc.getWorld()) {
               LuxDialogues.getDialogueSender().clearDialogue(player);
            } else {
               if (playerLoc.distance(startLoc) > allowedRange) {
                  LuxDialogues.getDialogueSender().clearDialogue(player);
               }
            }
         }, 0L, 5L).getTaskId();
         LuxDialogues.getTaskData().setRangeTask(player, taskID);
      }
   }
}
