package org.aselstudios.luxdialogues.Interactions;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class LeftClickBlockListener implements Listener {
   @EventHandler(
      priority = EventPriority.HIGH
   )
   public void onPlayerInteract(PlayerInteractEvent event) {
      Player player = event.getPlayer();
      if (event.getHand() == EquipmentSlot.HAND) {
         if (event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            if (DataUtil.getPlayerDialogue(player) != null) {
               LuxDialogues.getDialogueSender().handleClick(player, DataUtil.getPlayerDialogue(player), DataUtil.getPlayerPage(player).getID());
            }
         }
      }
   }
}
