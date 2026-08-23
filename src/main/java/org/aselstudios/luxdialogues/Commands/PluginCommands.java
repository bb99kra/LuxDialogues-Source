package org.aselstudios.luxdialogues.Commands;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerCommon;
import java.util.Arrays;
import java.util.Locale;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.ColorUtil;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.LoaderUtil;
import org.aselstudios.luxdialogues.Utils.MessageUtil;
import org.aselstudios.luxdialogues.Utils.ResourceUtil;
import org.aselstudios.luxdialogues.Utils.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PluginCommands implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if (!Arrays.asList("luxdialogues", "ld", "luxd").contains(command.getName().toLowerCase(Locale.ROOT))) {
         return true;
      } else if (args.length == 0) {
         unknownCommand(sender);
         return true;
      } else if (args[0].equalsIgnoreCase("about") && args.length == 1) {
         sender.sendMessage(ColorUtil.colorText("&7&m-----------------------------------"));
         sender.sendMessage(ColorUtil.colorText("#FFA500             LuxDialogues"));
         sender.sendMessage(ColorUtil.colorText("#7289DADiscord: &7&ohttps://discord.aselstudios.com"));
         sender.sendMessage(ColorUtil.colorText("&7&m-----------------------------------"));
         return true;
      } else if (args[0].equalsIgnoreCase("reload") && args.length == 1) {
         if (!sender.hasPermission("luxdialogues.reload") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else {
            reloadCommand(sender);
            return true;
         }
      } else if (args[0].equalsIgnoreCase("create-pack") && args.length == 1) {
         if (!sender.hasPermission("luxdialogues.create-pack") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else {
            createResourceCommand(sender);
            return true;
         }
      } else if (args[0].equalsIgnoreCase("start") && args.length == 3) {
         if (!sender.hasPermission("luxdialogues.start") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else if (args[1].isEmpty()) {
            unknownCommand(sender);
            return true;
         } else if (!DataUtil.loadedDialogues.containsKey(args[1])) {
            dialogueError(sender, args[1]);
            return true;
         } else if (DataUtil.loadedDialogues.get(args[1]) != null && DataUtil.loadedDialogues.get(args[1]).getPages().get(args[2]) == null) {
            dialogueError(sender, args[1]);
            return true;
         } else if (!(sender instanceof Player)) {
            consoleError(sender);
            return true;
         } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) != null) {
            String alreadyERR = LuxDialogues.getLang().getString("Messages.already-error");
            if (MessageUtil.isMessageEnabled(alreadyERR)) {
               Bukkit.getPlayer(args[1]).sendMessage(ColorUtil.colorText(alreadyERR));
            }

            return true;
         } else if (!canStartDialogue((Player)sender)) {
            return true;
         } else {
            String startMSG = LuxDialogues.getLang().getString("Messages.dialogue-started").replace("{DialogueName}", args[1]);
            if (MessageUtil.isMessageEnabled(startMSG)) {
               sender.sendMessage(ColorUtil.colorText(startMSG));
            }

            LuxDialogues.getDialogueSender().sendDialogue((Player)sender, DataUtil.getDialogue(args[1]), args[2]);
            return true;
         }
      } else if (args[0].equalsIgnoreCase("sendall") && args.length == 3) {
         if (!sender.hasPermission("luxdialogues.sendall") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else if (args[1].isEmpty()) {
            unknownCommand(sender);
            return true;
         } else if (!DataUtil.loadedDialogues.containsKey(args[1])) {
            dialogueError(sender, args[1]);
            return true;
         } else if (DataUtil.loadedDialogues.get(args[1]) != null && DataUtil.loadedDialogues.get(args[1]).getPages().get(args[2]) == null) {
            dialogueError(sender, args[1]);
            return true;
         } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) != null) {
            String alreadyERR = LuxDialogues.getLang().getString("Messages.already-error");
            if (MessageUtil.isMessageEnabled(alreadyERR)) {
               Bukkit.getPlayer(args[1]).sendMessage(ColorUtil.colorText(alreadyERR));
            }

            return true;
         } else {
            String startMSG = LuxDialogues.getLang().getString("Messages.dialogue-started").replace("{DialogueName}", args[1]);
            if (MessageUtil.isMessageEnabled(startMSG)) {
               sender.sendMessage(ColorUtil.colorText(startMSG));
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
               if (!canStartDialogue(player)) {
                  return true;
               }

               LuxDialogues.getDialogueSender().sendDialogue(player, DataUtil.getDialogue(args[1]), args[2]);
            }

            return true;
         }
      } else if (args[0].equalsIgnoreCase("stop") && args.length == 2) {
         if (!sender.hasPermission("luxdialogues.stop") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else if (args[1].isEmpty()) {
            unknownCommand(sender);
            return true;
         } else if (Bukkit.getPlayer(args[1]) == null) {
            String playerERR = LuxDialogues.getLang().getString("Messages.player-error").replace("{TargetPH}", args[1]);
            if (MessageUtil.isMessageEnabled(playerERR)) {
               sender.sendMessage(ColorUtil.colorText(playerERR));
            }

            return true;
         } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) == null) {
            return true;
         } else {
            LuxDialogues.getDialogueSender().clearDialogue(Bukkit.getPlayer(args[1]));
            return true;
         }
      } else if (args[0].equalsIgnoreCase("stopall") && args.length == 1) {
         if (!sender.hasPermission("luxdialogues.stopall") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else {
            for (Player stopplayer : DataUtil.playerDialogues.keySet()) {
               if (DataUtil.getPlayerDialogue(stopplayer) == null) {
                  return true;
               }

               LuxDialogues.getDialogueSender().clearDialogue(stopplayer);
            }

            return true;
         }
      } else if (args[0].equalsIgnoreCase("send") && args.length == 4) {
         if (!sender.hasPermission("luxdialogues.send") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else if (args[1].isEmpty() || args[2].isEmpty()) {
            unknownCommand(sender);
            return true;
         } else if (Bukkit.getPlayer(args[1]) == null) {
            String playerERR = LuxDialogues.getLang().getString("Messages.player-error").replace("{TargetPH}", args[1]);
            if (MessageUtil.isMessageEnabled(playerERR)) {
               sender.sendMessage(ColorUtil.colorText(playerERR));
            }

            return true;
         } else if (!DataUtil.loadedDialogues.containsKey(args[2])) {
            dialogueError(sender, args[2]);
            return true;
         } else if (DataUtil.loadedDialogues.get(args[2]) != null && DataUtil.loadedDialogues.get(args[2]).getPages().get(args[3]) == null) {
            dialogueError(sender, args[2]);
            return true;
         } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) != null) {
            return true;
         } else if (!canStartDialogue(Bukkit.getPlayer(args[1]))) {
            return true;
         } else {
            String sendMSG = LuxDialogues.getLang().getString("Messages.dialogue-sent").replace("{TargetPH}", args[1]).replace("{DialogueName}", args[2]);
            if (MessageUtil.isMessageEnabled(sendMSG)) {
               sender.sendMessage(ColorUtil.colorText(sendMSG));
            }

            LuxDialogues.getDialogueSender().sendDialogue(Bukkit.getPlayer(args[1]), DataUtil.getDialogue(args[2]), args[3]);
            return true;
         }
      } else if (args[0].equalsIgnoreCase("send") && args.length == 5) {
         if (!sender.hasPermission("luxdialogues.send") && !sender.hasPermission("luxdialogues.admin")) {
            permissionError(sender);
            return true;
         } else if (args[1].isEmpty() || args[2].isEmpty()) {
            unknownCommand(sender);
            return true;
         } else if (Bukkit.getPlayer(args[1]) == null) {
            String playerERR = LuxDialogues.getLang().getString("Messages.player-error").replace("{TargetPH}", args[1]);
            if (MessageUtil.isMessageEnabled(playerERR)) {
               sender.sendMessage(ColorUtil.colorText(playerERR));
            }

            return true;
         } else if (!DataUtil.loadedDialogues.containsKey(args[2])) {
            dialogueError(sender, args[2]);
            return true;
         } else if (DataUtil.loadedDialogues.get(args[2]) != null && DataUtil.loadedDialogues.get(args[2]).getPages().get(args[3]) == null) {
            dialogueError(sender, args[2]);
            return true;
         } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) != null) {
            return true;
         } else if (!args[4].equalsIgnoreCase("-p")) {
            unknownCommand(sender);
            return true;
         } else if (!canStartDialogue(Bukkit.getPlayer(args[1]))) {
            return true;
         } else {
            String sendMSG = LuxDialogues.getLang().getString("Messages.dialogue-sent").replace("{TargetPH}", args[1]).replace("{DialogueName}", args[2]);
            if (MessageUtil.isMessageEnabled(sendMSG)) {
               sender.sendMessage(ColorUtil.colorText(sendMSG));
            }

            DataUtil.commandPrevent.put(Bukkit.getPlayer(args[1]), args[2]);
            LuxDialogues.getDialogueSender().sendDialogue(Bukkit.getPlayer(args[1]), DataUtil.getDialogue(args[2]), args[3]);
            return true;
         }
      } else if (!args[0].equalsIgnoreCase("redirect") || args.length != 4) {
         unknownCommand(sender);
         return true;
      } else if (!sender.hasPermission("luxdialogues.redirect") && !sender.hasPermission("luxdialogues.admin")) {
         permissionError(sender);
         return true;
      } else if (args[1].isEmpty() || args[2].isEmpty()) {
         unknownCommand(sender);
         return true;
      } else if (Bukkit.getPlayer(args[1]) == null) {
         String playerERR = LuxDialogues.getLang().getString("Messages.player-error").replace("{TargetPH}", args[1]);
         if (MessageUtil.isMessageEnabled(playerERR)) {
            sender.sendMessage(ColorUtil.colorText(playerERR));
         }

         return true;
      } else if (!DataUtil.loadedDialogues.containsKey(args[2])) {
         dialogueError(sender, args[2]);
         return true;
      } else if (DataUtil.loadedDialogues.get(args[2]) != null && DataUtil.loadedDialogues.get(args[2]).getPages().get(args[3]) == null) {
         dialogueError(sender, args[2]);
         return true;
      } else if (DataUtil.getPlayerDialogue(Bukkit.getPlayer(args[1])) != null) {
         return true;
      } else if (!canStartDialogue(Bukkit.getPlayer(args[1]))) {
         return true;
      } else {
         String sendMSG = LuxDialogues.getLang().getString("Messages.dialogue-sent").replace("{TargetPH}", args[1]).replace("{DialogueName}", args[2]);
         if (MessageUtil.isMessageEnabled(sendMSG)) {
            sender.sendMessage(ColorUtil.colorText(sendMSG));
         }

         if (LuxDialogues.getProvider().isInDialogue(Bukkit.getPlayer(args[1]))) {
            LuxDialogues.getDialogueSender().redirectDialogue(Bukkit.getPlayer(args[1]), DataUtil.getDialogue(args[2]), args[3]);
         } else {
            LuxDialogues.getDialogueSender().sendDialogue(Bukkit.getPlayer(args[1]), DataUtil.getDialogue(args[2]), args[3]);
         }

         return true;
      }
   }

   private static void reloadCommand(CommandSender sender) {
      HandlerList.unregisterAll(LuxDialogues.getInstance());

      for (PacketListenerCommon packetListener : DataUtil.packetListeners.values()) {
         PacketEvents.getAPI().getEventManager().unregisterListener(packetListener);
      }

      YamlUtil.reload("config.yml");
      YamlUtil.reload("Pack/Fonts/pages.yml");
      YamlUtil.reload("Pack/Images/images.yml");
      YamlUtil.reload("Pack/Sounds/sounds.yml");
      YamlUtil.reload("Pack/Lines/lines.yml");
      LuxDialogues.listenerRegisterer();
      YamlUtil.reload("Langs/" + YamlUtil.get("config.yml").getString("Settings.lang") + ".yml");

      for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
         YamlUtil.reload("Dialogues/" + fileName + ".yml");
      }

      String reloadMSG = LuxDialogues.getLang().getString("Messages.reload");
      if (MessageUtil.isMessageEnabled(reloadMSG)) {
         sender.sendMessage(ColorUtil.colorText(reloadMSG));
      }

      DataUtil.loadedDialogues.clear();
      LoaderUtil.loadDialoguesFromFolder();
   }

   private static void createResourceCommand(CommandSender sender) {
      ResourceUtil.createResourcePack(sender);
   }

   private static void dialogueError(CommandSender sender, String target) {
      String diaERR = LuxDialogues.getLang().getString("Messages.unknown-dialogue").replace("{DialogueName}", target);
      if (MessageUtil.isMessageEnabled(diaERR)) {
         sender.sendMessage(ColorUtil.colorText(diaERR));
      }
   }

   private static void unknownCommand(CommandSender sender) {
      String unkERR = LuxDialogues.getLang().getString("Messages.unknown-command");
      if (MessageUtil.isMessageEnabled(unkERR)) {
         sender.sendMessage(ColorUtil.colorText(unkERR));
      }
   }

   private static void consoleError(CommandSender sender) {
      String conkERR = LuxDialogues.getLang().getString("Messages.console-error");
      if (MessageUtil.isMessageEnabled(conkERR)) {
         sender.sendMessage(ColorUtil.colorText(conkERR));
      }
   }

   private static void permissionError(CommandSender sender) {
      String permERR = LuxDialogues.getLang().getString("Messages.permission-error");
      if (MessageUtil.isMessageEnabled(permERR)) {
         sender.sendMessage(ColorUtil.colorText(permERR));
      }
   }

   private static boolean canStartDialogue(Player player) {
      Long cooldownEnd = DataUtil.commandCooldown.get(player);
      if (cooldownEnd == null) {
         return true;
      } else if (System.currentTimeMillis() > cooldownEnd) {
         DataUtil.commandCooldown.remove(player);
         return true;
      } else {
         return false;
      }
   }
}
