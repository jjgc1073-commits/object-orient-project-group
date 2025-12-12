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
        // Color de fondo general (Lavanda muy suave)
        root.setStyle("-fx-background-color: #F3E5F5;");

        scene = new Scene(root, 900, 700);

        addHeader();
        addSearchAndCategories();

        // Se crea vacío, sin cargar datos todavía
        tutorTabsPanel = new TutorTabsPanel();
        tutorTabsPanel.setPrefSize(900, 450);

        // Opcional: Si TutorTabsPanel hereda de Region, podrías ponerle fondo blanco
        // tutorTabsPanel.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        root.setBottom(tutorTabsPanel);

        MainController controller = new MainController(this);
        tutorTabsPanel.initialize(controller);
    }

    private void addHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(15)); // Un poco más de padding
        header.setSpacing(10);
        header.setAlignment(Pos.CENTER);

        // Estilo de la cabecera (Morado Oscuro)
        header.setStyle("-fx-background-color: #4A148C; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 5);");

        Label title = new Label("Find Your Ideal Tutor!!");
        // Texto blanco para contrastar con el fondo oscuro
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Segoe UI';");

        header.getChildren().add(title);
        root.setTop(header);
    }

    private void addSearchAndCategories() {
        VBox searchPanel = new VBox();
        searchPanel.setSpacing(15);
        searchPanel.setPadding(new Insets(20));
        searchPanel.setAlignment(Pos.CENTER);

        // Barra de búsqueda
        HBox topSearch = new HBox();
        topSearch.setSpacing(10);
        topSearch.setAlignment(Pos.CENTER);

        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Search subjects...");
        txtSearch.setPrefWidth(300);
        txtSearch.setPrefHeight(35);
        // Estilo del campo de texto (Borde morado y fondo blanco)
        txtSearch.setStyle("-fx-background-color: white; -fx-border-color: #7B1FA2; -fx-border-radius: 5; -fx-background-radius: 5;");

        Button btnSearch = new Button("🔍");
        btnSearch.setPrefHeight(35);
        // Botón de búsqueda (Morado medio)
        btnSearch.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand; -fx-font-weight: bold;");

        topSearch.getChildren().addAll(txtSearch, btnSearch);

        // Categorías
        HBox categoriesPanel = new HBox();
        categoriesPanel.setSpacing(10);
        categoriesPanel.setAlignment(Pos.CENTER);

        String[] cats = {"Training", "Math", "English", "Dance", "Guitar",
                "Programming", "Piano", "Finance"};

        for (String c : cats) {
            Button btn = new Button(c);
            // Estilo de botones de categoría (Morado vibrante, redondeados tipo 'pastilla')
            btn.setStyle(
                    "-fx-background-color: #8E24AA; " +
                            "-fx-text-fill: white; " +
                            "-fx-background-radius: 20; " + // Bordes muy redondeados
                            "-fx-padding: 8 15 8 15; " +      // Padding interno para que se vean más grandes
                            "-fx-cursor: hand; " +
                            "-fx-font-weight: bold;"
            );

            // Efecto simple al pasar el mouse (Hover) usando eventos de Java
            btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #AB47BC; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 15 8 15; -fx-cursor: hand; -fx-font-weight: bold;"));
            btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #8E24AA; -fx-text-fill: white; -fx-background-radius: 20; -fx-padding: 8 15 8 15; -fx-cursor: hand; -fx-font-weight: bold;"));

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