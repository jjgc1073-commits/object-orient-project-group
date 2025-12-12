package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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

        // VALIDACIÓN: Evitar NullPointerException si el ID no existe
        if (this.tutor == null) {
            handleTutorNotFound();
            return; 
        }

        // 2. CONFIGURACIÓN DEL STAGE
        stage.setTitle("GetClasses - Perfil de " + this.tutor.getName() + " " + this.tutor.getLastName());
        stage.setResizable(true);
        
        // 3. ESTRUCTURA PRINCIPAL
        BorderPane root = new BorderPane();
        HBox mainContent = new HBox(30); 

        // 4. CREACIÓN DE PANELES
        eastPanel = createEastPanel();
        ScrollPane scrollWest = createWestPanel();
        
        mainContent.getChildren().addAll(scrollWest, eastPanel);
        HBox.setHgrow(scrollWest, Priority.ALWAYS);
        root.setCenter(mainContent);
        
        // 5. ESCENA
        this.scene = new Scene(root, 900, 700); 
        this.stage.setScene(scene);
    }
    
    private void handleTutorNotFound() {
        this.stage.setTitle("Error: Tutor no encontrado");
        BorderPane errorRoot = new BorderPane();
        Label errorLabel = new Label("Error: No se pudo encontrar el perfil del tutor con ID " + id);
        errorLabel.setStyle("-fx-font-size: 1.5em; -fx-padding: 50; -fx-text-fill: red;");
        errorRoot.setCenter(errorLabel);
        this.scene = new Scene(errorRoot, 400, 300);
        this.stage.setScene(scene);
    }
    
    // --- PANELES ---

    private VBox createEastPanel() {
        VBox panel = new VBox(20); 
        panel.setPrefWidth(300);
        panel.setPadding(new Insets(30, 15, 15, 15));
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: lightgray; -fx-border-width: 0 0 0 1;");

        Node header = addEastHeader();
        
        // FIX: Locale.US y cast a double para evitar errores de formato
        Label priceLabel = new Label("$" + String.format(Locale.US, "%.2f", (double)tutor.tutorInfo.getHourlyRate()) + " / hour");
        priceLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #be0bf5;");
        
        btnBook = new Button("Contact with me");
        btnBook.setMaxWidth(Double.MAX_VALUE);
        btnBook.setPrefHeight(40);
        btnBook.setStyle("-fx-font-size: 16px; -fx-background-color: #4f1cc7; -fx-text-fill: white; -fx-background-radius: 5;");

        btnRate = new Button("Rate me");
        btnRate.setMaxWidth(Double.MAX_VALUE);
        btnRate.setPrefHeight(40);
        btnRate.setStyle("-fx-font-size: 16px; -fx-background-color: #4f1cc7; -fx-text-fill: white; -fx-background-radius: 5;");
        
        Region separator = new Region();
        VBox.setVgrow(separator, Priority.ALWAYS); 
        
        panel.getChildren().addAll(header, priceLabel, separator, btnBook, btnRate);
        return panel;
    }

    private ScrollPane createWestPanel() {
        westPanelContent = createWestPanelContent();
        ScrollPane scrollWest = new ScrollPane(westPanelContent);
        scrollWest.setFitToWidth(true); 
        scrollWest.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); 
        scrollWest.setPrefWidth(600); 
        scrollWest.setMinWidth(300); 
        return scrollWest;
    }

    private VBox createWestPanelContent() {
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        content.getChildren().addAll(
            addWestHeader(),
            addAboutMe(),
            // 🟢 AQUÍ LLAMAMOS A TU NUEVO MÉTODO
            addReviews()
        );
        
        return content;
    }

    // --- COMPONENTES ---

    public Node addWestHeader() {
        VBox header = new VBox(15);
        header.setAlignment(Pos.CENTER); 
        
        HBox topBar = new HBox();
        btnBack = new Button("←"); 
        topBar.getChildren().add(btnBack);
        topBar.setAlignment(Pos.CENTER_LEFT);
        header.getChildren().add(topBar);

        lblNameTitle = new Label("Hi! I'm " + tutor.getName() + " " + tutor.getLastName());
        lblNameTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        header.getChildren().add(lblNameTitle);
        
        subjectsContainer = new HBox(10); 
        subjectsContainer.setAlignment(Pos.CENTER); 
        if (tutor.tutorInfo.getSubjects() != null) {
            for (String s : tutor.tutorInfo.getSubjects()) {
                subjectsContainer.getChildren().add(new Button(s));
            }
        }
        header.getChildren().add(subjectsContainer);
        
        return header; 
    }

    public Node addEastHeader(){
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(30));

        Region photo = new Region();
        photo.setPrefSize(120, 120); // Ajustado para ser cuadrado
        photo.setStyle("-fx-background-color: lightgray; -fx-border-radius: 60; -fx-background-radius: 60;"); 
        
        Label nameLabel = new Label(tutor.getName() + " " + tutor.getLastName() + " - " + tutor.getAge());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // FIX: Locale.US y cast a double
        Label ratingLabel = new Label("Rate: " + String.format(Locale.US, "%.1f", (double)tutor.tutorInfo.getRating()));
        
        header.getChildren().addAll(photo, nameLabel, ratingLabel);
        return header;
    }

    public Node addAboutMe() {
        VBox aboutme = new VBox(10);
        aboutme.setPadding(new Insets(10)); 
        aboutme.setAlignment(Pos.CENTER_LEFT); 

        Label abouttittle = new Label("About Me:");
        abouttittle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        txtAboutMe = new TextArea(tutor.tutorInfo.getAboutMe());
        txtAboutMe.setEditable(false);
        txtAboutMe.setWrapText(true); 
        txtAboutMe.setPrefHeight(150); 
        txtAboutMe.setMaxHeight(Region.USE_PREF_SIZE); 

        aboutme.getChildren().addAll(abouttittle, txtAboutMe);
        return aboutme;
    }

    // 🟢 IMPLEMENTACIÓN CORRECTA DE ADDREVIEWS
    public Node addReviews(){
        VBox reviewSection = new VBox(15);
        reviewSection.setPadding(new Insets(10));

        // Título de la sección
        int reviewCount = (tutor.tutorInfo.getReviews() != null) ? tutor.tutorInfo.getReviews().size() : 0;
        Label title = new Label("Reseñas de Alumnos (" + reviewCount + ")");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        reviewSection.getChildren().add(title);

        // Instanciar tu componente ReviewsTabs
        // Este componente ya carga los datos internamente usando el ID del tutor
        ReviewsTabs reviewsTabs = new ReviewsTabs(tutor.getId());
        
        // ¡IMPORTANTE! Como está dentro de un ScrollPane (el panel general),
        // necesitamos darle una altura para que se muestre.
        reviewsTabs.setPrefHeight(500); 
        reviewsTabs.setMinHeight(300);

        reviewSection.getChildren().add(reviewsTabs);
        
        return reviewSection;
    }

    public Scene getScene(){
        return scene;
    }

}