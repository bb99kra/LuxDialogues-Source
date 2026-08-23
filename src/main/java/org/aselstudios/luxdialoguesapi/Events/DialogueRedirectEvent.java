package org.aselstudios.luxdialoguesapi.Events;

import org.aselstudios.luxdialoguesapi.Builders.Dialogue;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class DialogueRedirectEvent extends Event {
   private static final HandlerList HANDLERS = new HandlerList();
   private final Player player;
   private final Dialogue oldDialogue;
   private final Dialogue newDialogue;

   public DialogueRedirectEvent(Player player, Dialogue oldDialogue, Dialogue newDialogue) {
      this.player = player;
      this.oldDialogue = oldDialogue;
      this.newDialogue = newDialogue;
   }

   public Player getPlayer() {
      return this.player;
   }

   public Dialogue getOldDialogue() {
      return this.oldDialogue;
   }

   public Dialogue getNewDialogue() {
      return this.newDialogue;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }
}
