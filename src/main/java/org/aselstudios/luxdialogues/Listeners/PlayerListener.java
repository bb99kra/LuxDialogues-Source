package org.aselstudios.luxdialogues.Listeners;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {
   @EventHandler
   public void onQuit(PlayerQuitEvent event) {
      Player player = event.getPlayer();
      if (player.getWalkSpeed() == 0.0F) {
         player.setWalkSpeed(0.2F);
      }

      player.removePotionEffect(PotionEffectType.SLOWNESS);
      player.removePotionEffect(PotionEffectType.JUMP_BOOST);
      if (DataUtil.getPlayerDialogue(player) != null) {
         LuxDialogues.getDialogueSender().clearDialogue(player);
      }
   }

   @EventHandler
   public void onDeath(PlayerDeathEvent event) {
      Player player = event.getEntity();
      if (player.getWalkSpeed() == 0.0F) {
         player.setWalkSpeed(0.2F);
      }

      player.removePotionEffect(PotionEffectType.SLOWNESS);
      player.removePotionEffect(PotionEffectType.JUMP_BOOST);
      if (DataUtil.getPlayerDialogue(player) != null) {
         LuxDialogues.getDialogueSender().clearDialogue(player);
      }
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      if (player.getWalkSpeed() == 0.0F) {
         player.setWalkSpeed(0.2F);
      }

      player.removePotionEffect(PotionEffectType.SLOWNESS);
      player.removePotionEffect(PotionEffectType.JUMP_BOOST);
      if (DataUtil.getPlayerDialogue(player) != null) {
         LuxDialogues.getDialogueSender().clearDialogue(player);
      }
   }
}
