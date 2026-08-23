package org.aselstudios.luxdialogues.Databases;

import org.bukkit.entity.Player;

public interface TaskData<T> {
   void setPlayerTask(Player var1, T var2);

   T getPlayerTask(Player var1);

   void cancelPlayerTask(Player var1);

   void setDialogueTask(Player var1, T var2);

   T getDialogueTask(Player var1);

   void cancelDialogueTask(Player var1);

   void setRangeTask(Player var1, T var2);

   T getRangeTask(Player var1);

   void cancelRangeTask(Player var1);
}
