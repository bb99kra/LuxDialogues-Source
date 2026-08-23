package org.aselstudios.luxdialogues.Utils;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import io.lumine.mythic.bukkit.MythicBukkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ExecuteUtil {
   private static final Pattern CONDITION_PATTERN = Pattern.compile("@condition\\.([^\\s]+)");
   private static final Pattern DELAY_PATTERN = Pattern.compile("@delay\\.([\\d]+)");
   private static final Pattern CONSOLE_PATTERN = Pattern.compile("@console");
   private static final Pattern MYTHICMOBSKILL_PATTERN = Pattern.compile("@mythicmobskill");

   public static void executeAction(Player player, String action) {
      if (ForkUtil.isUsingFolia()) {
         foliaExecutor(player, action);
      } else {
         spigotExecutor(player, action);
      }
   }

   private static void foliaExecutor(Player player, String action) {
      ExecuteUtil.ParsedAction parsed = parseAction(player, action);
      if (parsed.delay <= 0) {
         LuxDialogues.getMorePaperLib().scheduling().globalRegionalScheduler().run(task -> executeParsedAction(player, parsed));
      } else {
         LuxDialogues.getMorePaperLib().scheduling().globalRegionalScheduler().runDelayed(task -> executeParsedAction(player, parsed), parsed.delay);
      }
   }

   private static void spigotExecutor(Player player, String action) {
      ExecuteUtil.ParsedAction parsed = parseAction(player, action);
      if (parsed.delay <= 0) {
         Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> executeParsedAction(player, parsed));
      } else {
         Bukkit.getScheduler().runTaskLater(LuxDialogues.getInstance(), () -> executeParsedAction(player, parsed), parsed.delay);
      }
   }

   private static void executeParsedAction(Player player, ExecuteUtil.ParsedAction parsed) {
      if (parsed.isMythicMobSkill) {
         castMythicMobSkill(player, parsed.action);
      } else {
         runCommand(player, parsed.action, parsed.isConsole);
      }
   }

   private static void runCommand(Player player, String action, boolean isConsole) {
      if (action.startsWith("server")) {
         String server = action.replaceFirst("server", "").trim();
         sendConnectMessage(player, server);
      } else if (!action.isEmpty()) {
         String processed = ProcessUtil.processPlaceholder(player, action);
         if (isConsole) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), processed);
         } else {
            Bukkit.dispatchCommand(player, processed);
         }
      }
   }

   public static void sendConnectMessage(Player player, String server) {
      ByteArrayDataOutput out = ByteStreams.newDataOutput();
      out.writeUTF("Connect");
      out.writeUTF(server);
      player.sendPluginMessage(LuxDialogues.getInstance(), "BungeeCord", out.toByteArray());
   }

   public static void castMythicMobSkill(Player player, String skillName) {
      MythicBukkit.inst().getAPIHelper().castSkill(player, skillName);
   }

   private static ExecuteUtil.ParsedAction parseAction(Player player, String action) {
      boolean isConsole = false;
      boolean isMythicMobSkill = false;
      int delay = 0;

      for (Matcher conditionMatcher = CONDITION_PATTERN.matcher(action); conditionMatcher.find(); action = action.replace(conditionMatcher.group(), "").trim()) {
         String conditionName = conditionMatcher.group(1);
         if (!ConditionUtil.areConditionsTrue(player, conditionName)) {
            action = "";
            break;
         }
      }

      Matcher delayMatcher = DELAY_PATTERN.matcher(action);
      if (delayMatcher.find()) {
         try {
            delay = Integer.parseInt(delayMatcher.group(1)) * 20;
         } catch (NumberFormatException var9) {
            Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("&4LuxDialogues &7- &cInvalid delay in dialog file."));
         }

         action = action.replace(delayMatcher.group(), "").trim();
      }

      Matcher consoleMatcher = CONSOLE_PATTERN.matcher(action);
      if (consoleMatcher.find()) {
         isConsole = true;
         action = action.replace(consoleMatcher.group(), "").trim();
      }

      Matcher mythicMatcher = MYTHICMOBSKILL_PATTERN.matcher(action);
      if (mythicMatcher.find()) {
         isMythicMobSkill = true;
         action = action.replace(mythicMatcher.group(), "").trim();
      }

      return new ExecuteUtil.ParsedAction(action, delay, isConsole, isMythicMobSkill);
   }

   private static class ParsedAction {
      String action;
      int delay;
      boolean isConsole;
      boolean isMythicMobSkill;

      ParsedAction(String action, int delay, boolean isConsole, boolean isMythicMobSkill) {
         this.action = action;
         this.delay = delay;
         this.isConsole = isConsole;
         this.isMythicMobSkill = isMythicMobSkill;
      }
   }
}
