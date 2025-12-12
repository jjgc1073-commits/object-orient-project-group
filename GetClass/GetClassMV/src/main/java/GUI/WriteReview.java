package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class WriteReview extends BorderPane {

    // ---- CONTROLES QUE MANEJARÁ EL CONTROLADOR ----
    public ImageView imgTutorProfile;
    public Label lblTutorName;
    public Label lblClassDate;
    public Label lblClassSubject;
    public HBox ratingStarsContainer;
    public TextArea txtComment;
    public FlowPane tagSuggestionsContainer;
    public Button btnSkip;
    public Button btnSend;

    // Contenedor principal
    public BorderPane root;

    public WriteReview(Stage stage) {

        root = this;
        this.setPadding(new Insets(20));

        // Contenedor principal de secciones (Header + Body)
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        this.setCenter(mainLayout);


        // =========================================================================
        // 1. HEADER Y RATING (Fondo morado claro en la imagen)
        // =========================================================================

        HBox headerAndRating = new HBox(40);
        headerAndRating.setAlignment(Pos.CENTER);
        headerAndRating.setPadding(new Insets(20));

        // --- A. Imagen e Info del Tutor ---
        HBox tutorInfoBox = new HBox(20);
        tutorInfoBox.setAlignment(Pos.CENTER_LEFT);

        imgTutorProfile = new ImageView();
        imgTutorProfile.setFitWidth(100);
        imgTutorProfile.setFitHeight(100);

        VBox textInfoBox = new VBox(5);

        lblTutorName = new Label("Name Tutor");
        lblClassDate = new Label("CLASS DATE:");
        lblClassSubject = new Label("CLASS SUBJECT:");

        // Contenedor de Tags del Curso
        FlowPane subjectTags = new FlowPane(5, 5);
        subjectTags.getChildren().addAll(
                new Label("Maths"), new Label("Programming"), new Label("Finance")
        );

        textInfoBox.getChildren().addAll(lblTutorName, lblClassDate, lblClassSubject, subjectTags);
        tutorInfoBox.getChildren().addAll(imgTutorProfile, textInfoBox);

        // --- B. Rating (Estrellas) ---
        ratingStarsContainer = new HBox(5);
        // Añadir 5 placeholders de estrella
        for (int i = 0; i < 5; i++) {
            ratingStarsContainer.getChildren().add(new Label("★"));
        }

        headerAndRating.getChildren().addAll(tutorInfoBox, ratingStarsContainer);
        HBox.setHgrow(tutorInfoBox, Priority.ALWAYS);

        // =========================================================================
        // 2. COMENTARIO Y SUGERENCIAS (Cajas separadas)
        // =========================================================================

        HBox bodySection = new HBox(20);
        bodySection.setPrefHeight(300);

        // --- A. Sección de Comentario (Izquierda, caja grande) ---
        VBox commentBox = new VBox(10);
        commentBox.setPadding(new Insets(20));

        Label commentLabel = new Label("Comment:");

        txtComment = new TextArea();
        txtComment.setPromptText("Escribe tu reseña aquí...");
        VBox.setVgrow(txtComment, Priority.ALWAYS);

        commentBox.getChildren().addAll(commentLabel, txtComment);
        HBox.setHgrow(commentBox, Priority.ALWAYS);

        // --- B. Sección de Tags Sugeridos (Derecha, caja más pequeña) ---
        VBox tagsBox = new VBox(10);
        tagsBox.setPadding(new Insets(20));
        tagsBox.setPrefWidth(250);

        tagSuggestionsContainer = new FlowPane(10, 10);

        // Tags sugeridos placeholders
        tagSuggestionsContainer.getChildren().addAll(
                new Label("+ Good Explanation"),
                new Label("Punctual")
        );

        tagsBox.getChildren().add(tagSuggestionsContainer);

        bodySection.getChildren().addAll(commentBox, tagsBox);

        // =========================================================================
        // 3. BOTONES DE ACCIÓN (Parte Inferior)
        // =========================================================================

        HBox actionButtons = new HBox(20);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(10, 0, 0, 0));

        btnSkip = new Button("Skip");
        btnSkip.setPrefWidth(120);

        btnSend = new Button("Send");
        btnSend.setPrefWidth(120);

        actionButtons.getChildren().addAll(btnSkip, btnSend);

        // =========================================================================
        // 4. ENSAMBLAR
        // =========================================================================

        mainLayout.getChildren().addAll(headerAndRating, bodySection);
        this.setBottom(actionButtons);
    }
}