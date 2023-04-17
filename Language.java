import java.util.HashMap;

public class Language {

    private HashMap<String, String> newLanguage;

    public void findLanguage(String name){
        if (this.newLanguage.containsKey(name))
            System.out.println(newLanguage.get(name));
    }
}

