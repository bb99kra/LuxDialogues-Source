package org.aselstudios.luxdialogues.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class YamlUtil {
   private static final Map<String, FileConfiguration> configs = new HashMap<>();

   private YamlUtil() {
   }

   public static boolean isFileLoaded(String fileName) {
      return configs.containsKey(fileName);
   }

   public static void load(String fileName) {
      File file = new File(LuxDialogues.getInstance().getDataFolder(), fileName);
      if (!file.getParentFile().exists()) {
         file.getParentFile().mkdirs();
      }

      if (!file.exists()) {
         try {
            LuxDialogues.getInstance().saveResource(fileName, false);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

      if (!isFileLoaded(fileName)) {
         configs.put(fileName, YamlConfiguration.loadConfiguration(file));
      }
   }

   public static FileConfiguration get(String fileName) {
      File file = new File(LuxDialogues.getInstance().getDataFolder(), fileName);
      if (!file.getParentFile().exists()) {
         file.getParentFile().mkdirs();
      }

      if (isFileLoaded(fileName)) {
         return configs.get(fileName);
      } else {
         load(fileName);
         return configs.get(fileName);
      }
   }

   public static void reload(String fileName) {
      File file = new File(LuxDialogues.getInstance().getDataFolder(), fileName);
      if (!file.getParentFile().exists()) {
         file.getParentFile().mkdirs();
      }

      if (isFileLoaded(fileName)) {
         try {
            configs.get(fileName).load(file);
            load(fileName);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }
   }

   public static void save(String fileName) {
      File file = new File(LuxDialogues.getInstance().getDataFolder(), fileName);
      if (!file.getParentFile().exists()) {
         file.getParentFile().mkdirs();
      }

      if (isFileLoaded(fileName)) {
         try {
            configs.get(fileName).save(file);
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }
   }

   public static List<String> getFlexibleStringList(FileConfiguration file, String path) {
      List<String> list = file.getStringList(path);
      if (!list.isEmpty()) {
         return list;
      } else {
         String single = file.getString(path);
         return single != null ? Collections.singletonList(single) : Collections.emptyList();
      }
   }

   public static List<String> getAllYamlFilesInFolder(String folderName) {
      List<String> names = new ArrayList<>();
      File folder = new File("plugins/LuxDialogues/" + folderName + "/");
      if (folder.exists() && folder.isDirectory()) {
         File[] files = folder.listFiles();
         if (files == null) {
            return names;
         } else {
            for (File file : files) {
               if (file.getName().endsWith(".yml")) {
                  names.add(file.getName().replace(".yml", ""));
               }
            }

            return names;
         }
      } else {
         return names;
      }
   }

   public static List<String> getAllYamlFileNames(String folderPath) {
      List<String> fileNames = new ArrayList<>();
      File folder = new File(LuxDialogues.getInstance().getDataFolder(), folderPath);
      if (folder.exists() && folder.isDirectory()) {
         getYamlFileNamesRecursive(folder, "", fileNames);
      }

      return fileNames;
   }

   private static void getYamlFileNamesRecursive(File folder, String relativePath, List<String> fileNames) {
      File[] files = folder.listFiles();
      if (files != null) {
         for (File file : files) {
            if (file.isDirectory()) {
               String newRelativePath = relativePath.isEmpty() ? file.getName() : relativePath + "/" + file.getName();
               getYamlFileNamesRecursive(file, newRelativePath, fileNames);
            } else if (file.getName().endsWith(".yml")) {
               String fileNameWithoutExt = file.getName().substring(0, file.getName().length() - 4);
               if (relativePath.isEmpty()) {
                  fileNames.add(fileNameWithoutExt);
               } else {
                  fileNames.add(relativePath + "/" + fileNameWithoutExt);
               }
            }
         }
      }
   }
}
