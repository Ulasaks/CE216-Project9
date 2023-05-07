import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GUI extends Application {
    private Dictionary dictionary;
    private TextField searchInputField;
    private ListView<Word> searchResultsListView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        dictionary = new Dictionary();

        primaryStage.setTitle("Dictionary");

        GridPane gridPane = createGridPane();
        addSearchComponents(gridPane);
        addSearchResultListView(gridPane);

        Scene scene = new Scene(gridPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(5);
        gridPane.setHgap(5);
        return gridPane;
    }

    private void addSearchComponents(GridPane gridPane) {
        Label searchLabel = new Label("Search:");
        GridPane.setConstraints(searchLabel, 0, 0);

        searchInputField = new TextField();
        GridPane.setConstraints(searchInputField, 1, 0);
        searchInputField.setPrefWidth(300);

        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 2, 0);
        searchButton.setOnAction(event -> searchWord());

        gridPane.getChildren().addAll(searchLabel, searchInputField, searchButton);
    }

    private void addSearchResultListView(GridPane gridPane) {
        searchResultsListView = new ListView<>();
        GridPane.setConstraints(searchResultsListView, 0, 1, 3, 1);
        searchResultsListView.setOnMouseClicked(event -> showWordDetails());

        gridPane.getChildren().add(searchResultsListView);
    }

    private void searchWord() {
        String query = searchInputField.getText();
        if (query.isEmpty()) {
            showAlert("Search Error", "Please enter a word to search.");
            return;
        }

        searchResultsListView.getItems().clear();

        for (Word word : dictionary.searchWord(query)) {
            searchResultsListView.getItems().add(word);
        }
    }

    private void showWordDetails() {
        Word selectedWord = searchResultsListView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            String details = selectedWord.getDetails();
            showAlert("Word Details", details);
        }
    }

    private void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}








