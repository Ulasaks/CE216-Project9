import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
public class Translation {
    private String fromLanguage; // the language of the original word
    private String toLanguage; // the language of the translated word

    private Map<String, Map<String, List<String>>> data;
    private String[] languages;
    private String basePath;

    public Translation(){}

    public Translation(String[] languages, String basePath) {
        this.languages = languages;
        this.basePath = basePath;
        this.data = new HashMap<>();
        loadDictionaries();
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

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public void loadDictionaries() {
        for (String language : languages) {
            String filePath = basePath + File.separator + language + ".txt";
            try {
                List<String> lines = Files.readAllLines(Paths.get(filePath));
                Map<String, List<String>> wordMap = new HashMap<>();
                for (String line : lines) {
                    String[] parts = line.split("\t");
                    String word = parts[0].trim();
                    String[] translations = parts[1].split(",");
                    List<String> translationList = new ArrayList<>();
                    for (String translation : translations) {
                        translationList.add(translation.trim());
                    }
                    wordMap.put(word, translationList);
                }
                data.put(language, wordMap);
            } catch (IOException e) {
                System.err.println("Could not load dictionary file for " + language);
            }
        }
    }

    public List<String> translate(String word, String fromLanguage, String toLanguage) {
        if (!data.containsKey(fromLanguage) || !data.containsKey(toLanguage)) {
            return new ArrayList<>();
        }
        Map<String, List<String>> fromWordMap = data.get(fromLanguage);
        if (!fromWordMap.containsKey(word)) {
            return new ArrayList<>();
        }
        List<String> translations = fromWordMap.get(word);
        List<String> result = new ArrayList<>();
        for (String translation : translations) {
            Map<String, List<String>> toWordMap = data.get(toLanguage);
            for (Map.Entry<String, List<String>> entry : toWordMap.entrySet()) {
                if (entry.getValue().contains(translation)) {
                    result.add(entry.getKey() + ": " + entry.getValue());
                }
            }
        }
        return result;
    }



}