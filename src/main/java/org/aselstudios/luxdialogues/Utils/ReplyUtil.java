package org.aselstudios.luxdialogues.Utils;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReplyUtil {
   public static void sendReply(Player player, String reply) {
      if (ForkUtil.isUsingFolia()) {
         foliaReplier(player, reply);
      } else {
         spigotReplier(player, reply);
      }
   }

   private static void foliaReplier(Player player, String reply) {
      int delay = 0;
      if (reply.contains("@delay.")) {
         String[] parts = reply.split("@delay\\.");
         if (parts.length > 1) {
            try {
               String delayPart = parts[1].split(" ")[0];
               delay = Integer.parseInt(delayPart) * 20;
               reply = parts[0].trim();
            } catch (NumberFormatException var5) {
               Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("&4LuxDialogues &7- &cYour dialog files have invalid delay time, please check."));
               return;
            }
         }
      }

      String finalReply = reply;
      if (delay <= 0) {
         LuxDialogues.getMorePaperLib().scheduling().globalRegionalScheduler().run(task -> player.sendMessage(ColorUtil.colorText(finalReply)));
      } else {
         LuxDialogues.getMorePaperLib().scheduling().globalRegionalScheduler().runDelayed(task -> player.sendMessage(ColorUtil.colorText(finalReply)), delay);
      }
   }

   private static void spigotReplier(Player player, String reply) {
      int delay = 0;
      if (reply.contains("@delay.")) {
         String[] parts = reply.split("@delay\\.");
         if (parts.length > 1) {
            try {
               String delayPart = parts[1].split(" ")[0];
               delay = Integer.parseInt(delayPart) * 20;
               reply = parts[0].trim();
            } catch (NumberFormatException var5) {
               Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("&4LuxDialogues &7- &cYour dialog files have invalid delay time, please check."));
               return;
            }
         }
      }

      String finalReply = reply;
      if (delay <= 0) {
         Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> player.sendMessage(ColorUtil.colorText(finalReply)));
      } else {
         Bukkit.getScheduler().runTaskLater(LuxDialogues.getInstance(), () -> player.sendMessage(ColorUtil.colorText(finalReply)), delay);
      }
   }
}
