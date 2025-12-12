package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class LoginForm {

    // Atributos públicos para el controlador
    public TextField txtUser;
    public PasswordField txtPass;
    public Button btnLogin;
    public Label linkRegister;

    private Scene scene;

    public LoginForm(Stage stage) {

        stage.setTitle("Login - GetClasses");

        // 1. FONDO PRINCIPAL (Lavanda)
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #F3E5F5;");
        root.setPadding(new Insets(20));

        // 2. TARJETA DE LOGIN (Blanca y centrada)
        VBox card = new VBox(20); // Espaciado vertical de 20px
        card.setAlignment(Pos.CENTER);
        card.setMaxWidth(380);
        card.setPadding(new Insets(40));

        // Estilo de la tarjeta: Blanca, bordes redondeados, sombra
        card.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 10; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        // --- ICONO / HEADER ---
        VBox headerBox = new VBox(10);
        headerBox.setAlignment(Pos.CENTER);

        // Círculo decorativo (simula logo)
        StackPane logoContainer = new StackPane();
        Circle bgCircle = new Circle(30, Color.web("#E1BEE7")); // Lila claro
        Label iconLabel = new Label("🔒");
        iconLabel.setStyle("-fx-font-size: 24px;");
        logoContainer.getChildren().addAll(bgCircle, iconLabel);

        Label lblTitle = new Label("Welcome Back");
        lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4A148C;"); // Morado oscuro

        Label lblSubtitle = new Label("Sign in to continue");
        lblSubtitle.setStyle("-fx-text-fill: #757575;");

        headerBox.getChildren().addAll(logoContainer, lblTitle, lblSubtitle);

        // --- FORMULARIO (Inputs apilados) ---
        VBox formBox = new VBox(15);
        formBox.setAlignment(Pos.CENTER_LEFT);

        // Usuario
        Label lblUser = new Label("Email Address");
        lblUser.setStyle("-fx-font-weight: bold; -fx-text-fill: #616161;");

        txtUser = new TextField();
        txtUser.setPromptText("example@email.com");
        txtUser.setPrefHeight(40);
        // Estilo Input
        txtUser.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

        // Contraseña
        Label lblPass = new Label("Password");
        lblPass.setStyle("-fx-font-weight: bold; -fx-text-fill: #616161;");

        txtPass = new PasswordField();
        txtPass.setPromptText("Enter your password");
        txtPass.setPrefHeight(40);
        txtPass.setStyle("-fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-background-radius: 5;");

        formBox.getChildren().addAll(lblUser, txtUser, lblPass, txtPass);

        // --- BOTÓN LOGIN ---
        btnLogin = new Button("LOGIN");
        btnLogin.setPrefHeight(45);
        btnLogin.setMaxWidth(Double.MAX_VALUE); // Ocupar todo el ancho
        btnLogin.setCursor(Cursor.HAND);

        // Estilo Botón Morado
        btnLogin.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5;");

        // Efecto Hover
        btnLogin.setOnMouseEntered(e -> btnLogin.setStyle("-fx-background-color: #8E24AA; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5;"));
        btnLogin.setOnMouseExited(e -> btnLogin.setStyle("-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5;"));

        // --- FOOTER (Registro) ---
        HBox footerBox = new HBox(5);
        footerBox.setAlignment(Pos.CENTER);

        Label lblNew = new Label("Don't have an account?");
        lblNew.setStyle("-fx-text-fill: #757575;");

        linkRegister = new Label("Sign up");
        linkRegister.setCursor(Cursor.HAND);
        // Link en morado vibrante
        linkRegister.setStyle("-fx-text-fill: #9C27B0; -fx-font-weight: bold; -fx-underline: true;");

        footerBox.getChildren().addAll(lblNew, linkRegister);

        // ENSAMBLAJE
        card.getChildren().addAll(headerBox, formBox, btnLogin, footerBox);
        root.getChildren().add(card);

        // ESCENA (Un poco más grande para que respire el diseño)
        scene = new Scene(root, 500, 600);
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}