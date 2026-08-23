package org.aselstudios.luxdialogues.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class ProcessUtil {
   public static String removeHexCodes(String dialogueLine) {
      Pattern hexPattern = Pattern.compile("#[0-9A-Fa-f]{6}");
      return hexPattern.matcher(dialogueLine).replaceAll("");
   }

   public static String replaceHexCodes(String dialogueLine) {
      Pattern hexPattern = Pattern.compile("(#([0-9A-Fa-f]{6}))");
      Matcher matcher = hexPattern.matcher(dialogueLine);
      StringBuilder sb = new StringBuilder();

      while (matcher.find()) {
         matcher.appendReplacement(sb, "<color:" + matcher.group(1) + ">");
      }

      matcher.appendTail(sb);
      return sb.toString();
   }

   public static List<String> removeHexCodes(List<String> dialogueLines) {
      List<String> cleanedLines = new ArrayList<>();
      Pattern hexPattern = Pattern.compile("#[0-9A-Fa-f]{6}");

      for (String line : dialogueLines) {
         String cleanedLine = hexPattern.matcher(line).replaceAll("");
         cleanedLines.add(cleanedLine);
      }

      return cleanedLines;
   }

   public static List<String> processHexPositions(List<String> dialogueLines) {
      List<String> results = new ArrayList<>();
      Pattern hexPattern = Pattern.compile("#[0-9A-Fa-f]{6}");

      for (int i = 0; i < dialogueLines.size(); i++) {
         String line = dialogueLines.get(i);
         Matcher matcher = hexPattern.matcher(line);

         while (matcher.find()) {
            int startIndex = matcher.start() + 1;
            results.add(i + 1 + ";" + startIndex);
         }
      }

      return results;
   }

   public static List<String> replaceHexCodes(List<String> dialogueLines) {
      List<String> replacedLines = new ArrayList<>();
      Pattern hexPattern = Pattern.compile("(#([0-9A-Fa-f]{6}))");

      for (String line : dialogueLines) {
         Matcher matcher = hexPattern.matcher(line);
         StringBuilder sb = new StringBuilder();

         while (matcher.find()) {
            matcher.appendReplacement(sb, "<color:" + matcher.group(1) + ">");
         }

         matcher.appendTail(sb);
         replacedLines.add(sb.toString());
      }

      return replacedLines;
   }

   public static List<String> processPlaceholderList(Player player, List<String> lines) {
      List<String> formattedLines = new ArrayList<>();

      for (String line : lines) {
         formattedLines.add(PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setBracketPlaceholders(player, line)));
      }

      return formattedLines;
   }

   public static String processPlaceholder(Player player, String line) {
      return PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setBracketPlaceholders(player, line));
   }
}
