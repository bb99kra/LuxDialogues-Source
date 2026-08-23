package org.aselstudios.luxdialogues.Utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.aselstudios.luxdialogues.LuxDialogues;

public class UpdateUtil {
   private static final String VERSION_URL = "https://raw.githubusercontent.com/zKillerPTVI/LuxDialogues/main/version.txt";

   public static void checkUpdate() {
      new Thread(() -> {
         try {
            URL url = new URL("https://raw.githubusercontent.com/zKillerPTVI/LuxDialogues/main/version.txt");
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String versionLine = reader.readLine();
            reader.close();
            if (versionLine != null && versionLine.startsWith("Version:")) {
               String latestVersion = versionLine.split(":")[1].trim();
               String currentVersion = LuxDialogues.getInstance().getDescription().getVersion();
               if (!currentVersion.equals(latestVersion)) {
                  MessageUtil.sendConsole("&4LuxDialogues &7- &cA new version is available!");
               } else {
                  MessageUtil.sendConsole("&2LuxDialogues &7- &aPlugin is up to date.");
               }
            }
         } catch (Exception var6) {
            MessageUtil.sendConsole("&4LuxDialogues &7- &cAn error occurred while checking the version!");
         }
      }).start();
   }
}
