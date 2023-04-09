import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Dictionary {
    public static void main(String[] args) throws FileNotFoundException {
        // Load the dictionary file
        File dictFile = new File("dictionary.txt");
        Scanner scanner = new Scanner(dictFile);


        // Parse the dictionary into a map
        Map<String, String> dict = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("=");
            String word = parts[0].trim();
            String translation = parts[1].trim();
            dict.put(word, translation);
        }

        // Test the translation
        String input = "hello";
        String translation = dict.get(input);
        System.out.println("Translation of " + input + ": " + translation);

    }
}
