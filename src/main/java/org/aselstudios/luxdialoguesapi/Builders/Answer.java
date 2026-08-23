package org.aselstudios.luxdialoguesapi.Builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answer {
   private final String answerID;
   private final String answerText;
   private final List<String> goTo;
   private final List<String> replyMessages;
   private final String sound;
   private final String soundSource;
   private final Double soundVolume;
   private final Double soundPitch;
   private final List<String> conditions;
   private final List<String> actions;
   private final List<DialogueCallback> callbacks;

   private Answer(Answer.Builder builder) {
      this.answerID = builder.answerID;
      this.answerText = builder.answerText;
      this.goTo = builder.goTo;
      this.replyMessages = Collections.unmodifiableList(new ArrayList<>(builder.replyMessages));
      this.sound = builder.sound;
      this.soundSource = builder.soundSource;
      this.soundVolume = builder.soundVolume;
      this.soundPitch = builder.soundPitch;
      this.conditions = Collections.unmodifiableList(new ArrayList<>(builder.conditions));
      this.actions = Collections.unmodifiableList(new ArrayList<>(builder.actions));
      this.callbacks = Collections.unmodifiableList(new ArrayList<>(builder.callbacks));
   }

   public String getAnswerID() {
      return this.answerID;
   }

   public String getAnswerText() {
      return this.answerText;
   }

   public List<String> getGoTo() {
      return this.goTo;
   }

   public List<String> getReplyMessages() {
      return this.replyMessages;
   }

   public String getSoundName() {
      return this.sound;
   }

   public String getSoundSource() {
      return this.soundSource;
   }

   public Double getSoundVolume() {
      return this.soundVolume;
   }

   public Double getSoundPitch() {
      return this.soundPitch;
   }

   public List<String> getConditions() {
      return this.conditions;
   }

   public List<String> getActions() {
      return this.actions;
   }

   public List<DialogueCallback> getCallbacks() {
      return this.callbacks;
   }

   public static class Builder {
      private String answerID;
      private String answerText;
      private List<String> goTo;
      private List<String> replyMessages = new ArrayList<>();
      private String sound;
      private String soundSource;
      private Double soundVolume;
      private Double soundPitch;
      private List<String> conditions = new ArrayList<>();
      private List<String> actions = new ArrayList<>();
      private List<DialogueCallback> callbacks = new ArrayList<>();

      public Answer.Builder setAnswerID(String answerID) {
         this.answerID = answerID;
         return this;
      }

      public Answer.Builder setAnswerText(String answerText) {
         this.answerText = answerText;
         return this;
      }

      public Answer.Builder setGoTo(List<String> goTo) {
         this.goTo = goTo;
         return this;
      }

      public Answer.Builder addReplyMessage(String message) {
         this.replyMessages.add(message);
         return this;
      }

      public Answer.Builder setSound(String sound, String soundSource, Double soundVolume, Double soundPitch) {
         this.sound = sound;
         this.soundSource = soundSource;
         this.soundVolume = soundVolume;
         this.soundPitch = soundPitch;
         return this;
      }

      public Answer.Builder addCondition(String condition) {
         this.conditions.add(condition);
         return this;
      }

      public Answer.Builder addAction(String action) {
         this.actions.add(action);
         return this;
      }

      public Answer.Builder addCallback(DialogueCallback callback) {
         this.callbacks.add(callback);
         return this;
      }

      public Answer build() {
         return new Answer(this);
      }
   }
}
