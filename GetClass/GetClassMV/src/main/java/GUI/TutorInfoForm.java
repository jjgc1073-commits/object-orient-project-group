package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TutorInfoForm {

    // Cambié TextField a TextArea para "About Me" porque suele ser texto largo
    public TextArea txtAboutMe;
    public ListView<String> subjects;
    public TextField txtcert;
    public TextField txtprice;
    public TextField txtLocated;

    public Button btnRegister;
    public Button btnBack; // Botón opcional para volver

    private Scene scene;

    public TutorInfoForm (Stage stage){

        stage.setTitle("Tutor Profile - GetClasses");

        // 1. FONDO PRINCIPAL (Lavanda)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F3E5F5;");

        // ScrollPane para asegurar que todo quepa en pantallas pequeñas
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #F3E5F5; -fx-background-color: #F3E5F5;");

        // 2. TARJETA BLANCA
        VBox card = new VBox(20);
        card.setMaxWidth(500);
        card.setPadding(new Insets(40));
        card.setAlignment(Pos.TOP_CENTER);

        // Estilo Tarjeta
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        // --- HEADER ---
        VBox header = new VBox(5);
        header.setAlignment(Pos.CENTER);

        Label stepLabel = new Label("Step 2 of 2");
        stepLabel.setStyle("-fx-text-fill: #9C27B0; -fx-font-weight: bold; -fx-font-size: 12px;");

        Label title = new Label("Complete your Profile");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");

        Label subtitle = new Label("Tell students why they should choose you.");
        subtitle.setStyle("-fx-text-fill: #757575; -fx-font-size: 14px;");

        header.getChildren().addAll(stepLabel, title, subtitle);

        // --- FORMULARIO ---
        VBox form = new VBox(15);
        form.setAlignment(Pos.CENTER_LEFT);

        // 1. ABOUT ME
        Label lblAboutMe = createLabel("About Me / Bio");
        txtAboutMe = new TextArea();
        txtAboutMe.setPromptText("Hi! I have over 5 years of experience teaching...");
        txtAboutMe.setPrefHeight(100);
        txtAboutMe.setWrapText(true);
        txtAboutMe.setStyle("-fx-control-inner-background: white; -fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5;");

        form.getChildren().addAll(lblAboutMe, txtAboutMe);

        // 2. SUBJECTS
        Label lblSubjects = createLabel("Subjects you teach");
        Label lblTip = new Label("(Hold Ctrl/Cmd to select multiple)");
        lblTip.setStyle("-fx-font-size: 11px; -fx-text-fill: #9E9E9E; -fx-font-style: italic;");

        subjects = new ListView<>();
        subjects.getItems().setAll("Math", "Physics", "Chemistry", "English", "Programming", "Literature", "History",
                "Music", "Personal Training", "Sports", "Geography", "Social Studies",
                "Religion", "Art", "Philosophy", "Biology", "Economics");
        subjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        subjects.setPrefHeight(150); // Altura fija para que no sea enorme
        subjects.setStyle("-fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

        form.getChildren().addAll(lblSubjects, lblTip, subjects);

        // 3. CERTIFICATES
        Label lblcert = createLabel("Certifications (comma separated)");
        txtcert = createStyledTextField("e.g. TOEFL, BSc Mathematics, PMP...");
        form.getChildren().addAll(lblcert, txtcert);

        // 4. PRICE & LOCATION (En una misma fila para ahorrar espacio)
        HBox rowDetails = new HBox(15);

        // Price Wrapper
        VBox boxPrice = new VBox(5);
        Label lblPrice = createLabel("Hourly Rate ($)");
        txtprice = createStyledTextField("20");
        // Lógica de validación (preservada)
        txtprice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return;
            }
            if (!newValue.matches("\\d*")) {
                txtprice.setText(oldValue);
            }
        });
        boxPrice.getChildren().addAll(lblPrice, txtprice);
        HBox.setHgrow(boxPrice, Priority.ALWAYS);

        // Location Wrapper
        VBox boxLoc = new VBox(5);
        Label lblLocation = createLabel("Location / City");
        txtLocated = createStyledTextField("e.g. New York, Online");
        boxLoc.getChildren().addAll(lblLocation, txtLocated);
        HBox.setHgrow(boxLoc, Priority.ALWAYS);

        rowDetails.getChildren().addAll(boxPrice, boxLoc);
        form.getChildren().add(rowDetails);

        // --- BOTÓN ---
        btnRegister = new Button("FINISH REGISTRATION");
        btnRegister.setPrefHeight(45);
        btnRegister.setMaxWidth(Double.MAX_VALUE);
        btnRegister.setCursor(Cursor.HAND);
        btnRegister.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;");

        // Hover
        btnRegister.setOnMouseEntered(e -> btnRegister.setStyle("-fx-background-color: #8E24AA; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;"));
        btnRegister.setOnMouseExited(e -> btnRegister.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;"));

        // ENSAMBLAJE
        card.getChildren().addAll(header, form, new Region(), btnRegister); // Region como espaciador

        // Margen inferior para que no quede pegado al scroll
        StackPane.setMargin(card, new Insets(40));
        root.getChildren().add(card);

        // ESCENA
        scene = new Scene(scrollPane, 550, 750);
        stage.setScene(scene);
    }

    // --- MÉTODOS AUXILIARES ---

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #616161; -fx-font-size: 13px;");
        return lbl;
    }

    private TextField createStyledTextField(String prompt) {
        TextField txt = new TextField();
        txt.setPromptText(prompt);
        txt.setPrefHeight(40);
        txt.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Focus color
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) txt.setStyle("-fx-background-color: white; -fx-border-color: #7B1FA2; -fx-border-radius: 5; -fx-background-radius: 5;");
            else txt.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");
        });
        return txt;
    }

    public Scene getScene() {
        return scene;
    }
}