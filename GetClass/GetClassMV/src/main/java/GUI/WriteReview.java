package GUI;

import DataBase.DAOS.UserTeacherDAO;
import java.time.LocalDate;
import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.ArrayList;
import javafx.scene.input.MouseEvent;

public class WriteReview extends BorderPane {

    // ---- CONTROLES QUE MANEJARÁ EL CONTROLADOR ----
    public ImageView imgTutorProfile;
    public Label lblTutorName;
    public Label lblClassDate;
    public Label lblClassSubject;
    public HBox ratingStarsContainer;
    public Label lblRatingValue;      
    public TextArea txtComment;
    public FlowPane tagSuggestionsContainer;
    public Button btnSkip;
    public Button btnSend;

    public final int id;
    public final UserTeacherDTO tutor;
    public Stage stage;
    public Scene scene;
    LocalDate DateNow = LocalDate.now();

    // Para manejar qué subject está seleccionado
    private Label selectedSubjectTag = null;

    // Valor numérico de rating
    private double currentRating = 0.0;

    public WriteReview(Stage stage, int id) {

        this.id = id;
        this.stage = stage;

        TutorProfile tut = new TutorProfile(id);
        this.tutor = tut.defineTutor();

        // -------------------------------------------------------------------------
        // 1. CAMBIO CLAVE: Definir tamaño ancho por defecto
        // -------------------------------------------------------------------------
        this.setPrefSize(1000, 650); 
        this.setPadding(new Insets(20));

        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        
        // -------------------------------------------------------------------------
        // 2. CAMBIO CLAVE: Asegurar que el layout vertical ocupe todo el ancho
        // -------------------------------------------------------------------------
        mainLayout.setFillWidth(true);
        
        this.setCenter(mainLayout);

        // =========================================================================
        // 1. HEADER + RATING
        // =========================================================================

        HBox headerAndRating = new HBox(40);
        headerAndRating.setAlignment(Pos.CENTER_LEFT); // Alineado a la izquierda
        headerAndRating.setPadding(new Insets(10));

        // --- Tutor Info ---
        HBox tutorInfoBox = new HBox(20);
        tutorInfoBox.setAlignment(Pos.CENTER_LEFT);

        imgTutorProfile = new ImageView();
        imgTutorProfile.setFitWidth(100);
        imgTutorProfile.setFitHeight(100);
        imgTutorProfile.setStyle("-fx-background-color: lightgray; -fx-border-radius: 60; -fx-background-radius: 60;"); 

        VBox textInfoBox = new VBox(5);

        lblTutorName = new Label(tutor.getName() + " " + tutor.getLastName()+ ", " + tutor.getAge());
        lblTutorName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;"); // Un poco más grande
        
        lblClassDate = new Label("CLASS DATE: " + DateNow);
        lblClassSubject = new Label("CLASS SUBJECT:");

        FlowPane subjectTags = new FlowPane(5, 5);

        List<String> subjects =
                (tutor.tutorInfo.getSubjects() != null && !tutor.tutorInfo.getSubjects().isEmpty())
                        ? tutor.tutorInfo.getSubjects()
                        : List.of("Maths", "Programming", "Finance");

        for (String subject : subjects) {
            Label tagLabel = new Label(subject);
            tagLabel.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 3 8; -fx-cursor: hand;");
            tagLabel.setOnMouseClicked(e -> handleSubjectSelection(tagLabel));
            subjectTags.getChildren().add(tagLabel);
        }

        textInfoBox.getChildren().addAll(lblTutorName, lblClassDate, lblClassSubject, subjectTags);
        tutorInfoBox.getChildren().addAll(imgTutorProfile, textInfoBox);

        // =========================================================================
        // ⭐ RATING (Estrellas Interactivas + Texto 0.0 - 5.0)
        // =========================================================================

        VBox ratingBox = new VBox(5);
        ratingBox.setAlignment(Pos.CENTER_RIGHT); // Alineado a la derecha

        ratingStarsContainer = new HBox(5);
        ratingStarsContainer.setAlignment(Pos.CENTER);

        // Crear 5 estrellas iniciales (vacías)
        for (int i = 1; i <= 5; i++) {
            Label star = new Label("☆");
            star.setStyle("-fx-font-size: 30px; -fx-cursor: hand; -fx-text-fill: #f0c419;"); // Color dorado opcional
            int index = i; // para usar dentro del lambda
            star.setOnMouseClicked(e -> updateRating(index));
            ratingStarsContainer.getChildren().add(star);
        }

        // Texto debajo de estrellas
        lblRatingValue = new Label("Rating: 0.0 / 5.0");
        lblRatingValue.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        ratingBox.getChildren().addAll(ratingStarsContainer, lblRatingValue);

        headerAndRating.getChildren().addAll(tutorInfoBox, ratingBox);
        
        // Esto empuja el rating hacia la derecha cuando la ventana es ancha
        HBox.setHgrow(tutorInfoBox, Priority.ALWAYS);

        // =========================================================================
        // 2. COMENTARIO + TAGS
        // =========================================================================

        HBox bodySection = new HBox(20);
        bodySection.setPrefHeight(300);
        bodySection.setAlignment(Pos.TOP_LEFT);

        // --- Comentarios ---
        VBox commentBox = new VBox(10);
        commentBox.setPadding(new Insets(10));
        
        Label commentLabel = new Label("Comment:");
        commentLabel.setStyle("-fx-font-weight: bold;");

        txtComment = new TextArea();
        txtComment.setPromptText("Escribe tu reseña aquí...");
        txtComment.setWrapText(true); // Permite que el texto baje de línea
        
        VBox.setVgrow(txtComment, Priority.ALWAYS);

        commentBox.getChildren().addAll(commentLabel, txtComment);
        
        // Esto hace que el área de comentario ocupe todo el ancho sobrante
        HBox.setHgrow(commentBox, Priority.ALWAYS);

        // --- Tags ---
        VBox tagsBox = new VBox(10);
        tagsBox.setPadding(new Insets(10));
        tagsBox.setPrefWidth(250);
        tagsBox.setMinWidth(250); // Fija el ancho del panel derecho

        tagSuggestionsContainer = new FlowPane(10, 10);
        Label suggestionLabel = new Label("Suggested Tags:");
        suggestionLabel.setStyle("-fx-font-weight: bold;");
        tagsBox.getChildren().add(suggestionLabel);

        List<String> suggestedTags = List.of(
                "+ Good Explanation",
                "Punctual",
                "Clear Teaching",
                "Helpful",
                "Friendly"
        );

        for (String tag : suggestedTags) {
            Label tagLabel = new Label(tag);
            tagLabel.setStyle("-fx-background-color: #f2cdf8ff; -fx-border-color: #e384f0ff; -fx-border-radius: 5; -fx-padding: 3 8; -fx-cursor: hand;");
            tagLabel.setOnMouseClicked(e -> handleTagSuggestionClick(tag));
            tagSuggestionsContainer.getChildren().add(tagLabel);
        }

        tagsBox.getChildren().add(tagSuggestionsContainer);
        bodySection.getChildren().addAll(commentBox, tagsBox);

        // =========================================================================
        // 3. BOTONES
        // =========================================================================

        HBox actionButtons = new HBox(20);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(10, 0, 10, 0));

        btnSkip = new Button("Skip");
        btnSkip.setPrefWidth(120);
        btnSkip.setStyle("-fx-font-size: 16px; -fx-background-color: #8e8290ff; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand;");

        btnSend = new Button("Send");
        btnSend.setPrefWidth(120);
        btnSend.setStyle("-fx-font-size: 16px; -fx-background-color: #4f1cc7; -fx-text-fill: white; -fx-background-radius: 5; -fx-cursor: hand;");

        actionButtons.getChildren().addAll(btnSkip, btnSend);

        // =========================================================================
        // Finalmente ensamblar todo
        // =========================================================================

        mainLayout.getChildren().addAll(headerAndRating, bodySection);
        this.setBottom(actionButtons);
    }

    // =========================================================================
    // ⭐ MÉTODO PARA MANEJAR LAS ESTRELLAS
    // =========================================================================
    private void updateRating(int starsSelected) {

        currentRating = starsSelected;

        for (int i = 0; i < 5; i++) {
            Label star = (Label) ratingStarsContainer.getChildren().get(i);
            if (i < starsSelected) {
                star.setText("★");
            } else {
                star.setText("☆");
            }
        }

        lblRatingValue.setText(String.format("Rating: %.1f / 5.0", currentRating));
        System.out.println("Nuevo Rating: " + currentRating);
    }

    // =========================================================================
    // SUBJECT SELECTION
    // =========================================================================
    private void handleSubjectSelection(Label newSelectedTag) {

        if (selectedSubjectTag != null) {
            selectedSubjectTag.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5; -fx-padding: 3 8; -fx-cursor: hand;");
        }

        if (selectedSubjectTag != newSelectedTag) {
            newSelectedTag.setStyle("-fx-border-color: #4CAF50; -fx-background-color: #DCEDC8; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 3 8; -fx-cursor: hand; -fx-font-weight: bold;");
            selectedSubjectTag = newSelectedTag;
            System.out.println("Subject Seleccionado: " + selectedSubjectTag.getText());
        } else {
            selectedSubjectTag = null;
            System.out.println("Subject Deseleccionado");
        }
    }

    // =========================================================================
    // TAG SUGGESTIONS
    // =========================================================================
    private void handleTagSuggestionClick(String tagText) {
        String currentText = txtComment.getText();
        String textToAdd = tagText;

        if (currentText != null && !currentText.trim().isEmpty()) {
            if (!currentText.endsWith(" ") && !currentText.endsWith("\n")) {
                textToAdd = " " + tagText;
            }
        }

        txtComment.appendText(textToAdd);
        System.out.println("Tag Añadido: " + tagText);
    }
}