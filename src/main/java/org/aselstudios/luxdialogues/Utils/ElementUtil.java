package org.aselstudios.luxdialogues.Utils;

import java.util.ArrayList;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialoguesapi.Builders.Answer;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;

public class ElementUtil {
   public static String formatAnswer(Player player, Dialogue dialogue, Answer answer, int answerCount) {
      return dialogue.getAnswerNumbers() != null && dialogue.getAnswerNumbers()
         ? ProcessUtil.processPlaceholder(player, LuxDialogues.getLang().getString("Answer-Numbers." + answerCount) + answer.getAnswerText())
         : ProcessUtil.processPlaceholder(player, answer.getAnswerText());
   }

   public static String formatCharacterName(Dialogue dialogue, Player player) {
      if (dialogue.getCharacterNameText() == null) {
         return "";
      } else {
         String rawName = PlaceholderAPI.setPlaceholders(player, PlaceholderAPI.setBracketPlaceholders(player, dialogue.getCharacterNameText()));
         int nameOffset = dialogue.getCharacterNameTextOffset();
         int nameBackgroundOffset = dialogue.getNameBackgroundImageOffset();
         String nameColor = dialogue.getCharacterNameTextColor();
         String nameBGColor = dialogue.getNameImageColor();
         StringBuilder nameBuilder = new StringBuilder();
         nameBuilder.append(getOffset(nameBackgroundOffset));
         String nameBackground = "<color:"
            + nameBGColor
            + ">"
            + ResourceUtil.imageUnicodes.get(dialogue.getNameStartImage())
            + getOffset(-1.0F)
            + (ResourceUtil.imageUnicodes.get(dialogue.getNameMidImage()) + getOffset(-1.0F)).repeat((int)(WidthUtil.getWidth(rawName) / 2.0F))
            + ResourceUtil.imageUnicodes.get(dialogue.getNameEndImage())
            + getOffset(-1.0F)
            + "</color:"
            + nameBGColor
            + ">"
            + getOffset(
               (float)(
                  (
                           ResourceUtil.imageSizes.get(dialogue.getNameStartImage()) - 1
                              + (ResourceUtil.imageSizes.get(dialogue.getNameMidImage()) - 1) * Math.floor(WidthUtil.getWidth(rawName) / 2.0F)
                              + ResourceUtil.imageSizes.get(dialogue.getNameEndImage()).intValue()
                              - 1.0
                        )
                        * -1.0
                     + 3.0
               )
            );
         nameBuilder.append(nameBackground);
         nameBuilder.append(getOffset(nameOffset));

         for (char c : rawName.toCharArray()) {
            nameBuilder.append("<font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_name>")
               .append("<color:" + nameColor + ">")
               .append(c)
               .append("</color:" + nameColor + ">")
               .append("</font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_name>");
         }

         nameBuilder.append(getOffset(nameOffset * -1));
         nameBuilder.append(getOffset(WidthUtil.getWidth(rawName) * -1.0F - 3.0F));
         nameBuilder.append(getOffset(nameBackgroundOffset * -1));
         return nameBuilder.toString();
      }
   }

   public static String formatChar(Character character, int line) {
      StringBuilder builder = new StringBuilder();
      builder.append("<font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_")
         .append(line)
         .append(">")
         .append(character)
         .append("</font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_")
         .append(line)
         .append(">");
      return builder.toString();
   }

   public static List<Character> toCharacterList(String text) {
      List<Character> charList = new ArrayList<>();

      for (char c : text.toCharArray()) {
         charList.add(c);
      }

      return charList;
   }

   public static String getOffset(float lineOff) {
      if (lineOff == 0.0F) {
         return "";
      } else {
         String symbol = lineOff < 0.0F ? "七" : "\ud857\udcfe";
         StringBuilder result = new StringBuilder(symbol.repeat((int)Math.ceil(Math.abs(lineOff))));
         return result.toString();
      }
   }

   public Boolean getTimerResult(Player player, Dialogue dialogue) {
      return true;
   }
}
