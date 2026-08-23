package org.aselstudios.luxdialoguesapi.Events;

import org.aselstudios.luxdialoguesapi.Builders.Answer;
import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DialogueAnsweredEvent extends Event {
   private static final HandlerList HANDLERS = new HandlerList();
   private final Player player;
   private final Dialogue dialogue;
   private final Answer answer;

   public DialogueAnsweredEvent(Player player, Dialogue dialogue, Answer answer) {
      this.player = player;
      this.dialogue = dialogue;
      this.answer = answer;
   }

   public Player getPlayer() {
      return this.player;
   }

   public Dialogue getDialogue() {
      return this.dialogue;
   }

   public Answer getAnswer() {
      return this.answer;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }
}
