package org.aselstudios.luxdialogues.Interactions;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class SwapHandListener implements Listener {
   @EventHandler(
      priority = EventPriority.HIGH,
      ignoreCancelled = true
   )
   public void onPlayerSwapHand(PlayerSwapHandItemsEvent event) {
      Player player = event.getPlayer();
      if (DataUtil.getPlayerDialogue(player) != null) {
         LuxDialogues.getDialogueSender().handleClick(player, DataUtil.getPlayerDialogue(player), DataUtil.getPlayerPage(player).getID());
         event.setCancelled(true);
      }
   }
}
