package org.aselstudios.luxdialogues.Dialogues;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import net.momirealms.customnameplates.api.CNPlayer;
import net.momirealms.customnameplates.api.CustomNameplates;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Listeners.RangeListener;
import org.aselstudios.luxdialogues.Utils.BarUtil;
import org.aselstudios.luxdialogues.Utils.ColorUtil;
import org.aselstudios.luxdialogues.Utils.ConditionUtil;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.ElementUtil;
import org.aselstudios.luxdialogues.Utils.ExecuteUtil;
import org.aselstudios.luxdialogues.Utils.GoToUtil;
import org.aselstudios.luxdialogues.Utils.ProcessUtil;
import org.aselstudios.luxdialogues.Utils.ReplyUtil;
import org.aselstudios.luxdialogues.Utils.ResourceUtil;
import org.aselstudios.luxdialogues.Utils.WidthUtil;
import org.aselstudios.luxdialogues.Utils.YamlUtil;
import org.aselstudios.luxdialoguesapi.Builders.Answer;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.aselstudios.luxdialoguesapi.Builders.DialogueCallback;
import org.aselstudios.luxdialoguesapi.Builders.Page;
import org.aselstudios.luxdialoguesapi.Events.DialogueAnsweredEvent;
import org.aselstudios.luxdialoguesapi.Events.DialogueStartEvent;
import org.aselstudios.luxdialoguesapi.Events.DialogueStopEvent;
import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class SpigotDialogue implements DialogueSender {
   @Override
   public void sendDialogue(Player player, Dialogue dialogue, String pageID) {
      if (checkDialogue(dialogue)) {
         Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> Bukkit.getPluginManager().callEvent(new DialogueStartEvent(player, dialogue)));
         DataUtil.setPlayerDialogue(player, dialogue, dialogue.getPages().get(pageID));
         RangeListener.startRangeChecker(player.getLocation(), player, dialogue);
         if (YamlUtil.get("config.yml").getBoolean("Hooks.CustomNameplates")) {
            CNPlayer cnPlayer = CustomNameplates.getInstance().getPlayer(player.getUniqueId());
            cnPlayer.acquireActionBar("LuxDialogues");
         }

         startPage(player, dialogue, pageID);
         player.setVelocity(new Vector(0, 0, 0));
         if (dialogue.getEffect() != null && !dialogue.getEffect().equalsIgnoreCase("none")) {
            if (dialogue.getEffect().equalsIgnoreCase("Freeze")) {
               Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> {
                  player.setWalkSpeed(0.0F);
                  player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, Integer.MAX_VALUE, 255, true, false, false));
               });
            }

            if (dialogue.getEffect().equalsIgnoreCase("Slowness")) {
               Bukkit.getScheduler()
                  .runTask(
                     LuxDialogues.getInstance(),
                     () -> player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Integer.MAX_VALUE, 1, true, false, false))
                  );
            }
         }
      }
   }

   @Override
   public void sendDialogue(Player player, String dialogueID, String pageID) {
      Dialogue dialogue = DataUtil.getDialogue(dialogueID);
      if (checkDialogue(dialogue)) {
         Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> Bukkit.getPluginManager().callEvent(new DialogueStartEvent(player, dialogue)));
         DataUtil.setPlayerDialogue(player, dialogue, dialogue.getPages().get(pageID));
         RangeListener.startRangeChecker(player.getLocation(), player, dialogue);
         if (YamlUtil.get("config.yml").getBoolean("Hooks.CustomNameplates")) {
            CNPlayer cnPlayer = CustomNameplates.getInstance().getPlayer(player.getUniqueId());
            cnPlayer.acquireActionBar("LuxDialogues");
         }

         startPage(player, dialogue, pageID);
         player.setVelocity(new Vector(0, 0, 0));
         if (dialogue.getEffect() != null && !dialogue.getEffect().equalsIgnoreCase("none")) {
            if (dialogue.getEffect().equalsIgnoreCase("Freeze")) {
               Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> {
                  player.setWalkSpeed(0.0F);
                  player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, Integer.MAX_VALUE, 255, true, false, false));
               });
            }

            if (dialogue.getEffect().equalsIgnoreCase("Slowness")) {
               Bukkit.getScheduler()
                  .runTask(
                     LuxDialogues.getInstance(),
                     () -> player.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, Integer.MAX_VALUE, 1, true, false, false))
                  );
            }
         }
      }
   }

   public static Boolean checkDialogue(Dialogue dialogue) {
      StringBuilder invalid = new StringBuilder();
      if (dialogue.getDialogueBackgroundImage() != null && ResourceUtil.imageSizes.get(dialogue.getDialogueBackgroundImage()) == null) {
         invalid.append("DialogueBackgroundImage, ");
      }

      if (dialogue.getFogImage() != null && ResourceUtil.imageSizes.get(dialogue.getFogImage()) == null) {
         invalid.append("FogImage, ");
      }

      if (dialogue.getCharacterImage() != null && ResourceUtil.imageSizes.get(dialogue.getCharacterImage()) == null) {
         invalid.append("DialogueBackgroundImage, ");
      }

      if (dialogue.getCharacterImage() != null && ResourceUtil.imageSizes.get(dialogue.getCharacterImage()) == null) {
         invalid.append("CharacterImage, ");
      }

      if (dialogue.getArrowImage() != null && ResourceUtil.imageSizes.get(dialogue.getArrowImage()) == null) {
         invalid.append("ArrowImage, ");
      }

      if (dialogue.getCharacterNameText() != null
         && !dialogue.getCharacterNameText().isEmpty()
         && dialogue.getNameStartImage() != null
         && ResourceUtil.imageSizes.get(dialogue.getNameStartImage()) == null) {
         invalid.append("NameStartImage, ");
      }

      if (dialogue.getCharacterNameText() != null
         && !dialogue.getCharacterNameText().isEmpty()
         && dialogue.getNameMidImage() != null
         && ResourceUtil.imageSizes.get(dialogue.getNameMidImage()) == null) {
         invalid.append("NameMidImage, ");
      }

      if (dialogue.getCharacterNameText() != null
         && !dialogue.getCharacterNameText().isEmpty()
         && dialogue.getNameEndImage() != null
         && ResourceUtil.imageSizes.get(dialogue.getNameEndImage()) == null) {
         invalid.append("NameEndImage, ");
      }

      if (!invalid.isEmpty()) {
         System.out.println();
         Bukkit.getConsoleSender()
            .sendMessage(
               ColorUtil.colorText(
                  "&4LuxDialogues &7- &cThe specified images are not defined in images.yml! Invalid images: " + invalid.substring(0, invalid.length() - 2)
               )
            );
         return false;
      } else if (dialogue.getEffect() != null && !dialogue.getEffect().equalsIgnoreCase("Freeze") && !dialogue.getEffect().equalsIgnoreCase("Slowness")) {
         Bukkit.getConsoleSender().sendMessage(ColorUtil.colorText("&4LuxDialogues &7- &cThe effect type is invalid! Use Slowness or Freeze."));
         return false;
      } else {
         return true;
      }
   }

   public static void startPage(Player player, Dialogue dialogue, String pageID) {
      Page page = dialogue.getPages().get(pageID);
      DataUtil.setDialogueStatus(player, true);
      int dialogueSpeed = dialogue.getDialogueSpeed();
      String typingSound = dialogue.getTypingSound();
      String typingSoundSource = dialogue.getTypingSoundSource();
      double typingSoundVolume = dialogue.getTypingSoundVolume();
      double typingSoundPitch = dialogue.getTypingSoundPitch();
      List<String> rawLines = ProcessUtil.processPlaceholderList(player, page.getLines());
      List<String> dialogueLines = ProcessUtil.removeHexCodes(ProcessUtil.processPlaceholderList(player, page.getLines()));
      List<String> hexPositions = ProcessUtil.processHexPositions(rawLines);
      int dialogueOffset = dialogue.getDialogueBackgroundImageOffset();
      int lineOffset = dialogue.getDialogueTextOffset();
      int dialogueSize = ResourceUtil.imageSizes.get(dialogue.getDialogueBackgroundImage());
      String dialogueColor = dialogue.getDialogueTextColor();
      String dialogueBGColor = dialogue.getDialogueBackgroundImageColor();
      String characterAvatar = "";
      if (dialogue.getCharacterImage() != null) {
         int characterOffset = dialogue.getCharacterImageOffset();
         int characterSize = ResourceUtil.imageSizes.get(dialogue.getCharacterImage());
         characterAvatar = "<color:"
            + dialogue.getCharacterImageColor()
            + ">"
            + ElementUtil.getOffset(characterOffset)
            + ResourceUtil.imageUnicodes.get(dialogue.getCharacterImage())
            + ElementUtil.getOffset(characterOffset * -1 + characterSize * -1)
            + "</color:"
            + dialogue.getCharacterImageColor()
            + ">";
      }

      String dialogueBackground = "<color:"
         + dialogueBGColor
         + ">"
         + ElementUtil.getOffset(dialogueOffset + dialogueSize * -1)
         + ResourceUtil.imageUnicodes.get(dialogue.getDialogueBackgroundImage())
         + ElementUtil.getOffset(dialogueOffset * -1 + dialogueSize * -1)
         + "</color:"
         + dialogueBGColor
         + ">";
      String characterName = ElementUtil.formatCharacterName(dialogue, player);
      AtomicInteger lineCounter = new AtomicInteger(1);
      AtomicInteger charCounter = new AtomicInteger(1);
      int totalLine = rawLines.size();
      String lineStart = dialogueBackground + characterName + characterAvatar + ElementUtil.getOffset(lineOffset) + "<color:" + dialogueColor + ">";
      StringBuilder updatedText = new StringBuilder();
      StringBuilder lastLine = new StringBuilder();
      updatedText.append(lineStart);
      if (page.getPreCallbacks() != null && !page.getPreCallbacks().isEmpty()) {
         for (DialogueCallback callback : page.getPreCallbacks()) {
            callback.execute(player);
         }
      }

      if (page.getPreActions() != null && !page.getPreActions().isEmpty()) {
         for (String action : page.getPreActions()) {
            ExecuteUtil.executeAction(player, action);
         }
      }

      int taskID = Bukkit.getScheduler()
         .runTaskTimerAsynchronously(
            LuxDialogues.getInstance(),
            () -> {
               if (!player.isOnline()) {
                  LuxDialogues.getDialogueSender().clearDialogue(player);
               } else if (lineCounter.get() > totalLine) {
                  LuxDialogues.getTaskData().cancelPlayerTask(player);
                  DataUtil.setDialogueStatus(player, false);
                  LuxDialogues.getDialogueSender().nextPage(player, dialogue, pageID);
               } else if (charCounter.get() > rawLines.get(lineCounter.get() - 1).length()) {
                  lastLine.setLength(0);
                  charCounter.set(1);
                  updatedText.append(ElementUtil.getOffset(WidthUtil.getWidth(dialogueLines.get(lineCounter.get() - 1)) * -1.0F));
                  updatedText.append("</color:" + dialogueColor + "><color:" + dialogueColor + ">");
                  lineCounter.getAndIncrement();
               } else {
                  if (hexPositions.contains(lineCounter.get() + ";" + charCounter.get())) {
                     int lineIndex = lineCounter.get() - 1;
                     List<Character> charList = ElementUtil.toCharacterList(rawLines.get(lineIndex));
                     int startIndex = charCounter.get() - 1;
                     StringBuilder hexColor = new StringBuilder();

                     for (int i = 0; i < 7; i++) {
                        hexColor.append(charList.get(startIndex + i));
                     }

                     charCounter.addAndGet(7);
                     updatedText.append("<color:" + hexColor + ">");
                     Character currentChar = ElementUtil.toCharacterList(rawLines.get(lineCounter.get() - 1)).get(charCounter.get() - 1);
                     lastLine.append(currentChar);
                     updatedText.append(
                        ElementUtil.formatChar(
                           ElementUtil.toCharacterList(rawLines.get(lineCounter.get() - 1)).get(charCounter.getAndIncrement() - 1), lineCounter.get()
                        )
                     );
                     if (typingSound != null && !typingSound.isBlank()) {
                        player.playSound(
                           player.getLocation(),
                           typingSound,
                           SoundCategory.valueOf(typingSoundSource.toUpperCase(Locale.ROOT)),
                           (float)typingSoundVolume,
                           (float)typingSoundPitch
                        );
                     }
                  } else {
                     Character currentChar = ElementUtil.toCharacterList(rawLines.get(lineCounter.get() - 1)).get(charCounter.get() - 1);
                     lastLine.append(currentChar);
                     updatedText.append(
                        ElementUtil.formatChar(
                           ElementUtil.toCharacterList(rawLines.get(lineCounter.get() - 1)).get(charCounter.getAndIncrement() - 1), lineCounter.get()
                        )
                     );
                     if (typingSound != null && !typingSound.isBlank()) {
                        player.playSound(
                           player.getLocation(),
                           typingSound,
                           SoundCategory.valueOf(typingSoundSource.toUpperCase(Locale.ROOT)),
                           (float)typingSoundVolume,
                           (float)typingSoundPitch
                        );
                     }
                  }

                  String outputString = updatedText + "</color:" + dialogueColor + ">" + ElementUtil.getOffset(lineOffset * -1);
                  String fogImage = "";
                  if (dialogue.getFogImage() != null) {
                     String fogColor = dialogue.getFogColor();
                     String fogString = "<color:"
                        + fogColor
                        + ">"
                        + (ResourceUtil.imageUnicodes.get(dialogue.getFogImage()) + ElementUtil.getOffset(-1.0F)).repeat(12)
                        + "</color:"
                        + fogColor
                        + ">";
                     fogImage = ElementUtil.getOffset(-1536.0F) + fogString + ElementUtil.getOffset(-1536.0F);
                  }

                  float lastLineOff = WidthUtil.getWidth(lastLine.toString());
                  String outputText = "<font:"
                     + DataUtil.packNamespace
                     + ":"
                     + DataUtil.packNamespace
                     + "_default>"
                     + ElementUtil.getOffset(lastLineOff)
                     + fogImage
                     + outputString
                     + "</font:"
                     + DataUtil.packNamespace
                     + ":"
                     + DataUtil.packNamespace
                     + "_default>";
                  BarUtil.sendActionBar(player, outputText, Math.max(1, dialogueSpeed));
               }
            },
            0L,
            Math.max(1, dialogueSpeed)
         )
         .getTaskId();
      LuxDialogues.getTaskData().setPlayerTask(player, taskID);
   }

   @Override
   public void sendFullPage(Player player, Dialogue dialogue, String pageID) {
      Page page = dialogue.getPages().get(pageID);
      List<String> dialogueLines = ProcessUtil.removeHexCodes(ProcessUtil.processPlaceholderList(player, page.getLines()));
      List<String> editedLines = ProcessUtil.replaceHexCodes(ProcessUtil.processPlaceholderList(player, page.getLines()));
      int dialogueOffset = dialogue.getDialogueBackgroundImageOffset();
      int lineOffset = dialogue.getDialogueTextOffset();
      int dialogueSize = ResourceUtil.imageSizes.get(dialogue.getDialogueBackgroundImage());
      String dialogueColor = dialogue.getDialogueTextColor();
      String dialogueBGColor = dialogue.getDialogueBackgroundImageColor();
      String fogColor = dialogue.getFogColor();
      String characterAvatar = "";
      if (dialogue.getCharacterImage() != null) {
         int characterOffset = dialogue.getCharacterImageOffset();
         int characterSize = ResourceUtil.imageSizes.get(dialogue.getCharacterImage());
         characterAvatar = "<color:white>"
            + ElementUtil.getOffset(characterOffset)
            + ResourceUtil.imageUnicodes.get(dialogue.getCharacterImage())
            + ElementUtil.getOffset(characterOffset * -1 + characterSize * -1)
            + "</color:white>";
      }

      String fogString = "<color:"
         + fogColor
         + ">"
         + (ResourceUtil.imageUnicodes.get(dialogue.getFogImage()) + ElementUtil.getOffset(-1.0F)).repeat(12)
         + "</color:"
         + fogColor
         + ">";
      String dialogueBackground = "<color:"
         + dialogueBGColor
         + ">"
         + ElementUtil.getOffset(dialogueOffset + dialogueSize * -1)
         + ResourceUtil.imageUnicodes.get(dialogue.getDialogueBackgroundImage())
         + ElementUtil.getOffset(dialogueOffset * -1 + dialogueSize * -1)
         + "</color:"
         + dialogueBGColor
         + ">";
      String characterName = ElementUtil.formatCharacterName(dialogue, player);
      StringBuilder fullText = new StringBuilder();
      fullText.append(dialogueBackground + characterName + characterAvatar + ElementUtil.getOffset(lineOffset) + "<color:" + dialogueColor + ">");
      AtomicInteger lineCounter = new AtomicInteger(1);

      for (String line : dialogueLines) {
         fullText.append("<font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_" + lineCounter.get() + ">");
         fullText.append(editedLines.get(lineCounter.get() - 1));
         fullText.append("</font:" + DataUtil.packNamespace + ":" + DataUtil.packNamespace + "_line_" + lineCounter.getAndIncrement() + ">");
         fullText.append(ElementUtil.getOffset(WidthUtil.getWidth(line) * -1.0F));
      }

      fullText.append("</color:" + dialogueColor + ">" + ElementUtil.getOffset(lineOffset * -1));
      if (page.getAnswers() != null && !page.getAnswers().isEmpty()) {
         StringBuilder answerSection = new StringBuilder();
         AtomicInteger answerCounter = new AtomicInteger(1);
         if (page.getAnswers() != null && !page.getAnswers().isEmpty()) {
            List<Answer> answerList = page.getAnswers();
            boolean visibleAnswer = false;

            for (Answer answerSec : answerList) {
               if (answerSec.getConditions() != null && !answerSec.getConditions().isEmpty()) {
                  if (ConditionUtil.areConditionsTrue(player, answerSec.getConditions())) {
                     visibleAnswer = true;
                     break;
                  }
               } else {
                  visibleAnswer = true;
               }
            }

            if (visibleAnswer) {
               String answerBGColor = dialogue.getAnswerBackgroundImageColor();
               String answerColor = dialogue.getAnswerTextColor();
               String selectedColor = dialogue.getAnswerSelectedTextColor();
               int answerSize = ResourceUtil.imageSizes.get(dialogue.getAnswerBackgroundImage());
               int answerbackOffset = dialogue.getAnswerBackgroundImageOffset();
               int answerOffset = dialogue.getAnswerTextOffset();
               int arrowOffset = dialogue.getArrowImageOffset();
               int arrowSize = ResourceUtil.imageSizes.get(dialogue.getArrowImage());
               String arrowColor = dialogue.getArrowImageColor();
               String arrowString = "<color:"
                  + arrowColor
                  + ">"
                  + ElementUtil.getOffset(arrowOffset)
                  + ResourceUtil.imageUnicodes.get(dialogue.getArrowImage() + DataUtil.getPlayerAnswer(player))
                  + ElementUtil.getOffset(arrowOffset * -1 + arrowSize * -1)
                  + "</color:"
                  + arrowColor
                  + ">";
               String answerBackground = "<color:"
                  + answerBGColor
                  + ">"
                  + ElementUtil.getOffset(answerbackOffset)
                  + ResourceUtil.imageUnicodes.get(dialogue.getAnswerBackgroundImage())
                  + ElementUtil.getOffset(answerSize * -1)
                  + "</color:"
                  + answerBGColor
                  + ">";
               answerSection.append(answerBackground + ElementUtil.getOffset(answerOffset));

               for (Answer answer : answerList) {
                  if (answer.getConditions() == null || answer.getConditions().isEmpty() || ConditionUtil.areConditionsTrue(player, answer.getConditions())) {
                     String rawAnswer = ElementUtil.formatAnswer(player, dialogue, answer, answerCounter.get());
                     String cleanAnswer = ProcessUtil.removeHexCodes(rawAnswer);
                     String coloredAnswer = ProcessUtil.replaceHexCodes(rawAnswer);
                     if (DataUtil.getPlayerAnswer(player).equals(answerCounter.get())) {
                        answerSection.append(
                           ElementUtil.getOffset(answerOffset * -1)
                              + arrowString
                              + ElementUtil.getOffset(answerOffset)
                              + "<color:"
                              + selectedColor
                              + "><font:"
                              + DataUtil.packNamespace
                              + ":"
                              + DataUtil.packNamespace
                              + "_answer_"
                              + answerCounter.get()
                              + ">"
                              + coloredAnswer
                              + "</font:"
                              + DataUtil.packNamespace
                              + ":"
                              + DataUtil.packNamespace
                              + "_answer_"
                              + answerCounter.get()
                              + ">"
                              + ElementUtil.getOffset(WidthUtil.getWidth(cleanAnswer) * -1.0F)
                              + "<color:"
                              + selectedColor
                              + ">"
                        );
                        answerCounter.incrementAndGet();
                     } else {
                        answerSection.append(
                           "<color:"
                              + answerColor
                              + "><font:"
                              + DataUtil.packNamespace
                              + ":"
                              + DataUtil.packNamespace
                              + "_answer_"
                              + answerCounter.get()
                              + ">"
                              + coloredAnswer
                              + "</font:"
                              + DataUtil.packNamespace
                              + ":"
                              + DataUtil.packNamespace
                              + "_answer_"
                              + answerCounter.get()
                              + ">"
                              + ElementUtil.getOffset(WidthUtil.getWidth(cleanAnswer) * -1.0F)
                              + "</color:"
                              + answerColor
                              + ">"
                        );
                        answerCounter.incrementAndGet();
                     }
                  }
               }

               fullText.append(answerSection + ElementUtil.getOffset(answerOffset * -1));
               fullText.append(ElementUtil.getOffset(answerbackOffset * -1));
            }
         }
      }

      String fogImage;
      if (dialogue.getFogImage() != null) {
         fogImage = ElementUtil.getOffset(-1536.0F) + fogString + ElementUtil.getOffset(-1536.0F);
      } else {
         fogImage = "";
      }

      int taskID = Bukkit.getScheduler()
         .runTaskTimerAsynchronously(
            LuxDialogues.getInstance(),
            () -> {
               if (player != null && player.isOnline()) {
                  String outputText = "<font:"
                     + DataUtil.packNamespace
                     + ":"
                     + DataUtil.packNamespace
                     + "_default>"
                     + fogImage
                     + fullText
                     + "</font:"
                     + DataUtil.packNamespace
                     + ":"
                     + DataUtil.packNamespace
                     + "_default>";
                  BarUtil.sendActionBar(player, outputText, 10);
               } else {
                  LuxDialogues.getDialogueSender().clearDialogue(player);
               }
            },
            0L,
            10L
         )
         .getTaskId();
      LuxDialogues.getTaskData().setDialogueTask(player, taskID);
   }

   @Override
   public void nextPage(Player player, Dialogue dialogue, String pageID) {
      boolean redirectENB = false;
      Page page = dialogue.getPages().get(pageID);
      if (page.getPostActions() != null && !page.getPostActions().isEmpty()) {
         for (String action : page.getPostActions()) {
            if (action.contains("@redirect")) {
               redirectENB = true;
            }
         }
      }

      if (!redirectENB) {
         LuxDialogues.getTaskData().cancelDialogueTask(player);
         if (DataUtil.isDialogueTyping(player)) {
            DataUtil.setDialogueStatus(player, false);
            LuxDialogues.getTaskData().cancelPlayerTask(player);
            return;
         }
      }

      if (page.getPostCallbacks() != null && !page.getPostCallbacks().isEmpty()) {
         for (DialogueCallback callback : page.getPostCallbacks()) {
            callback.execute(player);
         }
      }

      if (page.getPostActions() != null && !page.getPostActions().isEmpty()) {
         for (String actionx : page.getPostActions()) {
            ExecuteUtil.executeAction(player, actionx);
         }
      }

      if (!redirectENB) {
         if (page.getAnswers() != null && !page.getAnswers().isEmpty()) {
            DataUtil.setPlayerAnswer(player, 1);
         }

         this.sendFullPage(player, dialogue, pageID);
      }
   }

   @Override
   public void handleClick(Player player, Dialogue dialogue, String pageID) {
      if (DataUtil.isDialogueTyping(player)) {
         if (dialogue.getPreventSkip() == null || !dialogue.getPreventSkip()) {
            DataUtil.setDialogueStatus(player, false);
            LuxDialogues.getTaskData().cancelPlayerTask(player);
            this.nextPage(player, dialogue, DataUtil.getPlayerPage(player).getID());
         }
      } else {
         if (DataUtil.getPlayerPage(player).getGoTo() != null
            && !DataUtil.getPlayerPage(player).getGoTo().isEmpty()
            && GoToUtil.resolveGoto(player, DataUtil.getPlayerPage(player).getGoTo()) != null) {
            List<String> gotoList = DataUtil.getPlayerPage(player).getGoTo();
            String result = GoToUtil.resolveGoto(player, gotoList);
            if (result.contains(".yml:")) {
               String[] parts = result.split("\\.yml:");
               String goToDialogueID = parts[0];
               String goToPageID = parts.length > 1 ? parts[1] : null;
               this.redirectDialogue(player, goToDialogueID, goToPageID);
            }

            if (dialogue.getPages().containsKey(result)) {
               DataUtil.setDialoguePage(player, result);
               LuxDialogues.getTaskData().cancelDialogueTask(player);
               startPage(player, dialogue, result);
            }
         } else {
            if (DataUtil.getPlayerAnswer(player) == 0) {
               this.clearDialogue(player);
               return;
            }

            List<Integer> visibleAnswers = new ArrayList<>();
            if (dialogue.getPages().get(pageID).getAnswers() != null && !dialogue.getPages().get(pageID).getAnswers().isEmpty()) {
               List<Answer> answerList = dialogue.getPages().get(pageID).getAnswers();

               for (int i = 0; i < answerList.size(); i++) {
                  Answer answer = answerList.get(i);
                  if (answer.getConditions() == null || answer.getConditions().isEmpty()) {
                     visibleAnswers.add(i);
                  } else if (ConditionUtil.areConditionsTrue(player, answer.getConditions())) {
                     visibleAnswers.add(i);
                  }
               }
            }

            if (visibleAnswers.isEmpty()) {
               this.clearDialogue(player);
               return;
            }

            Integer playerAnswer = visibleAnswers.get(DataUtil.getPlayerAnswer(player) - 1);
            if (dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo() == null
               || dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo().isEmpty()
               || GoToUtil.resolveGoto(player, dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo()) == null) {
               this.clearDialogue(player);
            }

            List<String> replyList = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getReplyMessages();
            if (!replyList.isEmpty()) {
               for (String reply : replyList) {
                  ReplyUtil.sendReply(player, reply);
               }
            }

            String replySound = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getSoundName();
            if (replySound != null && !replySound.isBlank()) {
               String replySoundSource = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getSoundSource();
               double replySoundVolume = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getSoundVolume();
               double replySoundPitch = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getSoundPitch();
               player.playSound(
                  player.getLocation(),
                  replySound,
                  SoundCategory.valueOf(replySoundSource.toUpperCase(Locale.ROOT)),
                  (float)replySoundVolume,
                  (float)replySoundPitch
               );
            }

            Bukkit.getScheduler()
               .runTask(
                  LuxDialogues.getInstance(),
                  () -> Bukkit.getPluginManager()
                     .callEvent(new DialogueAnsweredEvent(player, dialogue, dialogue.getPages().get(pageID).getAnswers().get(playerAnswer)))
               );
            Bukkit.getScheduler().runTaskLaterAsynchronously(LuxDialogues.getInstance(), () -> {
               for (DialogueCallback callback : dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getCallbacks()) {
                  callback.execute(player);
               }

               for (String action : dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getActions()) {
                  ExecuteUtil.executeAction(player, action);
               }
            }, 3L);
            if (dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo() != null
               && !dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo().isEmpty()
               && GoToUtil.resolveGoto(player, dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo()) != null) {
               List<String> gotoListx = dialogue.getPages().get(pageID).getAnswers().get(playerAnswer).getGoTo();
               String resultx = GoToUtil.resolveGoto(player, gotoListx);
               if (resultx != null) {
                  if (resultx.contains(".yml:")) {
                     String[] parts = resultx.split("\\.yml:");
                     String goToDialogueID = parts[0];
                     String goToPageID = parts.length > 1 ? parts[1] : null;
                     this.redirectDialogue(player, goToDialogueID, goToPageID);
                  }

                  if (dialogue.getPages().containsKey(resultx)) {
                     DataUtil.setDialoguePage(player, resultx);
                     LuxDialogues.getTaskData().cancelDialogueTask(player);
                     startPage(player, dialogue, resultx);
                  }
               }
            }
         }
      }
   }

   @Override
   public void redirectDialogue(Player player, Dialogue dialogue, String PageID) {
      DataUtil.setDialogueStatus(player, false);
      DataUtil.setPlayerAnswer(player, 0);
      DataUtil.setDialoguePage(player, null);
      LuxDialogues.getTaskData().cancelPlayerTask(player);
      LuxDialogues.getTaskData().cancelDialogueTask(player);
      LuxDialogues.getTaskData().cancelRangeTask(player);
      DataUtil.setPlayerDialogue(player, dialogue, dialogue.getPages().get(PageID));
      RangeListener.startRangeChecker(player.getLocation(), player, dialogue);
      LuxDialogues.getDialogueSender().sendDialogue(player, dialogue, PageID);
   }

   @Override
   public void redirectDialogue(Player player, String dialogueID, String PageID) {
      Dialogue dialogue = DataUtil.getDialogue(dialogueID);
      DataUtil.setDialogueStatus(player, false);
      DataUtil.setPlayerAnswer(player, 0);
      DataUtil.setDialoguePage(player, null);
      LuxDialogues.getTaskData().cancelPlayerTask(player);
      LuxDialogues.getTaskData().cancelDialogueTask(player);
      LuxDialogues.getTaskData().cancelRangeTask(player);
      DataUtil.setPlayerDialogue(player, dialogue, dialogue.getPages().get(PageID));
      RangeListener.startRangeChecker(player.getLocation(), player, dialogue);
      LuxDialogues.getDialogueSender().sendDialogue(player, dialogue, PageID);
   }

   @Override
   public void clearDialogue(Player player) {
      Dialogue dialogue = DataUtil.getPlayerDialogue(player);
      if (dialogue == null) {
         DataUtil.setDialogueStatus(player, false);
         DataUtil.setPlayerDialogue(player, null, null);
         DataUtil.setPlayerAnswer(player, 0);
         DataUtil.setDialoguePage(player, null);
         LuxDialogues.getTaskData().cancelPlayerTask(player);
         LuxDialogues.getTaskData().cancelDialogueTask(player);
         LuxDialogues.getTaskData().cancelRangeTask(player);
      } else {
         Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> Bukkit.getPluginManager().callEvent(new DialogueStopEvent(player, dialogue)));
         if (dialogue.getEffect() != null && !dialogue.getEffect().isEmpty()) {
            String effectSetting = dialogue.getEffect();
            if (effectSetting.equalsIgnoreCase("Slowness")) {
               Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> player.removePotionEffect(PotionEffectType.SLOWNESS));
            } else if (effectSetting.equalsIgnoreCase("Freeze")) {
               Bukkit.getScheduler().runTask(LuxDialogues.getInstance(), () -> {
                  player.removePotionEffect(PotionEffectType.SLOWNESS);
                  player.removePotionEffect(PotionEffectType.JUMP_BOOST);
               });
               player.setWalkSpeed(0.2F);
            }
         }

         DataUtil.setDialogueStatus(player, false);
         DataUtil.setPlayerDialogue(player, null, null);
         DataUtil.setPlayerAnswer(player, 0);
         DataUtil.setDialoguePage(player, null);
         LuxDialogues.getTaskData().cancelPlayerTask(player);
         LuxDialogues.getTaskData().cancelDialogueTask(player);
         LuxDialogues.getTaskData().cancelRangeTask(player);
         Bukkit.getScheduler().runTaskAsynchronously(LuxDialogues.getInstance(), () -> BarUtil.sendActionBar(player, "", 0));
         if (YamlUtil.get("config.yml").getBoolean("Hooks.CustomNameplates")) {
            CNPlayer cnPlayer = CustomNameplates.getInstance().getPlayer(player.getUniqueId());
            cnPlayer.releaseActionBar("LuxDialogues");
         }

         DataUtil.commandCooldown.put(player, System.currentTimeMillis() + YamlUtil.get("config.yml").getInt("Settings.command-cooldown") * 50L);
      }
   }
}
