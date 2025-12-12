package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProfileView extends BorderPane {

    // ---- CONTROLES QUE MANEJARÁ EL CONTROLADOR ----
    public ImageView imgProfile;
    public Label lblName;
    public Label lblLocation;
    public Label lblHourlyRate; // ¡CORREGIDO! Ahora inicializado correctamente
    public Label lblAboutMe;
    public VBox subjectsContainer;
    public VBox certificationsContainer; // Usado para la sección de Reviews

    // Nuevas etiquetas introducidas en la última estructura
    public Label lblCost;
    public Label lblResponseTime;

    // Botones (solo declarados, el controlador no interactúa con ellos aquí)
    public Button btnSchedule;
    public Button btnContact;
    public Button btnRate;

    // Contenedor raíz de la vista (necesario para la clase App)
    public BorderPane root;


    public ProfileView(Stage stage) {

        root = this;
        this.setPadding(new Insets(10));

        // Contenedor principal para la división Izquierda/Derecha
        HBox mainContent = new HBox(10);
        this.setCenter(mainContent);

        // =========================================================================
        // 1. PANEL IZQUIERDO (Información del Tutor, About, Reviews)
        // =========================================================================

        VBox leftPanel = new VBox(20);
        leftPanel.setPadding(new Insets(30));
        leftPanel.setPrefWidth(700);

        // --- HEADER: Título y Tags ---
        Label backArrow = new Label("<");


        HBox header = new HBox(10, backArrow);
        header.setAlignment(Pos.CENTER_LEFT);

        // Contenedor de Tags
        HBox tagsContainer = createTagsBox();

        // --- SECCIÓN: About Me ---
        VBox aboutSection = new VBox(5);
        Label aboutTitle = new Label("About me");

        // Inicialización de lblAboutMe
        lblAboutMe = new Label("Descripción del tutor...");
        lblAboutMe.setWrapText(true);
        lblAboutMe.setPrefHeight(70);
        aboutSection.getChildren().addAll(aboutTitle, lblAboutMe);


        // --- SECCIÓN: How I teach ---
        VBox teachSection = new VBox(5);
        Label teachTitle = new Label("How I teach");
        // Inicialización de subjectsContainer
        subjectsContainer = new VBox(5);
        subjectsContainer.setPrefHeight(70);
        teachSection.getChildren().addAll(teachTitle, subjectsContainer);

        // --- SECCIÓN: Reviews ---
        VBox reviewsSection = new VBox(10);
        reviewsSection.setPadding(new Insets(15));

        Label reviewsTitle = new Label("Reviews");

        // Contenido de la review (simulado)
        HBox studentHeader = new HBox(10);
        ImageView studentImg = new ImageView();
        studentImg.setFitWidth(30);
        studentImg.setFitHeight(30);
        Label studentName = new Label("Student  ★★★★★");
        studentHeader.getChildren().addAll(studentImg, studentName);

        Label reviewText = new Label("[Contenido de la Review]");
        reviewText.setWrapText(true);

        Button readMoreBtn = new Button("Read more");

        // Inicialización de certificationsContainer
        certificationsContainer = new VBox(reviewText);

        HBox reviewAction = new HBox(readMoreBtn);

        reviewsSection.getChildren().addAll(
                reviewsTitle, studentHeader, certificationsContainer, reviewAction
        );
        reviewsSection.setAlignment(Pos.CENTER_LEFT);

        // Añadir todas las secciones al panel izquierdo
        leftPanel.getChildren().addAll(
                header, tagsContainer,
                aboutSection,
                teachSection,
                reviewsSection
        );


        // =========================================================================
        // 2. PANEL DERECHO (Foto del Tutor, Datos Clave, Botones de Acción)
        // =========================================================================

        VBox rightPanel = new VBox(15);
        rightPanel.setPrefWidth(300);
        rightPanel.setAlignment(Pos.TOP_CENTER);
        rightPanel.setPadding(new Insets(30, 10, 10, 10));

        // --- HEADER DERECHO (Get Classes) ---
        Label getClasses = new Label("Get Classes");
        HBox rightHeader = new HBox(getClasses);
        rightHeader.setAlignment(Pos.TOP_RIGHT);

        // --- IMAGEN DEL TUTOR ---
        imgProfile = new ImageView();
        imgProfile.setFitWidth(150);
        imgProfile.setFitHeight(150);

        // --- DATOS BÁSICOS ---
        // Inicialización de lblName
        lblName = new Label("Name Tutor Age");
        Label rate = new Label("RATE ★");

        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(10, 0, 10, 0));

        // Fila 1: Cost/Hour
        HBox costRow = new HBox(10);
        lblCost = new Label("COST/HOUR");
        // *** ESTA ES LA CORRECCIÓN CLAVE: Asignamos la instancia a lblHourlyRate ***
        lblHourlyRate = new Label("$");
        costRow.getChildren().addAll(lblCost, lblHourlyRate);

        // Fila 2: Response Time
        HBox responseRow = new HBox(10);
        lblResponseTime = new Label("RESPONSE TIME");
        Label responseValue = new Label("#H");
        responseRow.getChildren().addAll(lblResponseTime, responseValue);

        // Fila 3: Location
        HBox locationRow = new HBox(10);
        lblLocation = new Label("LOCATED IN");
        Label locationValue = new Label("XXXXXX");
        locationRow.getChildren().addAll(lblLocation, locationValue);

        infoBox.getChildren().addAll(costRow, responseRow, locationRow);

        // --- BOTONES DE ACCIÓN ---
        VBox actionButtons = new VBox(15);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(20, 0, 0, 0));

        btnSchedule = new Button("SHCEDULE A CLASS");
        btnSchedule.setPrefWidth(200);
        btnContact = new Button("CONTACT");
        btnContact.setPrefWidth(200);
        btnRate = new Button("RATE");
        btnRate.setPrefWidth(200);

        actionButtons.getChildren().addAll(btnSchedule, btnContact, btnRate);

        rightPanel.getChildren().addAll(
                rightHeader,
                imgProfile,
                lblName,
                rate,
                infoBox,
                actionButtons
        );
        VBox.setVgrow(infoBox, Priority.ALWAYS);

        // =========================================================================
        // 3. ENSAMBLAR
        // =========================================================================

        mainContent.getChildren().addAll(leftPanel, rightPanel);
        HBox.setHgrow(leftPanel, Priority.ALWAYS);
    }

    // --- MÉTODOS AUXILIARES ---

    private HBox createTagsBox() {
        HBox tags = new HBox(10);
        tags.setAlignment(Pos.CENTER_LEFT);
        tags.setPadding(new Insets(0, 0, 10, 0));

        tags.getChildren().addAll(
                new Button("Maths"),
                new Button("Programming"),
                new Button("Finance")
        );
        return tags;
    }
}