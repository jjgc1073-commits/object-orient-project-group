package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import GUI.Controller.*;

import java.awt.event.WindowEvent;

public class MainView {

    public MainController mainController;
    private Stage stage;
    private BorderPane root;
    private Scene scene;

    public MainView(Stage stage) {
        this.mainController = new MainController(this);
        this.stage = stage;
        this.stage.setTitle("GetClasses");


        root = new BorderPane();
        scene = new Scene(root, 900, 700);

        addHeader();
        addSearchAndCategories();
        addTutorTabs(); // Aquí llamamos al panel de tutores

        this.stage.setScene(scene);
        this.stage.show();
    }

    private void addHeader() {
        HBox header = new HBox();
        header.setPadding(new Insets(10));
        header.setSpacing(10);

        Label title = new Label("Find Your Ideal Tutor!!");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        header.getChildren().add(title);
        root.setTop(header);
    }

    private void addSearchAndCategories() {
        VBox searchPanel = new VBox();
        searchPanel.setSpacing(10);
        searchPanel.setPadding(new Insets(10));

        // Barra de búsqueda
        HBox topSearch = new HBox();
        topSearch.setSpacing(5);

        TextField txtSearch = new TextField();
        txtSearch.setPromptText("Search subjects...");
        txtSearch.setPrefWidth(300);

        Button btnSearch = new Button("🔍");
        topSearch.getChildren().addAll(txtSearch, btnSearch);

        // Categorías
        HBox categoriesPanel = new HBox();
        categoriesPanel.setSpacing(5);
        String[] cats = {"Training", "Math", "English", "Dance", "Guitar",
                "Programming", "Piano", "Finance"};

        for (String c : cats) {
            Button btn = new Button(c);
            categoriesPanel.getChildren().add(btn);
        }

        searchPanel.getChildren().addAll(topSearch, categoriesPanel);
        root.setCenter(searchPanel);
    }

    private void addTutorTabs() {
        TutorTabsPanel tabs = new TutorTabsPanel(mainController);
        tabs.setPrefSize(900, 450);
        root.setBottom(tabs);

        VBox placeholder = new VBox();
        placeholder.setPadding(new Insets(10));
        placeholder.setStyle("-fx-background-color: #e0e0e0;");
        placeholder.setPrefHeight(450);

        Label label = new Label("Aquí irá el panel de tutores");
        placeholder.getChildren().add(label);

        root.setBottom(placeholder);
    }

    public Stage getStage() {
        return stage;
    }

}
