package org.aselstudios.luxdialogues.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.entity.Player;

public class GoToUtil {
   private static final Pattern CONDITION_PATTERN = Pattern.compile("@condition\\.([^\\s]+)");

   public static String resolveGoto(Player player, List<String> gotoList) {
      for (String entry : gotoList) {
         Matcher matcher = CONDITION_PATTERN.matcher(entry);
         boolean conditionOK = true;
         if (matcher.find()) {
            String conditionName = matcher.group(1);
            conditionOK = ConditionUtil.areConditionsTrue(player, conditionName);
         }

         if (conditionOK) {
            String cleaned = entry.replaceAll("@condition\\.([^\\s]+)", "").trim();
            if (!cleaned.isEmpty()) {
               return cleaned;
            }
         }
      }

      return null;
   }
}
