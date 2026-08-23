package org.aselstudios.luxdialoguesapi.Builders;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface DialogueCallback {
   void execute(Player var1);
}
