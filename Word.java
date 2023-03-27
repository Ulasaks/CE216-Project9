import java.util.*;

public class Word {
    private String name, code;
    private HashMap<String, String> Dictionary;
    private HashMap<String, String> newLanguage;

    Language language=new Language();

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

    public void createWord(String name, String language){
        this.newLanguage.put(name, language);
    }

    public void editWord(String oldName, String name, String language){
        if (this.newLanguage.containsKey(oldName)){
            this.newLanguage.remove(oldName);
            this.newLanguage.put(name, language);
        }
    }

    public void findLanguage(String name){
        if (this.newLanguage.containsKey(name))
            System.out.println(newLanguage);
    }
}
