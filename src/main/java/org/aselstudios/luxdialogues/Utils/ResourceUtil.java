package org.aselstudios.luxdialogues.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.AlphaComposite;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ResourceUtil {
   private static List<String> unicodeList = new ArrayList<>(
      Arrays.asList(
         "ᚠ",
         "ᚡ",
         "ᚢ",
         "ᚣ",
         "ᚤ",
         "ᚥ",
         "ᚦ",
         "ᚧ",
         "ᚨ",
         "ᚩ",
         "ᚪ",
         "ᚫ",
         "ᚬ",
         "ᚭ",
         "ᚮ",
         "ᚯ",
         "ᚰ",
         "ᚱ",
         "ᚲ",
         "ᚳ",
         "ᚴ",
         "ᚵ",
         "ᚶ",
         "ᚷ",
         "ᚸ",
         "ᚹ",
         "ᚺ",
         "ᚻ",
         "ᚼ",
         "ᚽ",
         "ᚾ",
         "ᚿ",
         "ᛀ",
         "ᛁ",
         "ᛂ",
         "ᛃ",
         "ᛄ",
         "ᛅ",
         "ᛆ",
         "ᛇ",
         "ᛈ",
         "ᛉ",
         "ᛊ",
         "ᛋ",
         "ᛌ",
         "ᛍ",
         "ᛎ",
         "ᛏ",
         "ᛐ",
         "ᛑ",
         "ᛒ",
         "ᛓ",
         "ᛔ",
         "ᛕ",
         "ᛖ",
         "ᛗ",
         "ᛘ",
         "ᛙ",
         "ᛚ",
         "ᛛ",
         "ᛜ",
         "ᛝ",
         "ᛞ",
         "ᛟ",
         "ᚁ",
         "ᚂ",
         "ᚃ",
         "ᚄ",
         "ᚅ",
         "ᚆ",
         "ᚇ",
         "ᚈ",
         "ᚉ",
         "ᚊ",
         "ᚋ",
         "ᚌ",
         "ᚍ",
         "ᚎ",
         "ᚏ",
         "ⴰ",
         "ⴱ",
         "ⴲ",
         "ⴳ",
         "ⴴ",
         "ⴵ",
         "ⴶ",
         "ⴷ",
         "ⴸ",
         "ⴹ",
         "ⴺ",
         "ⴻ",
         "ⴼ",
         "ⴽ",
         "ⴾ",
         "ⴿ",
         "ⵀ",
         "ⵁ",
         "ⵂ",
         "ⵃ",
         "ⵄ",
         "\ud800\udf00",
         "\ud800\udf01",
         "\ud800\udf02",
         "\ud800\udf03",
         "\ud800\udf04",
         "\ud800\udf05",
         "\ud800\udf06",
         "\ud800\udf07",
         "\ud800\udf08",
         "\ud800\udf09",
         "\ud800\udf0a",
         "\ud800\udf0b",
         "\ud800\udf0c",
         "\ud800\udf0d",
         "\ud800\udf0e",
         "\ud800\udf0f",
         "\ud800\udf10",
         "\ud800\udf11",
         "\ud800\udf12",
         "\ud800\udf13",
         "\ud800\udf14",
         "\ud800\udf15",
         "\ud800\udf16",
         "\ud800\udf17",
         "\ud800\udf18",
         "\ud800\udf19",
         "\ud800\udf1a",
         "\ud800\udf1b",
         "\ud800\udf1c",
         "\ud800\udf1d",
         "\ud800\udf1e",
         "\ud800\udf1f",
         "\ud800\udf20",
         "\ud800\udf21",
         "\ud800\udf22",
         "\ud800\udf23",
         "\ud800\udf2d",
         "\ud800\udf2e",
         "\ud800\udf2f",
         "\ud800\udf30",
         "\ud800\udf31",
         "\ud800\udf32",
         "\ud800\udf33",
         "\ud800\udf34",
         "\ud800\udf35",
         "\ud800\udf36",
         "\ud800\udf37",
         "\ud800\udf38",
         "\ud800\udf39",
         "\ud800\udf3a",
         "\ud800\udf3b",
         "\ud800\udf3c",
         "\ud800\udf3d",
         "\ud800\udf3e",
         "\ud800\udf3f",
         "\ud800\udf40",
         "\ud800\udf41",
         "\ud800\udf42",
         "\ud800\udf43",
         "\ud800\udf44",
         "\ud800\udf45",
         "\ud800\udf46",
         "\ud800\udf47",
         "\ud800\udf48",
         "\ud800\udf49",
         "\ud800\udf4a",
         "\ud800\udf80",
         "\ud800\udf81",
         "\ud800\udf82",
         "\ud800\udf83",
         "\ud800\udf84",
         "\ud800\udf85",
         "\ud800\udf86",
         "\ud800\udf87",
         "\ud800\udf88",
         "\ud800\udf89",
         "\ud800\udf8a",
         "\ud800\udf8b",
         "\ud800\udf8c",
         "\ud800\udf8d",
         "\ud800\udf8e",
         "\ud800\udf8f",
         "\ud800\udf90",
         "\ud800\udf91",
         "\ud800\udf92",
         "\ud800\udf93",
         "Ⰰ",
         "Ⰱ",
         "Ⰲ",
         "Ⰳ",
         "Ⰴ",
         "Ⰵ",
         "Ⰶ",
         "Ⰷ",
         "Ⰸ",
         "Ⰹ",
         "Ⰺ",
         "Ⰻ",
         "Ⰼ",
         "Ⰽ",
         "Ⰾ",
         "Ⰿ",
         "Ⱀ",
         "Ⱁ",
         "Ⱂ",
         "Ⱃ",
         "Ⱄ",
         "Ⱅ",
         "Ⱆ",
         "Ⱇ",
         "Ⱈ",
         "Ⱉ",
         "Ⱊ",
         "Ⱋ",
         "Ⱌ",
         "Ⱍ",
         "Ⱎ",
         "Ⱏ",
         "Ⱐ",
         "Ⱑ",
         "Ⱒ",
         "Ⱓ",
         "Ⱔ",
         "Ⱕ",
         "Ⱖ",
         "Ⱗ",
         "Ⱘ",
         "Ⱙ",
         "Ⱚ",
         "Ⱛ",
         "Ⱜ",
         "Ⱝ",
         "Ⱞ",
         "Ⱟ",
         "ⰰ",
         "ⰱ",
         "ⰲ",
         "ⰳ",
         "ⰴ",
         "ⰵ",
         "ⰶ",
         "ⰷ",
         "ⰸ",
         "ⰹ",
         "ⰺ",
         "ⰻ",
         "ⰼ",
         "ⰽ",
         "ⰾ",
         "ⰿ",
         "ⱀ",
         "ⱁ",
         "ⱂ",
         "ⱃ",
         "ⱄ",
         "ⱅ",
         "ⱆ",
         "ⱇ",
         "ⱈ",
         "ⱉ",
         "ⱊ",
         "ⱋ",
         "ⱌ",
         "ⱍ",
         "ⱎ",
         "ⱏ",
         "ⱐ",
         "ⱑ",
         "ⱒ",
         "ⱓ",
         "ⱔ",
         "ⱕ",
         "ⱖ",
         "ⱗ",
         "ⱘ",
         "ⱙ",
         "ⱚ",
         "ⱛ",
         "ⱜ",
         "ⱝ",
         "ⱞ",
         "ⱟ",
         "\ud801\udc00",
         "\ud801\udc01",
         "\ud801\udc02",
         "\ud801\udc03"
      )
   );
   public static HashMap<String, Integer> imageSizes = new HashMap<>();
   public static HashMap<String, String> imageUnicodes = new HashMap<>();
   public static HashMap<String, Font> loadedTFFFonts = new HashMap<>();

   public static void createResourcePack(CommandSender sender) {
      if (ForkUtil.isUsingFolia()) {
         LuxDialogues.getMorePaperLib().scheduling().asyncScheduler().run(() -> createResources(sender));
      } else {
         Bukkit.getScheduler().runTaskAsynchronously(LuxDialogues.getInstance(), () -> createResources(sender));
      }
   }

   public static void createResources(CommandSender sender) {
      if (sender instanceof Player playerSender) {
         playerSender.sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaResourcepack creation started."));
      }

      Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaResourcepack creation started."));
      ConfigurationSection soundSection = YamlUtil.get("Pack/Sounds/sounds.yml").getConfigurationSection("Sounds");
      clearAllSounds();
      saveResource("Pack/Widths/widths.json", "Pack/Widths/widths.json", false);
      saveResource(
         "Output/assets/luxdialogues/textures/luxdialogues/pixel.png",
         "Output/assets/" + DataUtil.packNamespace + "/textures/" + DataUtil.packNamespace + "/pixel.png",
         false
      );
      if (soundSection != null) {
         for (String sound : soundSection.getKeys(false)) {
            processSound(sound);
         }

         Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaSounds created."));
      }

      FileConfiguration lineFile = YamlUtil.get("Pack/Lines/lines.yml");
      int dialoguelineCount = lineFile.getInt("Dialogue-Lines.count");
      int dialoguelineAscent = lineFile.getInt("Dialogue-Lines.ascent");
      int dialoguelineSpace = lineFile.getInt("Dialogue-Lines.space");
      int answerlineCount = lineFile.getInt("Answer-Lines.count");
      int answerlineAscent = lineFile.getInt("Answer-Lines.ascent");
      int answerlineSpace = lineFile.getInt("Answer-Lines.space");
      int characternameAscent = lineFile.getInt("Character-Name.ascent");
      File fontDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/font");
      if (fontDir.exists()) {
         deleteDirectory(fontDir);
      }

      createDefaultFont();

      try {
         downloadFontTextures();
      } catch (IOException var15) {
         throw new RuntimeException(var15);
      }

      createFont(DataUtil.packNamespace + "_line_name", characternameAscent);

      for (int i = 1; i <= dialoguelineCount; i++) {
         int finalAscent = dialoguelineAscent - (i - 1) * dialoguelineSpace;
         createFont(DataUtil.packNamespace + "_line_" + i, finalAscent);
      }

      for (int i = 1; i <= answerlineCount; i++) {
         int finalAscent = answerlineAscent - (i - 1) * answerlineSpace;
         createFont(DataUtil.packNamespace + "_answer_" + i, finalAscent);
      }

      saveResource(
         "Output/assets/luxdialogues/textures/font/luxdialogues_font.png",
         "Output/assets/" + DataUtil.packNamespace + "/textures/font/" + DataUtil.packNamespace + "_font.png",
         true
      );
      saveResource(
         "Output/assets/luxdialogues/textures/font/luxdialogues_nonlatin.png",
         "Output/assets/" + DataUtil.packNamespace + "/textures/font/" + DataUtil.packNamespace + "_nonlatin.png",
         true
      );
      saveResource(
         "Output/assets/luxdialogues/textures/font/luxdialogues_accented.png",
         "Output/assets/" + DataUtil.packNamespace + "/textures/font/" + DataUtil.packNamespace + "_accented.png",
         true
      );
      Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaFonts created."));
      loadWidths();
      if (YamlUtil.get("config.yml").getBoolean("Output.mcmeta", true)) {
         createMeta();
      }

      if (YamlUtil.get("config.yml").getBoolean("Output.pack-png", true)) {
         saveResource("Output/pack.png", "Output/pack.png", false);
      }

      if (sender instanceof Player playerSender) {
         playerSender.sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaResourcePack created successfully."));
      }

      Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaResourcePack created successfully."));
      if (YamlUtil.get("config.yml").getBoolean("Output.save-to-directory", false)) {
         File outputDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output");
         File zipFile = new File(
            "plugins/" + YamlUtil.get("config.yml").getString("Output.directory"), YamlUtil.get("config.yml").getString("Output.file-name") + ".zip"
         );

         try {
            zipDirectory(outputDir, zipFile);
            Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaResourcePack Zip saved successfully."));
         } catch (IOException var14) {
            throw new RuntimeException(var14);
         }
      }
   }

   public static void createDefaultFont() {
      try {
         ConfigurationSection imageSection = YamlUtil.get("Pack/Images/images.yml").getConfigurationSection("Images");
         if (imageSection == null || imageSection.getKeys(false).isEmpty()) {
            return;
         }

         StringBuilder jsonBuilder = new StringBuilder();
         jsonBuilder.append("{\n    \"providers\": [\n");
         AtomicInteger imageCount = new AtomicInteger(0);
         AtomicInteger providerCounter = new AtomicInteger(0);

         for (String image : imageSection.getKeys(false)) {
            String imagePath = "Pack/Images/" + imageSection.getString(image + ".file");
            setImageFrame(imagePath);
            imageSizes.put(image, (int)(Math.ceil(getImageWidth(imagePath) / imageSection.getInt(image + ".reduction-ratio")) + 1.0));
            int height = 256 / imageSection.getInt(image + ".reduction-ratio");
            String filePath = DataUtil.packNamespace + ":" + DataUtil.packNamespace + "/" + imageSection.getString(image + ".file", "default.png");
            if (imageSection.getBoolean(image + ".is-arrow")) {
               FileConfiguration lineFile = YamlUtil.get("Pack/Lines/lines.yml");
               int answerlineCount = lineFile.getInt("Answer-Lines.count");
               int answerlineAscent = lineFile.getInt("Answer-Lines.ascent");
               int answerlineSpace = lineFile.getInt("Answer-Lines.space");

               for (int i = 1; i <= answerlineCount; i++) {
                  int finalAscent = answerlineAscent - (i - 1) * answerlineSpace + imageSection.getInt(image + ".ascent");
                  String finalUnicode = unicodeList.get(imageCount.getAndIncrement());
                  imageUnicodes.put(image + i, finalUnicode);
                  String providerEntry = "        {\n            \"type\": \"bitmap\",\n            \"file\": \""
                     + filePath
                     + "\",\n            \"ascent\": "
                     + finalAscent
                     + ",\n            \"height\": "
                     + height
                     + ",\n            \"chars\": [\""
                     + finalUnicode
                     + "\"]\n        }";
                  if (providerCounter.getAndIncrement() > 0) {
                     jsonBuilder.append(",\n");
                  }

                  jsonBuilder.append(providerEntry);
               }
            } else {
               int finalAscent = imageSection.getInt(image + ".ascent");
               String finalUnicode = unicodeList.get(imageCount.getAndIncrement());
               imageUnicodes.put(image, finalUnicode);
               String providerEntry = "        {\n            \"type\": \"bitmap\",\n            \"file\": \""
                  + filePath
                  + "\",\n            \"ascent\": "
                  + finalAscent
                  + ",\n            \"height\": "
                  + height
                  + ",\n            \"chars\": [\""
                  + finalUnicode
                  + "\"]\n        }";
               if (providerCounter.getAndIncrement() > 0) {
                  jsonBuilder.append(",\n");
               }

               jsonBuilder.append(providerEntry);
            }
         }

         String negativePix = "        {\n            \"type\": \"bitmap\",\n            \"file\": \""
            + DataUtil.packNamespace
            + ":"
            + DataUtil.packNamespace
            + "/pixel.png\",\n            \"ascent\": -2000,\n            \"height\": -3,\n            \"chars\": [\"七\"]\n        }";
         if (providerCounter.getAndIncrement() > 0) {
            jsonBuilder.append(",\n");
         }

         jsonBuilder.append(negativePix);
         String positivePix = "        {\n            \"type\": \"bitmap\",\n            \"file\": \""
            + DataUtil.packNamespace
            + ":"
            + DataUtil.packNamespace
            + "/pixel.png\",\n            \"ascent\": -2000,\n            \"height\": 0,\n            \"chars\": [\"\ud857\udcfe\"]\n        }";
         if (providerCounter.getAndIncrement() > 0) {
            jsonBuilder.append(",\n");
         }

         jsonBuilder.append(positivePix);
         jsonBuilder.append("\n    ]\n}");
         File outputDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/font");
         if (!outputDir.exists()) {
            outputDir.mkdirs();
         }

         File outputFile = new File(outputDir, DataUtil.packNamespace + "_default.json");

         try (FileWriter writer = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
            writer.write(jsonBuilder.toString());
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }
   }

   public static void downloadFontTextures() throws IOException {
      File destDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/textures/font/");
      if (!destDir.exists()) {
         destDir.mkdirs();
      }

      File tempZip = new File(destDir, "font.zip");

      try (InputStream in = openConnection("https://lobfile.com/file/JeGgWXmD.zip")) {
         Files.copy(in, tempZip.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }

      extractZip(tempZip);
      if (!tempZip.delete()) {
         System.err.println("ZIP remove failed.");
      }
   }

   private static InputStream openConnection(String fileUrl) throws IOException {
      URL url = new URL(fileUrl);
      HttpURLConnection conn = (HttpURLConnection)url.openConnection();
      conn.setInstanceFollowRedirects(true);
      return conn.getInputStream();
   }

   private static void extractZip(File zipFile) {
      try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
         FileConfiguration pages = YamlUtil.get("Pack/Fonts/pages.yml");
         byte[] buffer = new byte[1024];

         ZipEntry zipEntry;
         while ((zipEntry = zis.getNextEntry()) != null) {
            String fileName = Paths.get(zipEntry.getName()).getFileName().toString();
            String key = fileName.replace(".png", "");
            if (!pages.getBoolean(key, false)) {
               zis.closeEntry();
            } else {
               File newFile = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/textures/font/" + fileName);
               Bukkit.getConsoleSender().sendMessage("Output/assets/" + DataUtil.packNamespace + "/textures/font/" + fileName);
               if (zipEntry.isDirectory()) {
                  newFile.mkdirs();
               } else {
                  newFile.getParentFile().mkdirs();

                  int len;
                  try (FileOutputStream fos = new FileOutputStream(newFile)) {
                     while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                     }
                  }
               }

               zis.closeEntry();
            }
         }
      } catch (Exception var15) {
         var15.printStackTrace();
         System.err.println("Font textures extraction failed.");
      }
   }

   private static void zipDirectory(File folder, File zipFile) throws IOException {
      try (
         FileOutputStream fos = new FileOutputStream(zipFile);
         ZipOutputStream zos = new ZipOutputStream(fos);
      ) {
         File[] files = folder.listFiles();
         if (files != null) {
            for (File file : files) {
               zipFile(file, file.getName(), zos);
            }
         }
      }
   }

   private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws IOException {
      if (!fileToZip.isHidden()) {
         if (fileToZip.isDirectory()) {
            if (!fileName.endsWith("/")) {
               fileName = fileName + "/";
            }

            zos.putNextEntry(new ZipEntry(fileName));
            zos.closeEntry();
            File[] children = fileToZip.listFiles();
            if (children != null) {
               for (File childFile : children) {
                  zipFile(childFile, fileName + childFile.getName(), zos);
               }
            }
         } else {
            try (FileInputStream fis = new FileInputStream(fileToZip)) {
               ZipEntry zipEntry = new ZipEntry(fileName);
               zos.putNextEntry(zipEntry);
               byte[] bytes = new byte[1024];

               int length;
               while ((length = fis.read(bytes)) >= 0) {
                  zos.write(bytes, 0, length);
               }
            }
         }
      }
   }

   public static void createFont(String name, int ascent) {
      try {
         InputStream input = LuxDialogues.getInstance().getResource("Pack/Lines/example_font.json");
         if (input == null) {
            System.err.println("Font structure not found.");
            return;
         }

         String template;
         try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            template = reader.lines().collect(Collectors.joining("\n"));
         }

         Pattern pattern = Pattern.compile("\"ascent\"\\s*:\\s*(x(?:[+-]\\d+)?)");
         Matcher matcher = pattern.matcher(template);
         StringBuffer sb = new StringBuffer();

         while (matcher.find()) {
            String placeholder = matcher.group(1);
            int offset = 0;
            if (!placeholder.equals("x")) {
               offset = Integer.parseInt(placeholder.substring(1));
            }

            int calculatedAscent = ascent + offset;
            matcher.appendReplacement(sb, "\"ascent\": " + calculatedAscent);
         }

         matcher.appendTail(sb);
         JsonObject jsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
         if (jsonObject.has("providers")) {
            JsonArray providers = jsonObject.getAsJsonArray("providers");
            FileConfiguration pagesConfig = YamlUtil.get("Pack/Fonts/pages.yml");

            for (int i = providers.size() - 1; i >= 0; i--) {
               JsonObject provider = providers.get(i).getAsJsonObject();
               if ("bitmap".equals(provider.get("type").getAsString())) {
                  String filePath = provider.get("file").getAsString().replace("<namespace>", DataUtil.packNamespace);
                  provider.addProperty("file", filePath);
                  String pageName = extractPageName(filePath);
                  if (!pagesConfig.getBoolean(pageName, true)) {
                     providers.remove(i);
                  }
               }
            }
         }

         Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
         String finalJson = gson.toJson(jsonObject);
         File outputDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/font");
         if (!outputDir.exists()) {
            outputDir.mkdirs();
         }

         File outputFile = new File(outputDir, name + ".json");

         try (FileWriter writer = new FileWriter(outputFile, StandardCharsets.UTF_8)) {
            writer.write(finalJson);
         }
      } catch (Exception var19) {
         var19.printStackTrace();
      }
   }

   private static String extractPageName(String filePath) {
      int slashIndex = filePath.lastIndexOf(47);
      String fileName = slashIndex != -1 ? filePath.substring(slashIndex + 1) : filePath;
      if (fileName.endsWith(".png")) {
         fileName = fileName.substring(0, fileName.lastIndexOf(".png"));
      }

      return fileName;
   }

   public static void loadTFF(String key, String filePath) {
      try {
         Font font = Font.createFont(0, new File(LuxDialogues.getInstance().getDataFolder(), "Pack/Fonts/" + filePath)).deriveFont(9.0F);
         loadedTFFFonts.put(key, font);
      } catch (FontFormatException var3) {
         throw new RuntimeException(var3);
      } catch (IOException var4) {
         throw new RuntimeException(var4);
      }
   }

   private static void processSound(String sound) {
      String soundPath = YamlUtil.get("Pack/Sounds/sounds.yml").getString("Sounds." + sound + ".file");
      if (soundPath != null && soundPath.endsWith(".ogg")) {
         File sourceFile = new File(LuxDialogues.getInstance().getDataFolder(), "Pack/Sounds/" + soundPath);
         File destinationDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/sounds");
         File destinationFile = new File(destinationDir, sound + ".ogg");
         if (!sourceFile.exists()) {
            System.out.println("Audio file not found: " + sourceFile.getAbsolutePath());
         } else {
            if (!destinationDir.exists()) {
               destinationDir.mkdirs();
            }

            try {
               Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
               String soundKey = DataUtil.packNamespace + ".sounds." + sound;
               File jsonFile = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/sounds.json");
               JsonObject soundsJson = new JsonObject();
               if (jsonFile.exists()) {
                  try (FileReader reader = new FileReader(jsonFile)) {
                     soundsJson = (JsonObject)new Gson().fromJson(reader, JsonObject.class);
                  } catch (IOException var18) {
                     System.out.println("Error reading JSON file: " + jsonFile.getAbsolutePath());
                     var18.printStackTrace();
                     return;
                  }
               }

               JsonObject soundObject = new JsonObject();
               JsonArray soundArray = new JsonArray();
               soundArray.add(DataUtil.packNamespace + ":" + sound);
               soundObject.add("sounds", soundArray);
               soundsJson.add(soundKey, soundObject);

               try (FileWriter writer = new FileWriter(jsonFile)) {
                  new Gson().toJson(soundsJson, writer);
               } catch (IOException var16) {
                  System.out.println("Error occurred while processing audio file: " + jsonFile.getAbsolutePath());
                  var16.printStackTrace();
               }
            } catch (IOException var19) {
               System.out.println("Error occurred while processing audio file: " + sourceFile.getAbsolutePath());
               var19.printStackTrace();
            }
         }
      } else {
         System.out.println("Audio file not found: " + sound);
      }
   }

   public static void clearAllSounds() {
      File soundsDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/sounds");
      if (soundsDir.exists()) {
         deleteDirectory(soundsDir);
      }

      soundsDir.mkdirs();
      File jsonFile = new File(LuxDialogues.getInstance().getDataFolder(), "/assets/" + DataUtil.packNamespace + "/sounds.json");
      if (jsonFile.exists()) {
         jsonFile.delete();
      }
   }

   public static void setImageFrame(String inputPath) {
      try {
         File inputFile = new File(LuxDialogues.getInstance().getDataFolder(), inputPath);
         BufferedImage originalImage = ImageIO.read(inputFile);
         BufferedImage resizedImage = new BufferedImage(256, 256, 2);
         Graphics2D g2d = resizedImage.createGraphics();
         g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
         g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
         g2d.setComposite(AlphaComposite.Clear);
         g2d.fillRect(0, 0, 256, 256);
         g2d.setComposite(AlphaComposite.Src);
         g2d.drawImage(originalImage, 0, 0, null);
         g2d.dispose();
         File outputDir = new File(
            LuxDialogues.getInstance().getDataFolder(), "Output/assets/" + DataUtil.packNamespace + "/textures/" + DataUtil.packNamespace
         );
         if (!outputDir.exists()) {
            outputDir.mkdirs();
         }

         String outputFileName = inputFile.getName();
         File outputFile = new File(outputDir, outputFileName);
         ImageIO.write(resizedImage, "PNG", outputFile);
      } catch (IOException var8) {
         var8.printStackTrace();
      }
   }

   public static int getImageWidth(String inputImagePath) {
      try {
         File inputFile = new File(LuxDialogues.getInstance().getDataFolder(), inputImagePath);
         BufferedImage originalImage = ImageIO.read(inputFile);
         return originalImage.getWidth();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static int getImageHeight(String inputImagePath) {
      try {
         File inputFile = new File(LuxDialogues.getInstance().getDataFolder(), inputImagePath);
         BufferedImage originalImage = ImageIO.read(inputFile);
         return originalImage.getHeight();
      } catch (IOException var3) {
         throw new RuntimeException(var3);
      }
   }

   public static void createMeta() {
      File outputDir = new File(LuxDialogues.getInstance().getDataFolder(), "Output");
      if (!outputDir.exists()) {
         outputDir.mkdirs();
      }

      String filePath = "Output/pack.mcmeta";
      JsonObject packObject = JsonUtil.get(filePath);
      if (packObject == null || !packObject.has("pack")) {
         packObject = new JsonObject();
      }

      String version = LuxDialogues.getInstance().getDescription().getVersion();
      JsonObject packInfo = packObject.has("pack") ? packObject.getAsJsonObject("pack") : new JsonObject();
      packInfo.addProperty("pack_format", 34);
      packInfo.addProperty("supported_formats", "[13,32767]");
      packInfo.addProperty("description", "LuxDialogues v" + version);
      packObject.add("pack", packInfo);
      JsonUtil.save(filePath, packObject);
      Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("#327fbaLuxDialogues &7- #41aafaMeta created."));
   }

   public static void loadWidths() {
      File bitmapsDir = new File(LuxDialogues.getInstance().getDataFolder(), "Pack/Widths/");
      File widthsFile = new File(bitmapsDir, "widths.json");

      try (FileReader reader = new FileReader(widthsFile)) {
         JsonObject obj = (JsonObject)new Gson().fromJson(reader, JsonObject.class);

         for (Entry<String, JsonElement> entry : obj.entrySet()) {
            String hexKey = entry.getKey();
            JsonElement value = entry.getValue();
            if (value.isJsonPrimitive() && value.getAsJsonPrimitive().isNumber()) {
               float width = value.getAsFloat();
               if (!hexKey.contains("US") && width != 131.0F) {
                  try {
                     int codePoint = Integer.parseInt(hexKey, 16);
                     char ch = (char)codePoint;
                     WidthUtil.widthMap2.put(String.valueOf(ch), width);
                  } catch (NumberFormatException var12) {
                     Bukkit.getLogger().severe("Unknown hex value: " + hexKey);
                  }
               }
            }
         }
      } catch (IOException var14) {
         Bukkit.getLogger().severe("Failed to read width json: " + var14.getMessage());
      }

      if (YamlUtil.get("Pack/Images/images.yml").contains("Images")
         && YamlUtil.get("Pack/Images/images.yml").getConfigurationSection("Images") != null
         && !YamlUtil.get("Pack/Images/images.yml").getConfigurationSection("Images").getKeys(false).isEmpty()) {
         ConfigurationSection imageSection = YamlUtil.get("Pack/Images/images.yml").getConfigurationSection("Images");

         for (String image : imageSection.getKeys(false)) {
            String imagePath = "Pack/Images/" + imageSection.getString(image + ".file");
            imageSizes.put(image, (int)(Math.ceil(getImageWidth(imagePath) / imageSection.getInt(image + ".reduction-ratio")) + 1.0));
         }
      }
   }

   public static void saveResource(String inputPath, String outputPath, boolean replace) {
      File outFile = new File(LuxDialogues.getInstance().getDataFolder(), outputPath);
      if (!outFile.exists() || replace) {
         File parentDir = outFile.getParentFile();
         if (!parentDir.exists()) {
            parentDir.mkdirs();
         }

         try (
            InputStream in = LuxDialogues.getInstance().getResource(inputPath);
            OutputStream out = new FileOutputStream(outFile);
         ) {
            if (in == null) {
               throw new IllegalArgumentException("Resource " + inputPath + " not found in JAR.");
            }

            byte[] buffer = new byte[1024];

            int length;
            while ((length = in.read(buffer)) > 0) {
               out.write(buffer, 0, length);
            }
         } catch (IOException var13) {
            var13.printStackTrace();
         }
      }
   }

   private static void deleteDirectory(File dir) {
      if (dir.isDirectory()) {
         String[] entries = dir.list();
         if (entries != null) {
            for (String entry : entries) {
               File currentFile = new File(dir, entry);
               deleteDirectory(currentFile);
            }
         }
      }

      dir.delete();
   }
}
