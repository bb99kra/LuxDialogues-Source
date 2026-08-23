package org.aselstudios.luxdialogues.Utils;

import org.aselstudios.luxdialoguesapi.Builders.Answer;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.aselstudios.luxdialoguesapi.Builders.Page;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class LoaderUtil {
   public static void loadDialoguesFromFolder() {
      if (!DataUtil.loadedDialogues.isEmpty()) {
         DataUtil.loadedDialogues.clear();
      }

      for (String dialogueID : YamlUtil.getAllYamlFileNames("Dialogues")) {
         FileConfiguration dialogueFile = YamlUtil.get("Dialogues/" + dialogueID + ".yml");
         ConfigurationSection pageSection = dialogueFile.getConfigurationSection("Pages");
         Dialogue.Builder dialogueBuilder = new Dialogue.Builder()
            .setDialogueID(dialogueID)
            .setDialogueSpeed(dialogueFile.getInt("Settings.typing-speed"))
            .setTypingSound(
               dialogueFile.getString("Sounds.typing.id"),
               dialogueFile.getString("Sounds.typing.source"),
               dialogueFile.getDouble("Sounds.typing.volume"),
               dialogueFile.getDouble("Sounds.typing.pitch")
            )
            .setRange(dialogueFile.getDouble("Settings.range"))
            .setSelectionSound(
               dialogueFile.getString("Sounds.selection.id"),
               dialogueFile.getString("Sounds.selection.source"),
               dialogueFile.getDouble("Sounds.selection.volume"),
               dialogueFile.getDouble("Sounds.selection.pitch")
            )
            .setEffect(dialogueFile.getString("Settings.effect"))
            .setAnswerNumbers(dialogueFile.getBoolean("Settings.answer-numbers"))
            .setArrowImage(dialogueFile.getString("Images.arrow"), dialogueFile.getString("Colors.arrow"), dialogueFile.getInt("Offsets.arrow"))
            .setDialogueBackgroundImage(
               dialogueFile.getString("Images.dialogue-background"),
               dialogueFile.getString("Colors.dialogue-background"),
               dialogueFile.getInt("Offsets.dialogue-background")
            )
            .setAnswerBackgroundImage(
               dialogueFile.getString("Images.answer-background"),
               dialogueFile.getString("Colors.answer-background"),
               dialogueFile.getInt("Offsets.answer-background")
            )
            .setDialogueText(dialogueFile.getString("Colors.dialogue"), dialogueFile.getInt("Offsets.dialogue-line"))
            .setAnswerText(dialogueFile.getString("Colors.answer"), dialogueFile.getInt("Offsets.answer-line"), dialogueFile.getString("Colors.selected"));
         if (dialogueFile.getBoolean("Settings.character-image")) {
            dialogueBuilder.setCharacterImage(
               dialogueFile.getString("Images.character-background"),
               dialogueFile.getString("Colors.character-background"),
               dialogueFile.getInt("Offsets.character")
            );
         }

         if (dialogueFile.getBoolean("Settings.character-name")) {
            dialogueBuilder.setCharacterNameText(
                  dialogueFile.getString("Character.name"), dialogueFile.getString("Colors.name"), dialogueFile.getInt("Offsets.name")
               )
               .setNameImage(
                  dialogueFile.getString("Images.name-start"),
                  dialogueFile.getString("Images.name-mid"),
                  dialogueFile.getString("Images.name-end"),
                  dialogueFile.getString("Colors.name-background"),
                  dialogueFile.getInt("Offsets.name-background")
               );
         }

         if (dialogueFile.getBoolean("Settings.background-fog")) {
            dialogueBuilder.setFogImage(dialogueFile.getString("Images.fog"), dialogueFile.getString("Colors.fog"));
         }

         dialogueBuilder.setPreventExit(dialogueFile.getBoolean("Settings.prevent-exit", false));
         dialogueBuilder.setPreventSkip(dialogueFile.getBoolean("Settings.prevent-skip", false));
         if (pageSection != null) {
            for (String pageID : pageSection.getKeys(false)) {
               Page.Builder pageBuilder = new Page.Builder();
               pageBuilder.setID(pageID);

               for (String line : dialogueFile.getStringList("Pages." + pageID + ".lines")) {
                  String processedLine = GradientUtil.expandGradientLine(line);
                  pageBuilder.addLine(processedLine);
               }

               if (dialogueFile.contains("Pages." + pageID + ".pre-actions") && !dialogueFile.getStringList("Pages." + pageID + ".pre-actions").isEmpty()) {
                  for (String preAction : dialogueFile.getStringList("Pages." + pageID + ".pre-actions")) {
                     pageBuilder.addPreAction(preAction);
                  }
               }

               if (dialogueFile.contains("Pages." + pageID + ".post-actions") && !dialogueFile.getStringList("Pages." + pageID + ".post-actions").isEmpty()) {
                  for (String postAction : dialogueFile.getStringList("Pages." + pageID + ".post-actions")) {
                     pageBuilder.addPostAction(postAction);
                  }
               }

               if (dialogueFile.contains("Pages." + pageID + ".exit-actions") && !dialogueFile.getStringList("Pages." + pageID + ".exit-actions").isEmpty()) {
                  for (String exitAction : dialogueFile.getStringList("Pages." + pageID + ".exit-actions")) {
                     pageBuilder.addExitAction(exitAction);
                  }
               }

               if (dialogueFile.contains("Pages." + pageID + ".goto")) {
                  pageBuilder.setGoTo(YamlUtil.getFlexibleStringList(dialogueFile, "Pages." + pageID + ".goto"));
               }

               ConfigurationSection answerSection = dialogueFile.getConfigurationSection("Pages." + pageID + ".answers");
               if (answerSection != null) {
                  for (String answerID : answerSection.getKeys(false)) {
                     Answer.Builder answerBuilder = new Answer.Builder();
                     answerBuilder.setAnswerID(answerID);
                     answerBuilder.setAnswerText(GradientUtil.expandGradientLine(dialogueFile.getString("Pages." + pageID + ".answers." + answerID + ".text")));
                     if (!dialogueFile.getString("Pages." + pageID + ".answers." + answerID + ".sound.id", "").isEmpty()) {
                        answerBuilder.setSound(
                           dialogueFile.getString("Pages." + pageID + ".answers." + answerID + ".sound.id"),
                           dialogueFile.getString("Pages." + pageID + ".answers." + answerID + ".sound.source", "MASTER"),
                           dialogueFile.getDouble("Pages." + pageID + ".answers." + answerID + ".sound.volume", 1.0),
                           dialogueFile.getDouble("Pages." + pageID + ".answers." + answerID + ".sound.pitch", 1.0)
                        );
                     }

                     if (dialogueFile.contains("Pages." + pageID + ".answers." + answerID + ".reply")
                        && !dialogueFile.getStringList("Pages." + pageID + ".answers." + answerID + ".reply").isEmpty()) {
                        for (String reply : dialogueFile.getStringList("Pages." + pageID + ".answers." + answerID + ".reply")) {
                           answerBuilder.addReplyMessage(reply);
                        }
                     }

                     if (dialogueFile.contains("Pages." + pageID + ".answers." + answerID + ".actions")
                        && !dialogueFile.getStringList("Pages." + pageID + ".answers." + answerID + ".actions").isEmpty()) {
                        for (String action : dialogueFile.getStringList("Pages." + pageID + ".answers." + answerID + ".actions")) {
                           answerBuilder.addAction(action);
                        }
                     }

                     if (dialogueFile.contains("Pages." + pageID + ".answers." + answerID + ".condition")) {
                        for (String condition : YamlUtil.getFlexibleStringList(dialogueFile, "Pages." + pageID + ".answers." + answerID + ".condition")) {
                           answerBuilder.addCondition(condition);
                        }
                     }

                     answerBuilder.setGoTo(YamlUtil.getFlexibleStringList(dialogueFile, "Pages." + pageID + ".answers." + answerID + ".goto"));
                     pageBuilder.addAnswer(answerBuilder.build());
                  }
               }

               dialogueBuilder.addPage(pageBuilder.build());
            }
         }

         Dialogue dialogue = dialogueBuilder.build();
         DataUtil.loadedDialogues.put(dialogueID, dialogue);
      }
   }
}
