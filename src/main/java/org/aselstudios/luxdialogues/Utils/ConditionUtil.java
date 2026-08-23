package org.aselstudios.luxdialogues.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

public class ConditionUtil {
   public static boolean areConditionsTrue(Player player, Object conditionInput) {
      if (conditionInput == null) {
         return false;
      } else if (conditionInput instanceof List<?> rawList) {
         if (rawList.isEmpty()) {
            return false;
         } else {
            for (Object o : rawList) {
               if (!areConditionsTrue(player, o.toString())) {
                  return false;
               }
            }

            return true;
         }
      } else {
         String condition = conditionInput.toString().trim();
         if (condition.isEmpty()) {
            return false;
         } else {
            condition = condition.replace(" ", "");
            String opRegex = "(==|!=|<=|>=|<|>)";
            Pattern pattern = Pattern.compile("^(.+?)" + opRegex + "(.+)$");
            Matcher matcher = pattern.matcher(condition);
            if (!matcher.matches()) {
               return false;
            } else {
               String left = matcher.group(1);
               String op = matcher.group(2);
               String right = matcher.group(3);
               if (left.startsWith(":") && left.endsWith(":")) {
                  String permission = left.substring(1, left.length() - 1);
                  boolean hasPermission = player.hasPermission(permission);
                  boolean expected = Boolean.parseBoolean(right);
                  switch (op) {
                     case "==":
                        return hasPermission == expected;
                     case "!=":
                        return hasPermission != expected;
                     default:
                        return false;
                  }
               } else if (left.startsWith("%") && left.endsWith("%")) {
                  String leftPlaceholder = left.substring(1, left.length() - 1);
                  String leftValue = PlaceholderAPI.setPlaceholders(player, "%" + leftPlaceholder + "%");
                  if (right.startsWith("%") && right.endsWith("%")) {
                     String rightPlaceholder = right.substring(1, right.length() - 1);
                     right = PlaceholderAPI.setPlaceholders(player, "%" + rightPlaceholder + "%");
                  }

                  try {
                     double actual = Double.parseDouble(leftValue);
                     double expected = Double.parseDouble(right);
                     switch (op) {
                        case "==":
                           return actual == expected;
                        case "!=":
                           return actual != expected;
                        case "<=":
                           return actual <= expected;
                        case ">=":
                           return actual >= expected;
                        case "<":
                           return actual < expected;
                        case ">":
                           return actual > expected;
                        default:
                           return false;
                     }
                  } catch (NumberFormatException var17) {
                     switch (op) {
                        case "==":
                           return leftValue.equals(right);
                        case "!=":
                           return !leftValue.equals(right);
                        default:
                           return false;
                     }
                  }
               } else {
                  return false;
               }
            }
         }
      }
   }
}
