package org.aselstudios.luxdialogues.Finishers;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.ExecuteUtil;
import org.aselstudios.luxdialoguesapi.Builders.DialogueCallback;
import org.aselstudios.luxdialoguesapi.Builders.Page;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakFinisher implements Listener {
   @EventHandler
   public void onSneak(PlayerToggleSneakEvent event) {
      Player player = event.getPlayer();
      if (DataUtil.getPlayerDialogue(player) != null) {
         if (DataUtil.getPlayerDialogue(player).getPreventExit() == null || !DataUtil.getPlayerDialogue(player).getPreventExit()) {
            Page page = DataUtil.getPlayerPage(player);
            if (page.getExitActions() != null && !page.getExitActions().isEmpty()) {
               for (String action : page.getExitActions()) {
                  ExecuteUtil.executeAction(player, action);
               }
            }

            if (page.getExitCallbacks() != null && !page.getExitCallbacks().isEmpty()) {
               for (DialogueCallback callback : page.getPreCallbacks()) {
                  callback.execute(player);
               }
            }

            LuxDialogues.getDialogueSender().clearDialogue(player);
         }
      }
   }
}
