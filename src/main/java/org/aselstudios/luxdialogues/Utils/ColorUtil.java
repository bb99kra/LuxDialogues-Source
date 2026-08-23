package org.aselstudios.luxdialogues.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;

public class ColorUtil {
   public static String colorText(String text) {
      if (text == null) {
         return null;
      } else {
         Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
         if (Bukkit.getVersion().contains("1.16")
            || Bukkit.getVersion().contains("1.17")
            || Bukkit.getVersion().contains("1.18")
            || Bukkit.getVersion().contains("1.19")
            || Bukkit.getVersion().contains("1.20")
            || Bukkit.getVersion().contains("1.21")) {
            for (Matcher matcher = pattern.matcher(text); matcher.find(); matcher = pattern.matcher(text)) {
               String color = text.substring(matcher.start(), matcher.end());
               text = text.replace(color, ChatColor.of(color) + "");
            }
         }

         return ChatColor.translateAlternateColorCodes('&', text);
      }
   }
}
