package GUI;

import GUI.Controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegisterForm {

    public ChoiceBox<String> txtrol;
    public TextField txtName;
    public TextField txtLastName;
    public DatePicker txtbithdate;
    public TextField txtEmail;
    public TextField txtEmailC;
    public PasswordField txtPass;
    public PasswordField txtPassC;
    public Button btnRegister;
    // Agrego botón de volver o login para mejor navegación
    public Button btnBack;

    private Scene scene;

    public RegisterForm (Stage stage){

        stage.setTitle("Create Account - GetClasses");

        // 1. FONDO PRINCIPAL (Lavanda)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F3E5F5;");

        // Usamos un ScrollPane por si la pantalla es pequeña verticalmente
        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background: #F3E5F5; -fx-background-color: #F3E5F5;"); // Ocultar bordes del scroll

        // 2. TARJETA BLANCA
        VBox card = new VBox(20);
        card.setMaxWidth(480); // Un poco más ancho que el login
        card.setPadding(new Insets(40));
        card.setAlignment(Pos.CENTER);

        // Estilo Tarjeta
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        // --- HEADER ---
        VBox header = new VBox(5);
        header.setAlignment(Pos.CENTER);

        Label title = new Label("Create Account");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");

        Label subtitle = new Label("Join our community of tutors and students");
        subtitle.setStyle("-fx-text-fill: #757575; -fx-font-size: 14px;");

        header.getChildren().addAll(title, subtitle);

        // --- FORMULARIO (GRID) ---
        // Usamos GridPane para organizar mejor los campos
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(15);
        grid.setAlignment(Pos.CENTER);

        // -- Role (Full Width) --
        Label lblrol = createLabel("I want to be a:");
        txtrol = new ChoiceBox<>();
        txtrol.getItems().addAll("STUDENT", "TEACHER");
        txtrol.setValue("STUDENT"); // Valor por defecto
        txtrol.setMaxWidth(Double.MAX_VALUE);
        txtrol.setPrefHeight(35);
        txtrol.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

        grid.add(lblrol, 0, 0, 2, 1); // Col 0, Fila 0, Span 2 cols
        grid.add(txtrol, 0, 1, 2, 1);

        // -- Name & Last Name (Side by Side) --
        Label lblName = createLabel("Name");
        txtName = createStyledTextField("First Name");

        Label lblLastName = createLabel("Last Name");
        txtLastName = createStyledTextField("Last Name");

        grid.add(lblName, 0, 2);
        grid.add(txtName, 0, 3);

        grid.add(lblLastName, 1, 2);
        grid.add(txtLastName, 1, 3);

        // -- Birthdate (Full Width) --
        Label lblbirhdate = createLabel("Birth Date");
        txtbithdate = new DatePicker();
        txtbithdate.setMaxWidth(Double.MAX_VALUE);
        txtbithdate.setPrefHeight(35);
        txtbithdate.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5;");

        grid.add(lblbirhdate, 0, 4, 2, 1);
        grid.add(txtbithdate, 0, 5, 2, 1);

        // -- Email Section --
        Label lblEmail = createLabel("Email Address");
        txtEmail = createStyledTextField("example@mail.com");

        Label lblEmailC = createLabel("Confirm Email");
        txtEmailC = createStyledTextField("Confirm your email");

        grid.add(lblEmail, 0, 6, 2, 1);
        grid.add(txtEmail, 0, 7, 2, 1);

        grid.add(lblEmailC, 0, 8, 2, 1);
        grid.add(txtEmailC, 0, 9, 2, 1);

        // -- Password Section --
        Label lblPass = createLabel("Password");
        txtPass = createStyledPasswordField();

        Label lblPassC = createLabel("Confirm Password");
        txtPassC = createStyledPasswordField();

        grid.add(lblPass, 0, 10, 2, 1);
        grid.add(txtPass, 0, 11, 2, 1);

        grid.add(lblPassC, 0, 12, 2, 1);
        grid.add(txtPassC, 0, 13, 2, 1);

        // --- BOTÓN REGISTRO ---
        btnRegister = new Button("CREATE ACCOUNT");
        btnRegister.setPrefHeight(45);
        btnRegister.setMaxWidth(Double.MAX_VALUE);
        btnRegister.setCursor(Cursor.HAND);
        btnRegister.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;");

        // Hover
        btnRegister.setOnMouseEntered(e -> btnRegister.setStyle("-fx-background-color: #8E24AA; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;"));
        btnRegister.setOnMouseExited(e -> btnRegister.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 25;"));

        // --- FOOTER ---
        HBox footer = new HBox(5);
        footer.setAlignment(Pos.CENTER);
        Label lblLogin = new Label("Already have an account?");
        btnBack = new Button("Sign In"); // Puedes usar esto para volver al login
        btnBack.setStyle("-fx-background-color: transparent; -fx-text-fill: #9C27B0; -fx-font-weight: bold; -fx-underline: true; -fx-cursor: hand;");

        footer.getChildren().addAll(lblLogin, btnBack);

        // ENSAMBLAJE
        card.getChildren().addAll(header, grid, new Region(), btnRegister, footer); // Region es un espaciador
        root.getChildren().add(card);

        // ESCENA (Dimensiones más amplias para acomodar el formulario)
        scene = new Scene(scrollPane, 550, 700);
        stage.setScene(scene);
    }

    // --- MÉTODOS AUXILIARES PARA ESTILOS REPETITIVOS ---

    private Label createLabel(String text) {
        Label lbl = new Label(text);
        lbl.setStyle("-fx-font-weight: bold; -fx-text-fill: #616161; -fx-font-size: 12px;");
        return lbl;
    }

    private TextField createStyledTextField(String prompt) {
        TextField txt = new TextField();
        txt.setPromptText(prompt);
        txt.setPrefHeight(35);
        txt.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");
        // Focus color
        txt.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) txt.setStyle("-fx-background-color: white; -fx-border-color: #7B1FA2; -fx-border-radius: 5; -fx-background-radius: 5;");
            else txt.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");
        });
        return txt;
    }

    private PasswordField createStyledPasswordField() {
        PasswordField txt = new PasswordField();
        txt.setPromptText("••••••••");
        txt.setPrefHeight(35);
        txt.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

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