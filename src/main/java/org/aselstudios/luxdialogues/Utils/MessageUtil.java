package org.aselstudios.luxdialogues.Utils;

import org.bukkit.Bukkit;

public class MessageUtil {
   public static boolean isMessageEnabled(String messageText) {
      if (messageText.trim().length() == 0) {
         return false;
      } else {
         return messageText == null ? false : messageText != "";
      }
   }

   public static void sendConsole(String messageText) {
      Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText(messageText));
   }
}
