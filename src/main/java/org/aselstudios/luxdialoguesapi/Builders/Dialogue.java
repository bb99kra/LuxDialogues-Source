package org.aselstudios.luxdialoguesapi.Builders;

import java.util.HashMap;
import java.util.Map;

public class Dialogue {
   private final String dialogueID;
   private final Double range;
   private final String effect;
   private final Boolean preventExit;
   private final Boolean preventSkip;
   private final String characterNameText;
   private final String characterNameTextColor;
   private final Integer characterNameTextOffset;
   private final String characterImage;
   private final String characterImageColor;
   private final Integer characterImageOffset;
   private final String arrowImage;
   private final String arrowImageColor;
   private final Integer arrowImageOffset;
   private final String dialogueTextColor;
   private final Integer dialogueTextOffset;
   private final String dialogueBackgroundImage;
   private final String dialogueBackgroundImageColor;
   private final Integer dialogueBackgroundImageOffset;
   private final String answerTextColor;
   private final String answerSelectedTextColor;
   private final Integer answerTextOffset;
   private final String answerBackgroundImage;
   private final String answerBackgroundImageColor;
   private final Integer answerBackgroundImageOffset;
   private final Boolean answerNumbers;
   private final String nameStartImage;
   private final String nameMidImage;
   private final String nameEndImage;
   private final String nameImageColor;
   private final Integer nameBackgroundImageOffset;
   private final String fogImage;
   private final String fogColor;
   private final Integer dialogueSpeed;
   private final String typingSound;
   private final String typingSoundSource;
   private final Double typingSoundVolume;
   private final Double typingSoundPitch;
   private final String selectionSound;
   private final String selectionSoundSource;
   private final Double selectionSoundVolume;
   private final Double selectionSoundPitch;
   private final Map<String, Page> pages;

   private Dialogue(Dialogue.Builder builder) {
      this.dialogueID = builder.dialogueID;
      this.range = builder.range;
      this.effect = builder.effect;
      this.preventExit = builder.preventExit;
      this.preventSkip = builder.preventSkip;
      this.characterNameText = builder.characterNameText;
      this.characterNameTextColor = builder.characterNameTextColor;
      this.characterNameTextOffset = builder.characterNameTextOffset;
      this.characterImage = builder.characterImage;
      this.characterImageColor = builder.characterImageColor;
      this.characterImageOffset = builder.characterImageOffset;
      this.arrowImage = builder.arrowImage;
      this.arrowImageColor = builder.arrowImageColor;
      this.arrowImageOffset = builder.arrowImageOffset;
      this.dialogueTextColor = builder.dialogueTextColor;
      this.dialogueTextOffset = builder.dialogueTextOffset;
      this.dialogueBackgroundImage = builder.dialogueBackgroundImage;
      this.dialogueBackgroundImageColor = builder.dialogueBackgroundImageColor;
      this.dialogueBackgroundImageOffset = builder.dialogueBackgroundImageOffset;
      this.answerTextColor = builder.answerTextColor;
      this.answerSelectedTextColor = builder.answerSelectedTextColor;
      this.answerTextOffset = builder.answerTextOffset;
      this.answerBackgroundImage = builder.answerBackgroundImage;
      this.answerBackgroundImageColor = builder.answerBackgroundImageColor;
      this.answerBackgroundImageOffset = builder.answerBackgroundImageOffset;
      this.answerNumbers = builder.answerNumbers;
      this.nameStartImage = builder.nameStartImage;
      this.nameMidImage = builder.nameMidImage;
      this.nameEndImage = builder.nameEndImage;
      this.nameImageColor = builder.nameImageColor;
      this.nameBackgroundImageOffset = builder.nameBackgroundImageOffset;
      this.fogImage = builder.fogImage;
      this.fogColor = builder.fogColor;
      this.dialogueSpeed = builder.dialogueSpeed;
      this.typingSound = builder.typingSound;
      this.typingSoundSource = builder.typingSoundSource;
      this.typingSoundVolume = builder.typingSoundVolume;
      this.typingSoundPitch = builder.typingSoundPitch;
      this.selectionSound = builder.selectionSound;
      this.selectionSoundSource = builder.selectionSoundSource;
      this.selectionSoundVolume = builder.selectionSoundVolume;
      this.selectionSoundPitch = builder.selectionSoundPitch;
      this.pages = builder.pages;
   }

   public String getDialogueID() {
      return this.dialogueID;
   }

   public Double getRange() {
      return this.range;
   }

   public String getEffect() {
      return this.effect;
   }

   public Boolean getPreventExit() {
      return this.preventExit;
   }

   public Boolean getPreventSkip() {
      return this.preventSkip;
   }

   public String getCharacterNameText() {
      return this.characterNameText;
   }

   public String getCharacterNameTextColor() {
      return this.characterNameTextColor;
   }

   public Integer getCharacterNameTextOffset() {
      return this.characterNameTextOffset;
   }

   public String getCharacterImage() {
      return this.characterImage;
   }

   public String getCharacterImageColor() {
      return this.characterImageColor;
   }

   public Integer getCharacterImageOffset() {
      return this.characterImageOffset;
   }

   public String getArrowImage() {
      return this.arrowImage;
   }

   public String getArrowImageColor() {
      return this.arrowImageColor;
   }

   public Integer getArrowImageOffset() {
      return this.arrowImageOffset;
   }

   public String getDialogueTextColor() {
      return this.dialogueTextColor;
   }

   public Integer getDialogueTextOffset() {
      return this.dialogueTextOffset;
   }

   public String getDialogueBackgroundImage() {
      return this.dialogueBackgroundImage;
   }

   public String getDialogueBackgroundImageColor() {
      return this.dialogueBackgroundImageColor;
   }

   public Integer getDialogueBackgroundImageOffset() {
      return this.dialogueBackgroundImageOffset;
   }

   public String getAnswerTextColor() {
      return this.answerTextColor;
   }

   public String getAnswerSelectedTextColor() {
      return this.answerSelectedTextColor;
   }

   public Integer getAnswerTextOffset() {
      return this.answerTextOffset;
   }

   public String getAnswerBackgroundImage() {
      return this.answerBackgroundImage;
   }

   public String getAnswerBackgroundImageColor() {
      return this.answerBackgroundImageColor;
   }

   public Integer getAnswerBackgroundImageOffset() {
      return this.answerBackgroundImageOffset;
   }

   public Boolean getAnswerNumbers() {
      return this.answerNumbers;
   }

   public String getNameStartImage() {
      return this.nameStartImage;
   }

   public String getNameMidImage() {
      return this.nameMidImage;
   }

   public String getNameEndImage() {
      return this.nameEndImage;
   }

   public String getNameImageColor() {
      return this.nameImageColor;
   }

   public Integer getNameBackgroundImageOffset() {
      return this.nameBackgroundImageOffset;
   }

   public String getFogImage() {
      return this.fogImage;
   }

   public String getFogColor() {
      return this.fogColor;
   }

   public Integer getDialogueSpeed() {
      return this.dialogueSpeed;
   }

   public String getTypingSound() {
      return this.typingSound;
   }

   public String getTypingSoundSource() {
      return this.typingSoundSource;
   }

   public Double getTypingSoundVolume() {
      return this.typingSoundVolume;
   }

   public Double getTypingSoundPitch() {
      return this.typingSoundPitch;
   }

   public String getSelectionSound() {
      return this.selectionSound;
   }

   public String getSelectionSoundSource() {
      return this.selectionSoundSource;
   }

   public Double getSelectionSoundVolume() {
      return this.selectionSoundVolume;
   }

   public Double getSelectionSoundPitch() {
      return this.selectionSoundPitch;
   }

   public Map<String, Page> getPages() {
      return this.pages;
   }

   public static class Builder {
      private String dialogueID;
      private Double range;
      private String effect;
      private Boolean preventExit;
      private Boolean preventSkip;
      private String characterNameText;
      private String characterNameTextColor;
      private Integer characterNameTextOffset;
      private String characterImage;
      private String characterImageColor;
      private Integer characterImageOffset;
      private String arrowImage;
      private String arrowImageColor;
      private Integer arrowImageOffset;
      private String dialogueTextColor;
      private Integer dialogueTextOffset;
      private String dialogueBackgroundImage;
      private String dialogueBackgroundImageColor;
      private Integer dialogueBackgroundImageOffset;
      private String answerTextColor;
      private String answerSelectedTextColor;
      private Integer answerTextOffset;
      private String answerBackgroundImage;
      private String answerBackgroundImageColor;
      private Integer answerBackgroundImageOffset;
      private Boolean answerNumbers;
      private String nameStartImage;
      private String nameMidImage;
      private String nameEndImage;
      private String nameImageColor;
      private Integer nameBackgroundImageOffset;
      private String fogImage;
      private String fogColor;
      private Integer dialogueSpeed;
      private String typingSound;
      private String typingSoundSource;
      private Double typingSoundVolume;
      private Double typingSoundPitch;
      private String selectionSound;
      private String selectionSoundSource;
      private Double selectionSoundVolume;
      private Double selectionSoundPitch;
      private final Map<String, Page> pages = new HashMap<>();

      public Dialogue.Builder setDialogueID(String dialogueID) {
         this.dialogueID = dialogueID;
         return this;
      }

      public Dialogue.Builder setEffect(String effect) {
         this.effect = effect;
         return this;
      }

      public Dialogue.Builder setRange(Double range) {
         this.range = range;
         return this;
      }

      public Dialogue.Builder setPreventExit(Boolean value) {
         this.preventExit = value;
         return this;
      }

      public Dialogue.Builder setPreventSkip(Boolean value) {
         this.preventSkip = value;
         return this;
      }

      public Dialogue.Builder setCharacterNameText(String text, String color, Integer offset) {
         this.characterNameText = text;
         this.characterNameTextColor = color;
         this.characterNameTextOffset = offset;
         return this;
      }

      public Dialogue.Builder setCharacterImage(String image, String color, Integer offset) {
         this.characterImage = image;
         this.characterImageColor = color;
         this.characterImageOffset = offset;
         return this;
      }

      public Dialogue.Builder setArrowImage(String image, String color, Integer offset) {
         this.arrowImage = image;
         this.arrowImageColor = color;
         this.arrowImageOffset = offset;
         return this;
      }

      public Dialogue.Builder setDialogueBackgroundImage(String image, String color, Integer offset) {
         this.dialogueBackgroundImage = image;
         this.dialogueBackgroundImageColor = color;
         this.dialogueBackgroundImageOffset = offset;
         return this;
      }

      public Dialogue.Builder setAnswerBackgroundImage(String image, String color, Integer offset) {
         this.answerBackgroundImage = image;
         this.answerBackgroundImageColor = color;
         this.answerBackgroundImageOffset = offset;
         return this;
      }

      public Dialogue.Builder setNameImage(String startImage, String midImage, String endImage, String color, Integer offset) {
         this.nameStartImage = startImage;
         this.nameMidImage = midImage;
         this.nameEndImage = endImage;
         this.nameImageColor = color;
         this.nameBackgroundImageOffset = offset;
         return this;
      }

      public Dialogue.Builder setFogImage(String image, String color) {
         this.fogImage = image;
         this.fogColor = color;
         return this;
      }

      public Dialogue.Builder setDialogueText(String color, Integer offset) {
         this.dialogueTextColor = color;
         this.dialogueTextOffset = offset;
         return this;
      }

      public Dialogue.Builder setAnswerText(String color, Integer offset, String selectedColor) {
         this.answerTextColor = color;
         this.answerSelectedTextColor = selectedColor;
         this.answerTextOffset = offset;
         return this;
      }

      public Dialogue.Builder setAnswerNumbers(Boolean value) {
         this.answerNumbers = value;
         return this;
      }

      public Dialogue.Builder setDialogueSpeed(Integer dialogueSpeed) {
         this.dialogueSpeed = dialogueSpeed;
         return this;
      }

      public Dialogue.Builder setTypingSound(String typingSound, String typingSoundSource, Double typingSoundVolume, Double typingSoundPitch) {
         this.typingSound = typingSound;
         this.typingSoundSource = typingSoundSource;
         this.typingSoundVolume = typingSoundVolume;
         this.typingSoundPitch = typingSoundPitch;
         return this;
      }

      public Dialogue.Builder setSelectionSound(String selectionSound, String selectionSoundSource, Double selectionSoundVolume, Double selectionSoundPitch) {
         this.selectionSound = selectionSound;
         this.selectionSoundSource = selectionSoundSource;
         this.selectionSoundVolume = selectionSoundVolume;
         this.selectionSoundPitch = selectionSoundPitch;
         return this;
      }

      public Dialogue.Builder addPage(Page page) {
         this.pages.put(page.getID(), page);
         return this;
      }

      public Dialogue build() {
         StringBuilder missing = new StringBuilder();
         if (this.dialogueID == null) {
            missing.append("dialogueID, ");
         }

         if (this.dialogueTextColor == null) {
            missing.append("dialogueTextColor, ");
         }

         if (this.dialogueTextOffset == null) {
            missing.append("dialogueTextOffset, ");
         }

         if (this.dialogueBackgroundImage == null) {
            missing.append("dialogueBackgroundImage, ");
         }

         if (this.dialogueBackgroundImageColor == null) {
            missing.append("dialogueBackgroundImageColor, ");
         }

         if (this.dialogueBackgroundImageOffset == null) {
            missing.append("dialogueBackgroundImageOffset, ");
         }

         if (this.characterImage != null && this.characterImageOffset == null) {
            missing.append("characterImageOffset, ");
         }

         if (this.characterNameText != null) {
            if (this.characterNameTextColor == null) {
               missing.append("characterNameTextColor, ");
            }

            if (this.characterNameTextOffset == null) {
               missing.append("characterNameTextOffset, ");
            }

            if (this.nameStartImage == null) {
               missing.append("nameStartImage, ");
            }

            if (this.nameMidImage == null) {
               missing.append("nameMidImage, ");
            }

            if (this.nameEndImage == null) {
               missing.append("nameEndImage, ");
            }

            if (this.nameImageColor == null) {
               missing.append("nameImageColor, ");
            }
         }

         if (this.dialogueSpeed == null) {
            missing.append("dialogueSpeed, ");
         }

         if (this.typingSound == null) {
            missing.append("typingSound, ");
         }

         if (this.typingSoundVolume == null) {
            missing.append("typingSoundVolume, ");
         }

         if (this.typingSoundPitch == null) {
            missing.append("typingSoundPitch, ");
         }

         if (this.selectionSound == null) {
            missing.append("selectionSound, ");
         }

         if (missing.length() > 0) {
            missing.setLength(missing.length() - 2);
            System.out.println("&4LuxDialogues &7- &cIncorrect usage of builder! Missing: " + missing);
         }

         if (this.pages.isEmpty()) {
            System.out.println("&4LuxDialogues &7- &cBuilder Error: Dialogue must have at least one page!");
         }

         return new Dialogue(this);
      }
   }
}
