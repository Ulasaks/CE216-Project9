import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.List;

public class GUI extends Application {

    private Dictionary dictionary;

    public GUI(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the title of the window
        primaryStage.setTitle("Dictionary");

        // Create the search label and text field
        Label searchLabel = new Label("Search:");
        TextField searchTextField = new TextField();
        searchTextField.setPrefWidth(300);

        // Create the search button
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchButtonHandler(searchTextField.getText()));

        // Create the add word button
        Button addWordButton = new Button("Add Word");
        addWordButton.setOnAction(e -> showAddWordDialog());

        // Create the search results list view
        ListView<Word> searchResultsListView = new ListView<>();

        // Create the grid pane to hold the search controls
        GridPane searchGridPane = new GridPane();
        searchGridPane.setHgap(10);
        searchGridPane.setVgap(10);
        searchGridPane.setPadding(new Insets(10));
        searchGridPane.add(searchLabel, 0, 0);
        searchGridPane.add(searchTextField, 1, 0);
        searchGridPane.add(searchButton, 2, 0);
        searchGridPane.add(searchResultsListView, 0, 1, 3, 1);

        // Create the main VBox to hold all the controls
        VBox mainVBox = new VBox();
        mainVBox.setPadding(new Insets(10));
        mainVBox.setSpacing(10);
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.getChildren().addAll(searchGridPane, addWordButton);

        // Create the scene
        Scene scene = new Scene(mainVBox, 500, 500);

        // Set the scene
        primaryStage.setScene(scene);

        // Show the window
        primaryStage.show();
    }

    private void searchButtonHandler(String searchWord) {
        List<Word> searchResults = dictionary.searchWord(searchWord);
        showSearchResults(searchResults);
    }

    public void showSearchResults(List<Word> searchResults) {
        // Get the search results list view
        ListView<Word> searchResultsListView = (ListView<Word>) ((VBox) ((GridPane) ((ScrollPane) ((VBox) ((Scene) searchResultsListView.getScene()).getRoot()).getChildren().get(0)).getContent()).getChildren().get(0)).getChildren().get(1);

        // Clear the search results list view
        searchResultsListView.getItems().clear();

        // Add the search results to the search results list view
        searchResultsListView.getItems().addAll(searchResults);
    }

    public void showAddWordDialog() {
        // Create the add word dialog
        Dialog<Word> addWordDialog = new Dialog<>();
        addWordDialog.setTitle("Add Word");

        // Create the add word dialog labels and text fields
        Label spellingLabel = new Label("Spelling:");
        TextField spellingTextField = new TextField();
        Label languageLabel = new Label("Language:");
        ChoiceBox<Language> languageChoiceBox = new ChoiceBox<>();
        languageChoiceBox.getItems().addAll(dictionary.getLanguages());
        languageChoiceBox.setValue(dictionary.getLanguages().get(0));
        Label translationLabel = new Label("Translation:");
        TextField translationTextField = new TextField();

        // Create the add word dialog grid pane and add the labels and text fields
        GridPane addWordGridPane = new GridPane();
        addWordGridPane.setHgap(10);
        addWordGridPane.setVgap(10);
        addWordGridPane.setPadding(new Insets(10));
        addWordGridPane.add(spellingLabel, 0, 0);
        addWordGridPane.add(spellingTextField, 1, 0);
        addWordGridPane.add(languageLabel, 0, 1);
        addWordGridPane.add(languageChoiceBox, 1, 1);
        addWordGridPane.add(translationLabel, 0, 2);
        addWordGridPane.add(translationTextField, 1, 2);

        // Disable the add button until all fields are filled in
        Node addButtonNode = addWordDialog.getDialogPane().lookupButton(addButton);
        addButtonNode.setDisable(true);
        spellingTextField.textProperty().addListener((observable, oldValue, newValue) -> addButtonNode.setDisable(newValue.trim().isEmpty() || translationTextField.getText().trim().isEmpty()));

        // Set the add word dialog content
        addWordDialog.getDialogPane().setContent(addWordGridPane);

        // Request focus on the spelling text field when the dialog is shown
        Platform.runLater(spellingTextField::requestFocus);

        // Convert the result to a Word object when the add button is clicked
        addWordDialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                Word word = new Word(spellingTextField.getText(), new Translation(languageChoiceBox.getValue(), translationTextField.getText()));
                dictionary.addWord(word);
                return word;
            }
            return null;
        });

        // Show the add word dialog
        addWordDialog.showAndWait();
    }
}
