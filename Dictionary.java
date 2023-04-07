import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

    private Map<String, Map<String, List<String>>> data;
    private String[] languages;
    private String basePath;
    public Dictionary(String[] languages, String basePath) {
        this.languages = languages;
        this.basePath = basePath;
        this.data = new HashMap<>();
        loadDictionaries();
    }

    private void loadDictionaries() {
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