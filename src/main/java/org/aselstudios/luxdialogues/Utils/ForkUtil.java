package org.aselstudios.luxdialogues.Utils;

import org.aselstudios.luxdialogues.LuxDialogues;
import org.aselstudios.luxdialogues.Databases.FoliaTaskData;
import org.aselstudios.luxdialogues.Databases.SpigotTaskData;
import org.aselstudios.luxdialogues.Databases.TaskData;
import org.aselstudios.luxdialogues.Dialogues.DialogueSender;
import org.aselstudios.luxdialogues.Dialogues.FoliaDialogue;
import org.aselstudios.luxdialogues.Dialogues.SpigotDialogue;

public class ForkUtil {
   public static DialogueSender getSender() {
      return (DialogueSender)(isUsingFolia() ? new FoliaDialogue() : new SpigotDialogue());
   }

   public static TaskData getTask() {
      return (TaskData)(isUsingFolia() ? new FoliaTaskData() : new SpigotTaskData());
   }

   public static boolean isUsingFolia() {
      return LuxDialogues.getMorePaperLib().scheduling().isUsingFolia();
   }
}
