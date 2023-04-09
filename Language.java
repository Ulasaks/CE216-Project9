import java.util.*;

public class Language {

    private HashMap<String, String> newLanguage;
    Language language=new Language();
    public void findLanguage(String name){
        if (this.newLanguage.containsKey(name))
            System.out.println(newLanguage);
    }
}