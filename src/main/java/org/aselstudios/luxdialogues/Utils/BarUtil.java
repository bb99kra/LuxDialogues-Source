package org.aselstudios.luxdialogues.Utils;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.chat.ChatTypes;
import com.github.retrooper.packetevents.protocol.chat.message.ChatMessageLegacy;
import com.github.retrooper.packetevents.protocol.player.ClientVersion;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerActionBar;
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerChatMessage;
import dev.aurelium.auraskills.api.AuraSkillsProvider;
import io.github.retrooper.packetevents.util.viaversion.ViaVersionUtil;
import java.util.concurrent.TimeUnit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BarUtil {
   public static void sendActionBar(Player player, String text, int duration) {
      Component parsed = MiniMessage.miniMessage().deserialize(text);
      ClientVersion version = PacketEvents.getAPI().getPlayerManager().getClientVersion(player);
      if (AdventureUtil.SHADOW_SUPPORTED && ViaVersionUtil.getViaVersionAccessor() != null && ViaVersionUtil.getProtocolVersion(player) > 769) {
         parsed = AdventureUtil.applyShadowColor(MiniMessage.miniMessage().deserialize(text), 0, 0, 0, 0);
      } else if (AdventureUtil.SHADOW_SUPPORTED && version.getProtocolVersion() > 769) {
         parsed = AdventureUtil.applyShadowColor(MiniMessage.miniMessage().deserialize(text), 0, 0, 0, 0);
      }

      if (YamlUtil.get("config.yml").getBoolean("Hooks.AuraSkills")) {
         AuraSkillsProvider.getInstance().getUser(player.getUniqueId()).pauseActionBar(duration * 50, TimeUnit.MILLISECONDS);
      }

      if (Bukkit.getVersion().toLowerCase().contains("arclight")) {
         AdventureUtil.playerActionbar(player, text);
      } else if (!Bukkit.getVersion().contains("1.17")
         && !Bukkit.getVersion().contains("1.18")
         && !Bukkit.getVersion().contains("1.19")
         && !Bukkit.getVersion().contains("1.20")
         && !Bukkit.getVersion().contains("1.21")) {
         ChatMessageLegacy textLegacy = new ChatMessageLegacy(parsed, ChatTypes.GAME_INFO);
         WrapperPlayServerChatMessage packet = new WrapperPlayServerChatMessage(textLegacy);
         PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);
      } else {
         WrapperPlayServerActionBar packet = new WrapperPlayServerActionBar(parsed);
         PacketEvents.getAPI().getPlayerManager().sendPacket(player, packet);
      }
   }
}
