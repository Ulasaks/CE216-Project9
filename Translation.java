import java.util.*;
public class Translation {
    private String sourceLanguage; // the language of the original word
    private String targetLanguage; // the language of the translated word
    private String sourceWord; // the original word
    private String targetWord; // the translated word

    // Constructor
    public Translation(String sourceLanguage, String targetLanguage, String sourceWord, String targetWord) {
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
        this.sourceWord = sourceWord;
        this.targetWord = targetWord;
    }

    // Getters and setters
    public String getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(String sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public String getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(String targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public String getSourceWord() {
        return sourceWord;
    }

    public void setSourceWord(String sourceWord) {
        this.sourceWord = sourceWord;
    }

    public String getTargetWord() {
        return targetWord;
    }

    public void setTargetWord(String targetWord) {
        this.targetWord = targetWord;
    }
}