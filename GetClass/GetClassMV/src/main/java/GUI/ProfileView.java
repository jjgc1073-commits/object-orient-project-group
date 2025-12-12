package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.util.Locale;

public class ProfileView {

    public final int id;
    public final UserTeacherDTO tutor;
    public Stage stage;
    public Scene scene;

    // Controles accesibles
    public Button btnBack;
    public Button btnRate;
    public Button btnBook;
    public VBox westPanelContent;
    public VBox eastPanel;

    // Controles internos
    public Label lblNameTitle;
    public HBox subjectsContainer;
    public TextArea txtAboutMe;

    public ProfileView(Stage stage, int id) {
        this.id = id;
        this.stage = stage;

        // 1. CARGA DE DATOS
        TutorProfile tut = new TutorProfile(id);
        this.tutor = tut.defineTutor();

        // VALIDACIÓN
        if (this.tutor == null) {
            handleTutorNotFound();
            return;
        }

        // 2. CONFIGURACIÓN DEL STAGE
        stage.setTitle("GetClasses - Perfil de " + this.tutor.getName() + " " + this.tutor.getLastName());
        stage.setResizable(true);

        // 3. ESTRUCTURA PRINCIPAL
        BorderPane root = new BorderPane();
        // Fondo general Lavanda
        root.setStyle("-fx-background-color: #F3E5F5;");

        HBox mainContent = new HBox(0); // Sin espacio, el padding lo manejan los hijos

        // 4. CREACIÓN DE PANELES
        eastPanel = createEastPanel();
        ScrollPane scrollWest = createWestPanel();

        mainContent.getChildren().addAll(scrollWest, eastPanel);
        HBox.setHgrow(scrollWest, Priority.ALWAYS);
        root.setCenter(mainContent);

        // 5. ESCENA
        this.scene = new Scene(root, 950, 700);
        this.stage.setScene(scene);
    }

    private void handleTutorNotFound() {
        this.stage.setTitle("Error: Tutor no encontrado");
        BorderPane errorRoot = new BorderPane();
        Label errorLabel = new Label("Error: No se pudo encontrar el perfil del tutor con ID " + id);
        errorLabel.setStyle("-fx-font-size: 1.5em; -fx-padding: 50; -fx-text-fill: #D32F2F;"); // Rojo oscuro
        errorRoot.setCenter(errorLabel);
        this.scene = new Scene(errorRoot, 400, 300);
        this.stage.setScene(scene);
    }

    // --- PANELES ---

    private VBox createEastPanel() {
        VBox panel = new VBox(20);
        panel.setPrefWidth(320);
        panel.setPadding(new Insets(40, 25, 25, 25));
        panel.setAlignment(Pos.TOP_CENTER);

        // Panel lateral Blanco con sombra sutil a la izquierda
        panel.setStyle(
                "-fx-background-color: white; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, -5, 0);"
        );

        Node header = addEastHeader();

        // Precio destacado
        Label priceLabel = new Label("$" + String.format(Locale.US, "%.2f", (double)tutor.tutorInfo.getHourlyRate()) + " / hour");
        priceLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #4A148C;"); // Morado oscuro

        // Botón Contact (Acción Principal)
        btnBook = new Button("Contact with me");
        btnBook.setMaxWidth(Double.MAX_VALUE);
        btnBook.setPrefHeight(45);
        btnBook.setCursor(Cursor.HAND);
        btnBook.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 30;");

        // Hover effect btnBook
        btnBook.setOnMouseEntered(e -> btnBook.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #8E24AA; -fx-text-fill: white; -fx-background-radius: 30;"));
        btnBook.setOnMouseExited(e -> btnBook.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 30;"));

        // Botón Rate (Acción Secundaria)
        btnRate = new Button("Rate me");
        btnRate.setMaxWidth(Double.MAX_VALUE);
        btnRate.setPrefHeight(45);
        btnRate.setCursor(Cursor.HAND);
        btnRate.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: #7B1FA2; -fx-background-radius: 30; -fx-border-color: #7B1FA2; -fx-border-radius: 30; -fx-border-width: 2;");

        // Hover effect btnRate
        btnRate.setOnMouseEntered(e -> btnRate.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: #F3E5F5; -fx-text-fill: #7B1FA2; -fx-background-radius: 30; -fx-border-color: #7B1FA2; -fx-border-radius: 30; -fx-border-width: 2;"));
        btnRate.setOnMouseExited(e -> btnRate.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: white; -fx-text-fill: #7B1FA2; -fx-background-radius: 30; -fx-border-color: #7B1FA2; -fx-border-radius: 30; -fx-border-width: 2;"));

        Region separator = new Region();
        VBox.setVgrow(separator, Priority.ALWAYS);

        panel.getChildren().addAll(header, priceLabel, separator, btnBook, btnRate);
        return panel;
    }

    private ScrollPane createWestPanel() {
        westPanelContent = createWestPanelContent();

        ScrollPane scrollWest = new ScrollPane(westPanelContent);
        scrollWest.setFitToWidth(true);
        // Fondo del scrollpane transparente para ver el fondo lavanda, o blanco.
        scrollWest.setStyle("-fx-background: #F3E5F5; -fx-background-color: #F3E5F5;");
        scrollWest.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollWest.setPrefWidth(600);
        scrollWest.setMinWidth(300);
        return scrollWest;
    }

    private VBox createWestPanelContent() {
        VBox content = new VBox(25);
        content.setPadding(new Insets(30, 40, 30, 40));
        // El contenido en sí no tiene fondo, flota sobre el lavanda

        content.getChildren().addAll(
                addWestHeader(),
                addAboutMe(),
                addReviews()
        );

        return content;
    }

    // --- COMPONENTES ---

    public Node addWestHeader() {
        VBox header = new VBox(15);
        header.setAlignment(Pos.CENTER);

        HBox topBar = new HBox();
        btnBack = new Button("← Back");
        // Botón de regreso simple y limpio
        btnBack.setStyle("-fx-background-color: transparent; -fx-text-fill: #6A1B9A; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand;");
        btnBack.setOnMouseEntered(e -> btnBack.setStyle("-fx-background-color: #E1BEE7; -fx-text-fill: #4A148C; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;"));
        btnBack.setOnMouseExited(e -> btnBack.setStyle("-fx-background-color: transparent; -fx-text-fill: #6A1B9A; -fx-font-size: 16px; -fx-font-weight: bold; -fx-cursor: hand;"));

        topBar.getChildren().add(btnBack);
        topBar.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().add(topBar);

        lblNameTitle = new Label("Hi! I'm " + tutor.getName() + " " + tutor.getLastName());
        lblNameTitle.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");

        header.getChildren().add(lblNameTitle);

        subjectsContainer = new HBox(10);
        subjectsContainer.setAlignment(Pos.CENTER);
        if (tutor.tutorInfo.getSubjects() != null) {
            for (String s : tutor.tutorInfo.getSubjects()) {
                Button badge = new Button(s);
                // Estilo "Pill" consistente con TutorCard
                badge.setStyle(
                        "-fx-background-color: #EDE7F6; " +
                                "-fx-text-fill: #5E35B1; " +
                                "-fx-background-radius: 20; " +
                                "-fx-font-size: 12px; " +
                                "-fx-padding: 5 15 5 15; -fx-font-weight: bold;"
                );
                subjectsContainer.getChildren().add(badge);
            }
        }
        header.getChildren().add(subjectsContainer);

        return header;
    }

    public Node addEastHeader(){
        VBox header = new VBox(15);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(10));

        Region photo = new Region();
        photo.setPrefSize(140, 140);
        // Círculo lila para la foto
        photo.setStyle("-fx-background-color: #E1BEE7; -fx-border-radius: 70; -fx-background-radius: 70;");

        Label nameLabel = new Label(tutor.getName() + " " + tutor.getLastName());
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #212121;");

        Label ageLabel = new Label(tutor.getAge() + " years old");
        ageLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #757575;");

        Label ratingLabel = new Label("★ " + String.format(Locale.US, "%.1f", (double)tutor.tutorInfo.getRating()));
        ratingLabel.setStyle("-fx-text-fill: #F9A825; -fx-font-weight: bold; -fx-font-size: 18px;"); // Estrella Dorada

        header.getChildren().addAll(photo, nameLabel, ageLabel, ratingLabel);
        return header;
    }

    public Node addAboutMe() {
        VBox aboutme = new VBox(15);
        // Estilo de tarjeta blanca para el bloque de texto
        aboutme.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
        aboutme.setAlignment(Pos.CENTER_LEFT);

        Label abouttittle = new Label("About Me");
        abouttittle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");

        txtAboutMe = new TextArea(tutor.tutorInfo.getAboutMe());
        txtAboutMe.setEditable(false);
        txtAboutMe.setWrapText(true);
        txtAboutMe.setPrefHeight(120);

        // TextArea limpio sin bordes feos
        txtAboutMe.setStyle("-fx-background-color: transparent; -fx-background-insets: 0; -fx-control-inner-background: white; -fx-text-fill: #424242; -fx-font-size: 14px;");

        aboutme.getChildren().addAll(abouttittle, txtAboutMe);
        return aboutme;
    }

    public Node addReviews(){
        VBox reviewSection = new VBox(15);
        // Tarjeta blanca para la sección de reviews
        reviewSection.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-padding: 20; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");

        int reviewCount = (tutor.tutorInfo.getReviews() != null) ? tutor.tutorInfo.getReviews().size() : 0;
        Label title = new Label("Student Reviews (" + reviewCount + ")");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");
        reviewSection.getChildren().add(title);

        ReviewsTabs reviewsTabs = new ReviewsTabs(tutor.getId());
        reviewsTabs.setPrefHeight(500);
        reviewsTabs.setMinHeight(300);
        // Asegurar que el fondo del componente interno sea transparente o blanco para que no choque
        reviewsTabs.setStyle("-fx-background-color: transparent;");

        reviewSection.getChildren().add(reviewsTabs);

        return reviewSection;
    }

    public Scene getScene(){
        return scene;
    }

}