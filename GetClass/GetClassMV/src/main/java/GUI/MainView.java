package GUI;

import GUI.Controller.MainController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainView {

    private Stage stage;
    private BorderPane root;
    private Scene scene;

    // Ahora el panel se declara como atributo accesible
    private TutorTabsPanel tutorTabsPanel;

    public MainView(Stage stage) {
        this.stage = stage;
        this.stage.setTitle("GetClasses");

        root = new BorderPane();
        scene = new Scene(root, 900, 700);

        addHeader();
        addSearchAndCategories();

        // Se crea vacío, sin cargar datos todavía
        tutorTabsPanel = new TutorTabsPanel();
        tutorTabsPanel.setPrefSize(900, 450);
        root.setBottom(tutorTabsPanel);

        MainController controller = new MainController(this);


        tutorTabsPanel.initialize(controller);

    }

    private void addHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);

        Label title = new Label("Find Your Ideal Tutor!!");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        header.getChildren().add(title);
        root.setTop(header);
    }

    private void addSearchAndCategories() {
        VBox searchPanel = new VBox();
        searchPanel.setSpacing(10);
        searchPanel.setPadding(new Insets(10));
        searchPanel.setAlignment(Pos.CENTER);

        // Barra de búsqueda
        HBox topSearch = new HBox();
        topSearch.setSpacing(5);
        topSearch.setAlignment(Pos.CENTER);

        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Search subjects...");
        txtSearch.setPrefWidth(300);

        Button btnSearch = new Button("🔍");
        topSearch.getChildren().addAll(txtSearch, btnSearch);

        // Categorías
        HBox categoriesPanel = new HBox();
        categoriesPanel.setSpacing(5);
        categoriesPanel.setAlignment(Pos.CENTER);

        String[] cats = {"Training", "Math", "English", "Dance", "Guitar",
                "Programming", "Piano", "Finance"};

        for (String c : cats) {
            Button btn = new Button(c);
            categoriesPanel.getChildren().add(btn);
        }

        searchPanel.getChildren().addAll(topSearch, categoriesPanel);
        root.setCenter(searchPanel);
    }

    // GETTERS necesarios para el controlador

    public TutorTabsPanel getTutorTabsPanel() {
        return tutorTabsPanel;
    }

    public Stage getStage() {
        return stage;
    }

    public Scene getScene(){
        return scene;
    }

}
