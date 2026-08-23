package org.aselstudios.luxdialoguesapi;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class LuxDialoguesAPI extends JavaPlugin {
   private static LuxDialoguesAPI api;
   private static DialogueProvider provider;

   public static void setInstance(LuxDialoguesAPI instance) {
      if (api != null) {
         throw new IllegalStateException("Plugin instance is already set!");
      } else if (instance == null) {
         throw new NullPointerException("Plugin instance cannot be null!");
      } else {
         api = instance;
      }
   }

   public static LuxDialoguesAPI getAPI() {
      return api;
   }

   public static void setProvider(DialogueProvider p) {
      if (provider != null) {
         throw new IllegalStateException("DialogueProvider already set!");
      } else {
         provider = p;
      }
   }

   public static DialogueProvider getProvider() {
      if (provider == null) {
         throw new IllegalStateException("DialogueProvider not set!");
      } else {
         return provider;
      }
   }
}
