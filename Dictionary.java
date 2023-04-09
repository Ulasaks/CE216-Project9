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

        Scanner sc=new Scanner(System.in);
        Translation translation1= new Translation();
        System.out.println("Welcome to dictionary ...");


        System.out.println("Enter a source Language:");
        String language1 = sc.nextLine();
        System.out.println("Enter a target language");
        String language2 = sc.nextLine();
        System.out.println("Enter a word");
        String word = sc.nextLine();
        translation1.translate(word,language1,language2);

    }



}
