package org.aselstudios.luxdialogues.Databases;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.aselstudios.luxdialogues.Utils.DataUtil;
import org.bukkit.entity.Player;
import space.arim.morepaperlib.scheduling.ScheduledTask;

public class FoliaTaskData implements TaskData<ScheduledTask> {
   private final ConcurrentMap<Player, ScheduledTask> playerTasks = new ConcurrentHashMap<>();
   private final ConcurrentMap<Player, ScheduledTask> dialogueTasks = new ConcurrentHashMap<>();
   private final ConcurrentMap<Player, ScheduledTask> rangeTasks = new ConcurrentHashMap<>();

   public void setPlayerTask(Player player, ScheduledTask task) {
      ScheduledTask oldTask = this.playerTasks.put(player, task);
      if (oldTask != null) {
         oldTask.cancel();
      }
   }

   public ScheduledTask getPlayerTask(Player player) {
      return this.playerTasks.get(player);
   }

   @Override
   public void cancelPlayerTask(Player player) {
      ScheduledTask task = this.playerTasks.remove(player);
      if (task != null) {
         task.cancel();
         DataUtil.setDialogueStatus(player, false);
      }
   }

   public void setDialogueTask(Player player, ScheduledTask task) {
      ScheduledTask oldTask = this.dialogueTasks.put(player, task);
      if (oldTask != null) {
         oldTask.cancel();
      }
   }

   public ScheduledTask getDialogueTask(Player player) {
      return this.dialogueTasks.get(player);
   }

   @Override
   public void cancelDialogueTask(Player player) {
      ScheduledTask task = this.dialogueTasks.remove(player);
      if (task != null) {
         task.cancel();
         DataUtil.setDialogueStatus(player, false);
      }
   }

   public void setRangeTask(Player player, ScheduledTask task) {
      ScheduledTask oldTask = this.rangeTasks.put(player, task);
      if (oldTask != null) {
         oldTask.cancel();
      }
   }

   public ScheduledTask getRangeTask(Player player) {
      return this.rangeTasks.get(player);
   }

   @Override
   public void cancelRangeTask(Player player) {
      ScheduledTask task = this.rangeTasks.remove(player);
      if (task != null) {
         task.cancel();
         DataUtil.setDialogueStatus(player, false);
      }
   }
}
