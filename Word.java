import java.util.*;

public class Word {
    private String name;
    private String code;
    private Map<String, String> dictionary;
    private Map<String, String> newLanguage;
    private Map<String, Map<String, List<String>>> translations;

    public Word(String name, String code, Map<String, String> dictionary, Map<String, String> newLanguage) {
        this.name = name;
        this.code = code;
        this.dictionary = dictionary;
        this.newLanguage = newLanguage;
        this.translations = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public Map<String, String> getNewLanguage() {
        return newLanguage;
    }

    public void addTranslation(String word, String translation, String language) {
        Map<String, List<String>> languageTranslations = translations.get(language);
        if (languageTranslations == null) {
            languageTranslations = new HashMap<>();
            translations.put(language, languageTranslations);
        }
        List<String> translationList = languageTranslations.get(word);
        if (translationList == null) {
            translationList = new ArrayList<>();
            languageTranslations.put(word, translationList);
        }
        if (!translationList.contains(translation)) {
            translationList.add(translation);
        }
    }

    public void editTranslation(String oldWord, String newWord, String language) {
        Map<String, List<String>> languageTranslations = translations.get(language);
        if (languageTranslations == null) {
            return;
        }
        List<String> translations = languageTranslations.remove(oldWord);
        if (translations != null) {
            languageTranslations.put(newWord, translations);
        }
    }

    public void deleteTranslation(String word, String language) {
        Map<String, List<String>> languageTranslations = translations.get(language);
        if (languageTranslations != null) {
            languageTranslations.remove(word);
        }
    }

    public List<String> findTranslations(String word, String language) {
        Map<String, List<String>> languageTranslations = translations.get(language);
        if (languageTranslations != null) {
            return languageTranslations.get(word);
        }
        return null;
    }
}