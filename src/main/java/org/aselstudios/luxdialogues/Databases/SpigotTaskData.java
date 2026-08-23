package org.aselstudios.luxdialogues.Databases;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class SpigotTaskData implements TaskData<Integer> {
   private final ConcurrentMap<Player, Integer> playerTasks = new ConcurrentHashMap<>();
   private final ConcurrentMap<Player, Integer> dialogueTasks = new ConcurrentHashMap<>();
   private final ConcurrentMap<Player, Integer> rangeTasks = new ConcurrentHashMap<>();

   public void setPlayerTask(Player player, Integer taskID) {
      Integer oldTask = this.playerTasks.put(player, taskID);
      if (oldTask != null && oldTask != 0) {
         Bukkit.getScheduler().cancelTask(oldTask);
      }
   }

   public Integer getPlayerTask(Player player) {
      return this.playerTasks.getOrDefault(player, 0);
   }

   @Override
   public void cancelPlayerTask(Player player) {
      Integer taskID = this.playerTasks.remove(player);
      if (taskID != null && taskID != 0) {
         Bukkit.getScheduler().cancelTask(taskID);
         DataUtil.setDialogueStatus(player, false);
      }
   }

   public void setDialogueTask(Player player, Integer taskID) {
      Integer oldTask = this.dialogueTasks.put(player, taskID);
      if (oldTask != null && oldTask != 0) {
         Bukkit.getScheduler().cancelTask(oldTask);
      }
   }

   public Integer getDialogueTask(Player player) {
      return this.dialogueTasks.getOrDefault(player, 0);
   }

   @Override
   public void cancelDialogueTask(Player player) {
      Integer taskID = this.dialogueTasks.remove(player);
      if (taskID != null && taskID != 0) {
         Bukkit.getScheduler().cancelTask(taskID);
         DataUtil.setDialogueStatus(player, false);
      }
   }

   public void setRangeTask(Player player, Integer taskID) {
      Integer oldTask = this.rangeTasks.put(player, taskID);
      if (oldTask != null && oldTask != 0) {
         Bukkit.getScheduler().cancelTask(oldTask);
      }
   }

   public Integer getRangeTask(Player player) {
      return this.rangeTasks.getOrDefault(player, 0);
   }

   @Override
   public void cancelRangeTask(Player player) {
      Integer taskID = this.rangeTasks.remove(player);
      if (taskID != null && taskID != 0) {
         Bukkit.getScheduler().cancelTask(taskID);
         DataUtil.setDialogueStatus(player, false);
      }
   }
}
