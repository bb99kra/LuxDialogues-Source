package org.aselstudios.luxdialogues.Listeners;

import net.Indyuce.mmocore.api.event.PlayerEnterCastingModeEvent;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MMOCoreListener implements Listener {
   @EventHandler
   public void onEnterCastingMode(PlayerEnterCastingModeEvent event) {
      Player player = event.getPlayer();
      if (DataUtil.getPlayerDialogue(player) != null) {
         event.setCancelled(true);
      }
   }
}
