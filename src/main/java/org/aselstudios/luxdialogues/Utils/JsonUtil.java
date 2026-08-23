package org.aselstudios.luxdialogues.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import org.aselstudios.luxdialogues.LuxDialogues;

public class JsonUtil {
   private static final Map<String, JsonObject> configs = new HashMap<>();
   private static final Gson gson = new Gson();

   private JsonUtil() {
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
         save(fileName, new JsonObject());
      }

      try (FileReader reader = new FileReader(file)) {
         JsonParser parser = new JsonParser();
         configs.put(fileName, parser.parse(reader).getAsJsonObject());
      } catch (Exception var7) {
         var7.printStackTrace();
      }
   }

   public static JsonObject get(String fileName) {
      if (!isFileLoaded(fileName)) {
         load(fileName);
      }

      return configs.get(fileName);
   }

   public static void save(String fileName) {
      if (isFileLoaded(fileName)) {
         save(fileName, configs.get(fileName));
      }
   }

   public static void save(String fileName, JsonObject jsonObject) {
      File file = new File(LuxDialogues.getInstance().getDataFolder(), fileName);
      if (!file.getParentFile().exists()) {
         file.getParentFile().mkdirs();
      }

      try (FileWriter writer = new FileWriter(file)) {
         gson.toJson(jsonObject, writer);
      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   public static void reload(String fileName) {
      if (isFileLoaded(fileName)) {
         load(fileName);
      }
   }
}
