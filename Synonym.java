import java.util.*;
public class Synonym {
    private Map<String, Map<String, List<String>>> data;
    private String basePath;

    public Synonym(String basePath,Map<String, Map<String, List<String>>> data) {
        this.basePath = basePath;
        this.data = data;

    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Map<String, Map<String, List<String>>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, List<String>>> data) {
        this.data = data;
    }
    public List<String> getSynonyms(String word, String language) {
        if (!data.containsKey(language)) {
            return new ArrayList<>();
        }
        Map<String, List<String>> wordMap = data.get(language);
        List<String> synonyms = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : wordMap.entrySet()) {
            if (entry.getValue().contains(word) && !entry.getKey().equals(word)) {
                synonyms.add(entry.getKey());
            }
        }
        return synonyms;
    }
}
