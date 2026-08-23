package org.aselstudios.luxdialogues.Interactions;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Client;
import com.github.retrooper.packetevents.protocol.player.InteractionHand;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientInteractEntity.InteractAction;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class LeftClickPlayerListener implements PacketListener {
   public void onPacketReceive(PacketReceiveEvent event) {
      if (event.getPacketType() == Client.INTERACT_ENTITY) {
         WrapperPlayClientInteractEntity packet = new WrapperPlayClientInteractEntity(event);
         if (packet.getAction() == InteractAction.ATTACK) {
            Player player = (Player)event.getPlayer();
            if (packet.getHand() == InteractionHand.MAIN_HAND) {
               if (DataUtil.getPlayerDialogue(player) != null) {
                  if (DataUtil.commandPrevent.containsKey(player)
                     && DataUtil.commandPrevent.get(player).equals(DataUtil.getPlayerDialogue(player).getDialogueID())) {
                     DataUtil.commandPrevent.remove(player);
                  } else {
                     Entity entity = SpigotReflectionUtil.getEntityById(packet.getEntityId());
                     if (entity != null) {
                        if (entity instanceof Player) {
                           LuxDialogues.getDialogueSender().handleClick(player, DataUtil.getPlayerDialogue(player), DataUtil.getPlayerPage(player).getID());
                           event.setCancelled(true);
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
