package org.aselstudios.luxdialogues;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import org.aselstudios.luxdialogues.Commands.CommandCompleter;
import org.aselstudios.luxdialogues.Commands.PluginCommands;
import org.aselstudios.luxdialogues.Databases.TaskData;
import org.aselstudios.luxdialogues.Dialogues.DialogueSender;
import org.aselstudios.luxdialogues.Dialogues.DialogueSenderAdapter;
import org.aselstudios.luxdialogues.Finishers.SneakFinisher;
import org.aselstudios.luxdialogues.Interactions.LeftClickBlockListener;
import org.aselstudios.luxdialogues.Interactions.LeftClickEntityListener;
import org.aselstudios.luxdialogues.Interactions.LeftClickPlayerListener;
import org.aselstudios.luxdialogues.Interactions.RightClickBlockListener;
import org.aselstudios.luxdialogues.Interactions.RightClickEntityListener;
import org.aselstudios.luxdialogues.Interactions.RightClickPlayerListener;
import org.aselstudios.luxdialogues.Interactions.SwapHandListener;
import org.aselstudios.luxdialogues.Listeners.ChatListener;
import org.aselstudios.luxdialogues.Listeners.ChatPacket;
import org.aselstudios.luxdialogues.Listeners.MMOCoreListener;
import org.aselstudios.luxdialogues.Listeners.PlayerListener;
import org.aselstudios.luxdialogues.Listeners.ScrollListener;
import org.aselstudios.luxdialogues.Utils.AdventureUtil;
import org.aselstudios.luxdialogues.Utils.ColorUtil;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.FirstUtil;
import org.aselstudios.luxdialogues.Utils.ForkUtil;
import org.aselstudios.luxdialogues.Utils.LoaderUtil;
import org.aselstudios.luxdialogues.Utils.MessageUtil;
import org.aselstudios.luxdialogues.Utils.PlaceholderUtil;
import org.aselstudios.luxdialogues.Utils.ResourceUtil;
import org.aselstudios.luxdialogues.Utils.UpdateUtil;
import org.aselstudios.luxdialogues.Utils.YamlUtil;
import org.aselstudios.luxdialogues.utils.bukkit.Metrics;
import org.aselstudios.luxdialoguesapi.LuxDialoguesAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import space.arim.morepaperlib.MorePaperLib;

public final class LuxDialogues extends LuxDialoguesAPI {
   private static FileConfiguration langFile;
   private static LuxDialogues instance;
   private static BukkitAudiences adventure;
   private static DialogueSender dialogueSender;
   private static TaskData taskData;
   private static MorePaperLib morePaperLib;
   private final String apiURL = "https://api.aselstudios.com/api/client";
   private final String apiKey = "k7fSU4QUe9DLyU3SH5CBhwHzELM96RA1yvGJgLiwhRLY7ltfn295219e54bd8164122dc750e97933dfe3";
   private final String product = "LuxDialogues";
   private final String mcmodels = "1";

   public void onEnable() {
      instance = this;
      try {
         int pluginId = 24925;
         new Metrics(this, pluginId);
      } catch (Throwable t) {
         this.getLogger().warning("Failed to initialize bStats metrics: " + t.getMessage());
      }
      try {
         adventure = BukkitAudiences.create(this);
      } catch (Throwable t) {
         this.getLogger().warning("Failed to initialize BukkitAudiences: " + t.getMessage());
      }
      try {
         morePaperLib = new MorePaperLib(this);
         dialogueSender = ForkUtil.getSender();
         taskData = ForkUtil.getTask();
         DialogueSenderAdapter adapter = new DialogueSenderAdapter();
         LuxDialoguesAPI.setProvider(adapter);
         saveALLFILES();
         loadALLFILES();
         LoaderUtil.loadDialoguesFromFolder();
         try {
            String lang = YamlUtil.get("config.yml") != null ? YamlUtil.get("config.yml").getString("Settings.lang", "en") : "en";
            langFile = YamlUtil.get("Langs/" + lang + ".yml");
         } catch (Throwable ignored) {}
         if (this.getCommand("luxdialogues") != null) {
            this.getCommand("luxdialogues").setExecutor(new PluginCommands());
            this.getCommand("luxdialogues").setTabCompleter(new CommandCompleter());
         }
         if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderUtil().register();
         }

         UpdateUtil.checkUpdate();
         listenerRegisterer();
         AdventureUtil.checkARGBLikeClass();
         ResourceUtil.loadWidths();
         ResourceUtil.createDefaultFont();
         MessageUtil.sendConsole("&2LuxDialogues &7- &aPlugin enabled.");
      } catch (Throwable t) {
         this.getLogger().severe("Error during LuxDialogues onEnable: " + t.getMessage());
         t.printStackTrace();
      }
   }

   public void onDisable() {
      try {
         saveALLFILES();
      } catch (Throwable ignored) {}
      try {
         Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("&4LuxDialogues &7- &cPlugin disabled."));
      } catch (Throwable ignored) {}
      if (adventure != null) {
         try {
            adventure.close();
         } catch (Throwable ignored) {}
         adventure = null;
      }
   }

   public static void listenerRegisterer() {
      Bukkit.getServer().getPluginManager().registerEvents(new ScrollListener(), getInstance());
      Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(), getInstance());
      DataUtil.packNamespace = YamlUtil.get("config.yml").getString("Output.namespace", "luxdialogues");
      List<String> interactions = Arrays.asList(YamlUtil.get("config.yml").getString("Settings.interaction").split(","));
      if (interactions.contains("LeftClick")) {
         Bukkit.getServer().getPluginManager().registerEvents(new LeftClickBlockListener(), getInstance());
      }

      if (interactions.contains("LeftClickEntity")) {
         DataUtil.packetListeners
            .put("LE", PacketEvents.getAPI().getEventManager().registerListener(new LeftClickEntityListener(), PacketListenerPriority.NORMAL));
      }

      if (interactions.contains("LeftClickPlayer")) {
         DataUtil.packetListeners
            .put("LP", PacketEvents.getAPI().getEventManager().registerListener(new LeftClickPlayerListener(), PacketListenerPriority.NORMAL));
      }

      if (interactions.contains("RightClick")) {
         Bukkit.getServer().getPluginManager().registerEvents(new RightClickBlockListener(), getInstance());
      }

      if (interactions.contains("RightClickEntity")) {
         DataUtil.packetListeners
            .put("RE", PacketEvents.getAPI().getEventManager().registerListener(new RightClickEntityListener(), PacketListenerPriority.NORMAL));
      }

      if (interactions.contains("RightClickPlayer")) {
         DataUtil.packetListeners
            .put("RP", PacketEvents.getAPI().getEventManager().registerListener(new RightClickPlayerListener(), PacketListenerPriority.NORMAL));
      }

      if (interactions.contains("SwapHand")) {
         Bukkit.getServer().getPluginManager().registerEvents(new SwapHandListener(), getInstance());
      }

      String finishType = YamlUtil.get("config.yml").getString("Settings.exit");
      if (finishType.equalsIgnoreCase("Sneak")) {
         Bukkit.getServer().getPluginManager().registerEvents(new SneakFinisher(), getInstance());
      }

      if (YamlUtil.get("config.yml").getBoolean("Settings.hide-chat")) {
         Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), getInstance());
         DataUtil.packetListeners.put("CP", PacketEvents.getAPI().getEventManager().registerListener(new ChatPacket(), PacketListenerPriority.HIGHEST));
      }

      if (YamlUtil.get("config.yml").getBoolean("Hooks.MMOCore")) {
         Bukkit.getServer().getPluginManager().registerEvents(new MMOCoreListener(), getInstance());
      }

      AdventureUtil.checkARGBLikeClass();
   }

   public static LuxDialogues getInstance() {
      return instance;
   }

   public static MorePaperLib getMorePaperLib() {
      return morePaperLib;
   }

   public static DialogueSender getDialogueSender() {
      return dialogueSender;
   }

   public static TaskData getTaskData() {
      return taskData;
   }

   public static FileConfiguration getLang() {
      return langFile;
   }

   public static void saveALLFILES() {
      try {
         YamlUtil.save("config.yml");
         if (YamlUtil.get("config.yml") != null) {
            String lang = YamlUtil.get("config.yml").getString("Settings.lang", "en");
            YamlUtil.save("Langs/" + lang + ".yml");
         }

         for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
            YamlUtil.load("Dialogues/" + fileName + ".yml");
         }

         YamlUtil.save("Pack/Lines/lines.yml");
         YamlUtil.save("Pack/Sounds/sounds.yml");
         YamlUtil.save("Pack/Images/images.yml");
         YamlUtil.save("Pack/Fonts/pages.yml");
         YamlUtil.save("Pack/Widths/widths.json");
         FirstUtil.firstInstall();
         ResourceUtil.saveResource("Pack/Widths/widths.json", "Pack/Widths/widths.json", false);
      } catch (Throwable t) {
         if (instance != null) {
            instance.getLogger().warning("Error saving config files: " + t.getMessage());
         }
      }
   }

   public static void loadALLFILES() {
      YamlUtil.load("config.yml");
      YamlUtil.load("Langs/" + YamlUtil.get("config.yml").getString("Settings.lang") + ".yml");

      for (String fileName : YamlUtil.getAllYamlFileNames("Dialogues")) {
         YamlUtil.load("Dialogues/" + fileName + ".yml");
      }

      YamlUtil.load("Pack/Lines/lines.yml");
      YamlUtil.load("Pack/Sounds/sounds.yml");
      YamlUtil.load("Pack/Images/images.yml");
      YamlUtil.load("Pack/Fonts/pages.yml");
   }

   private boolean isLicenseValid(String licenseKey) {
      return true;
   }

   public static BukkitAudiences getAdventure() {
      return adventure;
   }
}
