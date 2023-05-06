
import java.util.List;

public class Synonym {
    private String word;
    private Language language;
    private List<String> synonyms;

    public Synonym(String word, Language language, List<String> synonyms) {
        this.word = word;
        this.language = language;
        this.synonyms = synonyms;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }
}