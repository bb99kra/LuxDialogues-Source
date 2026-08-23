package org.aselstudios.luxdialogues.Utils;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.bukkit.entity.Player;

public class AdventureUtil {
   public static Boolean SHADOW_SUPPORTED = false;

   public static Component getComponentFromMiniMessage(String text) {
      return MiniMessage.miniMessage().deserialize(replaceLegacy(text));
   }

   public static void playerActionbar(Player player, String s) {
      Audience au = LuxDialogues.getAdventure().player(player);
      au.sendActionBar(getComponentFromMiniMessage(s));
   }

   public static void checkARGBLikeClass() {
      try {
         Class<?> argbClass = Class.forName("net.kyori.adventure.util.ARGBLike");
         Component.class.getMethod("shadowColor", argbClass);
         SHADOW_SUPPORTED = true;
      } catch (Exception var1) {
         SHADOW_SUPPORTED = false;
      }
   }

   public static Component applyShadowColor(Component comp, int r, int g, int b, int a) {
      if (!SHADOW_SUPPORTED) return comp;
      try {
         Class<?> shadowColorClass = Class.forName("net.kyori.adventure.text.format.ShadowColor");
         java.lang.reflect.Method shadowColorMethod = shadowColorClass.getMethod("shadowColor", int.class, int.class, int.class, int.class);
         Object shadowColorObj = shadowColorMethod.invoke(null, r, g, b, a);
         Class<?> argbClass = Class.forName("net.kyori.adventure.util.ARGBLike");
         java.lang.reflect.Method setShadowMethod = Component.class.getMethod("shadowColor", argbClass);
         return (Component) setShadowMethod.invoke(comp, shadowColorObj);
      } catch (Exception ignored) {
         return comp;
      }
   }

   public static String replaceLegacy(String legacy) {
      StringBuilder stringBuilder = new StringBuilder();
      char[] chars = legacy.toCharArray();

      for (int i = 0; i < chars.length; i++) {
         if (isColorCode(chars[i])) {
            if (i + 1 < chars.length) {
               switch (chars[i + 1]) {
                  case '0':
                     stringBuilder.append("<black>");
                     break;
                  case '1':
                     stringBuilder.append("<dark_blue>");
                     break;
                  case '2':
                     stringBuilder.append("<dark_green>");
                     break;
                  case '3':
                     stringBuilder.append("<dark_aqua>");
                     break;
                  case '4':
                     stringBuilder.append("<dark_red>");
                     break;
                  case '5':
                     stringBuilder.append("<dark_purple>");
                     break;
                  case '6':
                     stringBuilder.append("<gold>");
                     break;
                  case '7':
                     stringBuilder.append("<gray>");
                     break;
                  case '8':
                     stringBuilder.append("<dark_gray>");
                     break;
                  case '9':
                     stringBuilder.append("<blue>");
                     break;
                  case ':':
                  case ';':
                  case '<':
                  case '=':
                  case '>':
                  case '?':
                  case '@':
                  case 'A':
                  case 'B':
                  case 'C':
                  case 'D':
                  case 'E':
                  case 'F':
                  case 'G':
                  case 'H':
                  case 'I':
                  case 'J':
                  case 'K':
                  case 'L':
                  case 'M':
                  case 'N':
                  case 'O':
                  case 'P':
                  case 'Q':
                  case 'R':
                  case 'S':
                  case 'T':
                  case 'U':
                  case 'V':
                  case 'W':
                  case 'X':
                  case 'Y':
                  case 'Z':
                  case '[':
                  case '\\':
                  case ']':
                  case '^':
                  case '_':
                  case '`':
                  case 'g':
                  case 'h':
                  case 'i':
                  case 'j':
                  case 'p':
                  case 'q':
                  case 's':
                  case 't':
                  case 'u':
                  case 'v':
                  case 'w':
                  default:
                     stringBuilder.append(chars[i]);
                     continue;
                  case 'a':
                     stringBuilder.append("<green>");
                     break;
                  case 'b':
                     stringBuilder.append("<aqua>");
                     break;
                  case 'c':
                     stringBuilder.append("<red>");
                     break;
                  case 'd':
                     stringBuilder.append("<light_purple>");
                     break;
                  case 'e':
                     stringBuilder.append("<yellow>");
                     break;
                  case 'f':
                     stringBuilder.append("<white>");
                     break;
                  case 'k':
                     stringBuilder.append("<obfuscated>");
                     break;
                  case 'l':
                     stringBuilder.append("<bold>");
                     break;
                  case 'm':
                     stringBuilder.append("<strikethrough>");
                     break;
                  case 'n':
                     stringBuilder.append("<underlined>");
                     break;
                  case 'o':
                     stringBuilder.append("<italic>");
                     break;
                  case 'r':
                     stringBuilder.append("<reset><!italic>");
                     break;
                  case 'x':
                     if (i + 13 >= chars.length
                        || !isColorCode(chars[i + 2])
                        || !isColorCode(chars[i + 4])
                        || !isColorCode(chars[i + 6])
                        || !isColorCode(chars[i + 8])
                        || !isColorCode(chars[i + 10])
                        || !isColorCode(chars[i + 12])) {
                        stringBuilder.append(chars[i]);
                        continue;
                     }

                     stringBuilder.append("<#")
                        .append(chars[i + 3])
                        .append(chars[i + 5])
                        .append(chars[i + 7])
                        .append(chars[i + 9])
                        .append(chars[i + 11])
                        .append(chars[i + 13])
                        .append(">");
                     i += 13;
               }

               i++;
            } else {
               stringBuilder.append(chars[i]);
            }
         } else {
            stringBuilder.append(chars[i]);
         }
      }

      return stringBuilder.toString();
   }

   private static boolean isColorCode(char c) {
      return c == 167 || c == '&';
   }
}
