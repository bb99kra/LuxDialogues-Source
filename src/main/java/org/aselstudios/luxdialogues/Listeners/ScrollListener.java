package org.aselstudios.luxdialogues.Listeners;

import java.time.Duration;
import java.util.Locale;
import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Utils.ConditionUtil;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.aselstudios.luxdialogues.Utils.ForkUtil;
import org.aselstudios.luxdialogues.Utils.MessageUtil;
import org.aselstudios.luxdialogues.Utils.YamlUtil;
import org.aselstudios.luxdialoguesapi.Builders.Answer;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.aselstudios.luxdialoguesapi.Builders.Page;
import org.bukkit.Bukkit;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

public class ScrollListener implements Listener {
   @EventHandler
   public void onScroll(PlayerItemHeldEvent event) {
      Player player = event.getPlayer();
      Dialogue dialogue = DataUtil.getPlayerDialogue(player);
      Page page = DataUtil.getPlayerPage(player);
      if (dialogue != null) {
         if (page != null) {
            if (page.getAnswers() != null && (page.getAnswers() == null || !page.getAnswers().isEmpty())) {
               if (DataUtil.isDialogueTyping(player)) {
                  event.setCancelled(true);
               } else {
                  event.setCancelled(true);
                  if (!scrollCooldown(player)) {
                     if (page.getAnswers() != null && !page.getAnswers().isEmpty()) {
                        String selectionSound = dialogue.getSelectionSound();
                        String selectionSoundSource = dialogue.getSelectionSoundSource();
                        double selectionSoundVolume = dialogue.getSelectionSoundVolume();
                        double selectionSoundPitch = dialogue.getSelectionSoundPitch();
                        int answerCount = 0;

                        for (Answer answer : page.getAnswers()) {
                           if (answer.getConditions() == null
                              || answer.getConditions().isEmpty()
                              || ConditionUtil.areConditionsTrue(player, answer.getConditions())) {
                              answerCount++;
                           }
                        }

                        if (answerCount > 1) {
                           int playerAnswer = DataUtil.getPlayerAnswer(player);
                           int oldSlot = event.getPreviousSlot();
                           int newSlot = event.getNewSlot();
                           if (oldSlot == 0) {
                              if (newSlot == 8) {
                                 if (playerAnswer == 1) {
                                    DataUtil.setPlayerAnswer(player, answerCount);
                                 } else {
                                    DataUtil.setPlayerAnswer(player, playerAnswer - 1);
                                 }

                                 if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                    player.playSound(
                                       player.getLocation(),
                                       selectionSound,
                                       SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                       (float)selectionSoundVolume,
                                       (float)selectionSoundPitch
                                    );
                                 }
                              } else if (newSlot > oldSlot) {
                                 if (playerAnswer == answerCount) {
                                    DataUtil.setPlayerAnswer(player, 1);
                                 } else {
                                    DataUtil.setPlayerAnswer(player, playerAnswer + 1);
                                 }

                                 if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                    player.playSound(
                                       player.getLocation(),
                                       selectionSound,
                                       SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                       (float)selectionSoundVolume,
                                       (float)selectionSoundPitch
                                    );
                                 }
                              }
                           } else if (oldSlot == 8) {
                              if (newSlot == 0) {
                                 if (playerAnswer == answerCount) {
                                    DataUtil.setPlayerAnswer(player, 1);
                                 } else {
                                    DataUtil.setPlayerAnswer(player, playerAnswer + 1);
                                 }

                                 if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                    player.playSound(
                                       player.getLocation(),
                                       selectionSound,
                                       SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                       (float)selectionSoundVolume,
                                       (float)selectionSoundPitch
                                    );
                                 }
                              } else if (oldSlot > newSlot) {
                                 if (playerAnswer == 1) {
                                    DataUtil.setPlayerAnswer(player, answerCount);
                                 } else {
                                    DataUtil.setPlayerAnswer(player, playerAnswer - 1);
                                 }

                                 if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                    player.playSound(
                                       player.getLocation(),
                                       selectionSound,
                                       SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                       (float)selectionSoundVolume,
                                       (float)selectionSoundPitch
                                    );
                                 }
                              }
                           } else if (oldSlot > newSlot) {
                              if (playerAnswer == 1) {
                                 DataUtil.setPlayerAnswer(player, answerCount);
                              } else {
                                 DataUtil.setPlayerAnswer(player, playerAnswer - 1);
                              }

                              if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                 player.playSound(
                                    player.getLocation(),
                                    selectionSound,
                                    SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                    (float)selectionSoundVolume,
                                    (float)selectionSoundPitch
                                 );
                              }
                           } else if (oldSlot < newSlot) {
                              if (playerAnswer == answerCount) {
                                 DataUtil.setPlayerAnswer(player, 1);
                              } else {
                                 DataUtil.setPlayerAnswer(player, playerAnswer + 1);
                              }

                              if (selectionSound != null && MessageUtil.isMessageEnabled(selectionSound)) {
                                 player.playSound(
                                    player.getLocation(),
                                    selectionSound,
                                    SoundCategory.valueOf(selectionSoundSource.toUpperCase(Locale.ROOT)),
                                    (float)selectionSoundVolume,
                                    (float)selectionSoundPitch
                                 );
                              }
                           }

                           LuxDialogues.getTaskData().cancelDialogueTask(player);
                           LuxDialogues.getDialogueSender().sendFullPage(player, dialogue, DataUtil.getPlayerPage(player).getID());
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public static Boolean scrollCooldown(Player player) {
      if (DataUtil.scrollCooldown.containsKey(player) && DataUtil.scrollCooldown.get(player)) {
         return true;
      } else {
         DataUtil.scrollCooldown.put(player, true);
         int cooldownTick = YamlUtil.get("config.yml").getInt("Settings.scroll-cooldown");
         if (ForkUtil.isUsingFolia()) {
            LuxDialogues.getMorePaperLib()
               .scheduling()
               .asyncScheduler()
               .runDelayed(() -> DataUtil.scrollCooldown.put(player, false), Duration.ofMillis(cooldownTick * 50L));
         } else {
            Bukkit.getScheduler().runTaskLaterAsynchronously(LuxDialogues.getInstance(), () -> DataUtil.scrollCooldown.put(player, false), cooldownTick);
         }

         return false;
      }
   }
}
