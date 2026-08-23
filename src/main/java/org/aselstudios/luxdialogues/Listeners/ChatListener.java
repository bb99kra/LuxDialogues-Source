package org.aselstudios.luxdialogues.Listeners;

import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatListener implements Listener {
   @EventHandler(
      priority = EventPriority.HIGHEST,
      ignoreCancelled = false
   )
   public void onPlayerChat(AsyncPlayerChatEvent event) {
      Player sender = event.getPlayer();
      if (DataUtil.getPlayerDialogue(sender) != null) {
         event.setCancelled(true);
      }
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onPlayerChatReceive(AsyncPlayerChatEvent event) {
      event.getRecipients().removeIf(recipient -> recipient instanceof Player && DataUtil.getPlayerDialogue(recipient) != null);
   }

   @EventHandler(
      priority = EventPriority.HIGHEST
   )
   public void onCommandSend(PlayerCommandPreprocessEvent event) {
      Player player = event.getPlayer();
      if (DataUtil.getPlayerDialogue(player) != null) {
         String message = event.getMessage().toLowerCase();
         if (message.startsWith("/msg") || message.startsWith("/tell") || message.startsWith("/say") || message.startsWith("/me")) {
            event.setCancelled(true);
         }
      }
   }
}
