package org.aselstudios.luxdialoguesapi.Builders;

import java.util.ArrayList;
import java.util.List;

public class Page {
   private final String pageID;
   private final Integer pageTimer;
   private final List<String> goTo;
   private final List<String> lines;
   private final String typingInfoLine;
   private final String steadyInfoLine;
   private final List<String> preActions;
   private final List<String> postActions;
   private final List<String> exitActions;
   private final List<DialogueCallback> preCallbacks;
   private final List<DialogueCallback> postCallbacks;
   private final List<DialogueCallback> exitCallbacks;
   private final List<Answer> answers;

   private Page(Page.Builder builder) {
      this.pageID = builder.pageID;
      this.pageTimer = builder.pageTimer;
      this.goTo = builder.goTo;
      this.lines = builder.lines;
      this.typingInfoLine = builder.typingInfoLine;
      this.steadyInfoLine = builder.steadyInfoLine;
      this.preActions = builder.preActions;
      this.postActions = builder.postActions;
      this.exitActions = builder.exitActions;
      this.preCallbacks = builder.preCallbacks;
      this.postCallbacks = builder.postCallbacks;
      this.exitCallbacks = builder.exitCallbacks;
      this.answers = builder.answers;
   }

   public String getID() {
      return this.pageID;
   }

   public Integer getTimer() {
      return this.pageTimer;
   }

   public List<String> getGoTo() {
      return this.goTo;
   }

   public List<String> getLines() {
      return this.lines;
   }

   public String getTypingInfoLine() {
      return this.typingInfoLine;
   }

   public String getSteadyInfoLine() {
      return this.steadyInfoLine;
   }

   public List<String> getPreActions() {
      return this.preActions;
   }

   public List<String> getPostActions() {
      return this.postActions;
   }

   public List<String> getExitActions() {
      return this.exitActions;
   }

   public List<DialogueCallback> getPreCallbacks() {
      return this.preCallbacks;
   }

   public List<DialogueCallback> getPostCallbacks() {
      return this.postCallbacks;
   }

   public List<DialogueCallback> getExitCallbacks() {
      return this.exitCallbacks;
   }

   public List<Answer> getAnswers() {
      return this.answers;
   }

   public static class Builder {
      private String pageID;
      private Integer pageTimer;
      private List<String> goTo;
      private final List<String> lines = new ArrayList<>();
      private String typingInfoLine;
      private String steadyInfoLine;
      private final List<String> preActions = new ArrayList<>();
      private final List<String> postActions = new ArrayList<>();
      private final List<String> exitActions = new ArrayList<>();
      private final List<DialogueCallback> preCallbacks = new ArrayList<>();
      private final List<DialogueCallback> postCallbacks = new ArrayList<>();
      private final List<DialogueCallback> exitCallbacks = new ArrayList<>();
      private final List<Answer> answers = new ArrayList<>();

      public Page.Builder setID(String id) {
         this.pageID = id;
         return this;
      }

      public Page.Builder setTimer(Integer ticks) {
         this.pageTimer = ticks;
         return this;
      }

      public Page.Builder setGoTo(List<String> goTo) {
         this.goTo = goTo;
         return this;
      }

      public Page.Builder addLine(String line) {
         this.lines.add(line);
         return this;
      }

      public Page.Builder setTypingInfoLine(String typingInfoLine) {
         this.lines.add(typingInfoLine);
         return this;
      }

      public Page.Builder setSteadyInfoLine(String steadyInfoLine) {
         this.lines.add(steadyInfoLine);
         return this;
      }

      public Page.Builder addPreAction(String action) {
         this.preActions.add(action);
         return this;
      }

      public Page.Builder addPostAction(String action) {
         this.postActions.add(action);
         return this;
      }

      public Page.Builder addExitAction(String action) {
         this.exitActions.add(action);
         return this;
      }

      public Page.Builder addPreCallback(DialogueCallback callback) {
         this.preCallbacks.add(callback);
         return this;
      }

      public Page.Builder addPostCallback(DialogueCallback callback) {
         this.postCallbacks.add(callback);
         return this;
      }

      public Page.Builder addExitCallback(DialogueCallback callback) {
         this.exitCallbacks.add(callback);
         return this;
      }

      public Page.Builder addAnswer(Answer answer) {
         this.answers.add(answer);
         return this;
      }

      public Page build() {
         if (this.pageID.isEmpty()) {
            throw new IllegalStateException("Page must have ID!");
         } else if (this.lines.isEmpty()) {
            throw new IllegalStateException("Page must have at least one line!");
         } else {
            return new Page(this);
         }
      }
   }
}
