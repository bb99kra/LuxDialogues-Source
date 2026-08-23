package org.aselstudios.luxdialoguesapi.Events;

import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DialogueStopEvent extends Event {
   private static final HandlerList HANDLERS = new HandlerList();
   private final Player player;
   private final Dialogue dialogue;

   public DialogueStopEvent(Player player, Dialogue dialogue) {
      this.player = player;
      this.dialogue = dialogue;
   }

   public Player getPlayer() {
      return this.player;
   }

   public Dialogue getDialogue() {
      return this.dialogue;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }
}
