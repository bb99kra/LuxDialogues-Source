package org.aselstudios.luxdialogues.Listeners;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketSendEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType.Play.Server;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;

public class ChatPacket implements PacketListener {
   public void onPacketSend(PacketSendEvent event) {
      if (event.getPacketType() == Server.CHAT_MESSAGE || event.getPacketType() == Server.DISGUISED_CHAT || event.getPacketType() == Server.SYSTEM_CHAT_MESSAGE
         )
       {
         Player recipient = (Player)event.getPlayer();
         if (DataUtil.getPlayerDialogue(recipient) != null) {
            event.setCancelled(true);
         }
      }
   }
}
