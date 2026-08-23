package org.aselstudios.luxdialogues.Commands;

import java.util.ArrayList;
import java.util.List;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.YamlUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class CommandCompleter implements TabCompleter {
   public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
      List<String> completions = new ArrayList<>();
      if (args.length == 1) {
         if (sender.hasPermission("luxdialogues.admin")) {
            completions.add("reload");
            completions.add("start");
            completions.add("send");
            completions.add("create-pack");
            completions.add("stop");
            completions.add("stopall");
            completions.add("sendall");
         } else if (sender.hasPermission("luxdialogues.start")) {
            completions.add("start");
         } else if (sender.hasPermission("luxdialogues.send")) {
            completions.add("send");
         } else if (sender.hasPermission("luxdialogues.sendall")) {
            completions.add("sendall");
         } else if (sender.hasPermission("luxdialogues.stop")) {
            completions.add("stop");
         } else if (sender.hasPermission("luxdialogues.stopall")) {
            completions.add("stopall");
         } else if (sender.hasPermission("luxdialogues.create-pack")) {
            completions.add("create-pack");
         }

         completions.add("about");
      } else if (args.length == 2 && args[0].equalsIgnoreCase("start")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.start")) {
            for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
               completions.add(fileName);
            }
         }
      } else if (args.length == 3 && args[0].equalsIgnoreCase("start")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.start")) {
            for (String pageID : DataUtil.loadedDialogues.get(args[1]).getPages().keySet()) {
               completions.add(pageID);
            }
         }
      } else if (args.length == 2 && args[0].equalsIgnoreCase("stop")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.stop")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
               completions.add(player.getName());
            }
         }
      } else if (args.length == 2 && args[0].equalsIgnoreCase("sendall")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.sendall")) {
            for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
               completions.add(fileName);
            }
         }
      } else if (args.length == 3 && args[0].equalsIgnoreCase("sendall")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.sendall")) {
            for (String pageID : DataUtil.loadedDialogues.get(args[1]).getPages().keySet()) {
               completions.add(pageID);
            }
         }
      } else if (args.length == 2 && args[0].equalsIgnoreCase("send")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.send")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
               completions.add(player.getName());
            }
         }
      } else if (args.length == 3 && args[0].equalsIgnoreCase("send")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.send")) {
            for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
               completions.add(fileName);
            }
         }
      } else if (args.length == 4 && args[0].equalsIgnoreCase("send")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.send")) {
            for (String pageID : DataUtil.loadedDialogues.get(args[2]).getPages().keySet()) {
               completions.add(pageID);
            }
         }
      } else if (args.length == 5 && args[0].equalsIgnoreCase("send")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.send")) {
            completions.add("-p");
         }
      } else if (args.length == 2 && args[0].equalsIgnoreCase("redirect")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.redirect")) {
            for (Player player : Bukkit.getOnlinePlayers()) {
               completions.add(player.getName());
            }
         }
      } else if (args.length == 3 && args[0].equalsIgnoreCase("redirect")) {
         if (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.redirect")) {
            for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
               completions.add(fileName);
            }
         }
      } else if (args.length == 4
         && args[0].equalsIgnoreCase("redirect")
         && (sender.hasPermission("luxdialogues.admin") || sender.hasPermission("luxdialogues.redirect"))) {
         for (String pageID : DataUtil.loadedDialogues.get(args[2]).getPages().keySet()) {
            completions.add(pageID);
         }
      }

      return completions;
   }
}
