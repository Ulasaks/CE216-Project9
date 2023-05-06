import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Dictionary {
    private static final String API_BASE_URL = "https://api.freedict.org/";
    private static final String API_VERSION = "v1";
    private static final String API_LANG_PAIR = "eng-ger";
    private static final String API_URL = API_BASE_URL + API_VERSION + "/search?dict=" + API_LANG_PAIR + "&query=";

    public static void main(String[] args) {
        try {
            // Load the dictionary file
            Map<String, String> dict = loadDictionary("dictionary.txt");

            // Test the translation
            String input = "hello";
            String translation = translateWord(dict, input);
            if (translation == null) {
                System.out.println("Translation of " + input + " not found.");
            } else {
                System.out.println("Translation of " + input + ": " + translation);
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to the dictionary...");

            System.out.println("Enter the source language:");
            String language1 = sc.nextLine();
            System.out.println("Enter the target language:");
            String language2 = sc.nextLine();
            System.out.println("Enter a word:");
            String word = sc.nextLine();

            String translationResult = translateWord(dict, word);
            if (translationResult == null) {
                System.out.println("Translation of " + word + " not found.");
            } else {
                System.out.println("Translation of " + word + " from " + language1 + " to " + language2 + ": " + translationResult);
            }
        } catch (IOException e) {
            System.err.println("Dictionary file not found.");
        }
    }

    private static Map<String, String> loadDictionary(String filePath) throws IOException {
        Map<String, String> dict = new HashMap<>();
        Files.lines(Paths.get(filePath)).forEach(line -> {
            String[] parts = line.split("=");
            String word = parts[0].trim();
            String wordTranslation = parts[1].trim();
            dict.put(word, wordTranslation);
        });
        return dict;
    }

    private static String sendGetRequest(String url) throws IOException {
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL apiUrl = new URL(url);
            connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return response.toString();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String translateWord(Map<String, String> dict, String word) throws IOException {
        // Check if word is in the loaded dictionary
        String translation = dict.get(word);
        if (translation != null) {
            return translation;
        }

        // If not found in dictionary, use FreeDict API
        String encodedQuery = URLEncoder.encode(word, StandardCharsets.UTF_8.toString());
        String apiUrl = API_URL + encodedQuery;
        String apiResponse = sendGetRequest(apiUrl);

        try {
            JSONArray results = new JSONArray(apiResponse);
            if (results.length() > 0) {
                JSONObject firstResult = results.getJSONObject(0);
                String[] translations = firstResult.getString("translation").split("\\|");
                return translations[0].trim();
            }
        } catch (JSONException e) {
            System.err.println("Failed to parse API response: " + apiResponse);
        }

        // If API does not return a result, return null
        return null;
    }
}
