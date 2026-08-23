package org.aselstudios.luxdialogues.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientUtil {
   public static List<String> expandGradients(List<String> lines) {
      List<String> result = new ArrayList<>();
      Pattern pattern = Pattern.compile("<gradient:(#[0-9A-Fa-f]{6}):(#[0-9A-Fa-f]{6})>(.*?)</gradient>");

      for (String line : lines) {
         Matcher matcher = pattern.matcher(line);
         StringBuffer sb = new StringBuffer();

         while (matcher.find()) {
            String startHex = matcher.group(1);
            String endHex = matcher.group(2);
            String text = matcher.group(3);
            List<String> colors = interpolateColors(startHex, endHex, text.length());
            StringBuilder expanded = new StringBuilder();

            for (int i = 0; i < text.length(); i++) {
               expanded.append(colors.get(i)).append(text.charAt(i));
            }

            matcher.appendReplacement(sb, Matcher.quoteReplacement(expanded.toString()));
         }

         matcher.appendTail(sb);
         result.add(sb.toString());
      }

      return result;
   }

   public static String expandGradientLine(String line) {
      Pattern pattern = Pattern.compile("<gradient:(#[0-9A-Fa-f]{6}):(#[0-9A-Fa-f]{6})>(.*?)</gradient>");
      Matcher matcher = pattern.matcher(line);
      StringBuffer sb = new StringBuffer();

      while (matcher.find()) {
         String startHex = matcher.group(1);
         String endHex = matcher.group(2);
         String text = matcher.group(3);
         List<String> colors = interpolateColors(startHex, endHex, text.length());
         StringBuilder expanded = new StringBuilder();

         for (int i = 0; i < text.length(); i++) {
            expanded.append(colors.get(i)).append(text.charAt(i));
         }

         matcher.appendReplacement(sb, Matcher.quoteReplacement(expanded.toString()));
      }

      matcher.appendTail(sb);
      return sb.toString();
   }

   private static List<String> interpolateColors(String startHex, String endHex, int steps) {
      List<String> result = new ArrayList<>();
      int sr = Integer.parseInt(startHex.substring(1, 3), 16);
      int sg = Integer.parseInt(startHex.substring(3, 5), 16);
      int sb = Integer.parseInt(startHex.substring(5, 7), 16);
      int er = Integer.parseInt(endHex.substring(1, 3), 16);
      int eg = Integer.parseInt(endHex.substring(3, 5), 16);
      int eb = Integer.parseInt(endHex.substring(5, 7), 16);

      for (int i = 0; i < steps; i++) {
         float ratio = steps == 1 ? 0.0F : (float)i / (steps - 1);
         int r = (int)(sr + (er - sr) * ratio);
         int g = (int)(sg + (eg - sg) * ratio);
         int b = (int)(sb + (eb - sb) * ratio);
         result.add(String.format("#%02x%02x%02x", r, g, b));
      }

      return result;
   }
}
