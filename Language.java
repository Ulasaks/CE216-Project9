import java.util.*;

public class Language {
    private String name;
    private String code;
    private HashMap<String, String> dictionary;
    private char[] charSet;

    Word word=new Word();

    public Language(){
        getName();
        getCode();
        getCharSet();
        getDictionary();
        getWord();
    }

    public Language(String name, String code, char[] charSet) {
        this.name = name;
        this.code = code;
        this.charSet = charSet;
        this.dictionary = new HashMap<String, String>();
        this.word=new Word();
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public char[] getCharSet() {
        return this.charSet;
    }

    public void addWord(String word, String translation) {
        this.dictionary.put(word, translation);
    }

    public void editWord(String word, String newTranslation) {
        if (this.dictionary.containsKey(word)) {
            this.dictionary.put(word, newTranslation);
        }
    }

    public void deleteWord(String word) {
        if (this.dictionary.containsKey(word)) {
            this.dictionary.remove(word);
        }
    }

    public HashMap<String, String> getTranslations(String word, Language[] languages) {
        HashMap<String, String> translations = new HashMap<String, String>();
        for (Language language : languages) {
            if (language != this && language.getDictionary().containsKey(word)) {
                translations.put(language.getCode(), language.getDictionary().get(word));
            }
        }
        return translations;
    }

    public HashMap<String, String> getDictionary() {
        return this.dictionary;
    }

    public Word getWord() {
        return word;
    }

    public String findSynonyms(String word) {
        String synonyms = "";
        for (String key : this.dictionary.keySet()) {
            if (key != word && this.dictionary.get(key) == this.dictionary.get(word)) {
                synonyms += key + ", ";
            }
        }
        return synonyms;
    }
}