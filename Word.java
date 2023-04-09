import java.util.*;

public class Word {
    private String name, code;
    private HashMap<String, String> Dictionary;
    private HashMap<String, String> newLanguage;

    private Map<String, Map<String, List<String>>> data;
    private String[] languages;
    private String basePath;


    Word word=new Word();


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDictionary(HashMap<String, String> dictionary) {
        Dictionary = dictionary;
    }

    public HashMap<String, String> getDictionary() {
        return Dictionary;
    }

    public void setNewLanguage(HashMap<String, String> newLanguage) {
        this.newLanguage = newLanguage;
    }

    public HashMap<String, String> getNewLanguage() {
        return newLanguage;
    }

    public Word(){
        getName();
        getCode();
        getDictionary();
        getNewLanguage();
    }

    public Word(String name, String code, HashMap<String, String> newLanguage){
        setName(getName());
        setCode(getCode());
        setDictionary(getDictionary());
        setNewLanguage(getNewLanguage());
    }

    public void addWord(String word, String translation, String language) {
        if (!data.containsKey(language)) {
            data.put(language, new HashMap<>());
        }
        Map<String, List<String>> wordMap = data.get(language);
        if (!wordMap.containsKey(word)) {
            wordMap.put(word, new ArrayList<>());
        }
        List<String> translationList = wordMap.get(word);
        if (!translationList.contains(translation)) {
            translationList.add(translation);
        }
    }

    public void editWord(String oldWord, String newWord, String language) {
        if (!data.containsKey(language)) {
            return;
        }
        Map<String, List<String>> wordMap = data.get(language);
        if (!wordMap.containsKey(oldWord)) {
            return;
        }
        List<String> translations = wordMap.remove(oldWord);
        wordMap.put(newWord, translations);
    }
    public void deleteWord(String word) {
        if (this.getDictionary().containsKey(word)) {
            this.getDictionary().remove(word);
        }
    }

    public void findWord(String name){
        if (getName().contains(name))
            System.out.println(getName());
    }
}