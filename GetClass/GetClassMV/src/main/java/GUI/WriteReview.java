package GUI;

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
import javafx.scene.paint.Color;

import java.util.List;

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
        // 1. CONFIGURACIÓN GENERAL (Fondo Lavanda)
        // -------------------------------------------------------------------------
        this.setPrefSize(1000, 650);
        this.setPadding(new Insets(30)); // Padding externo
        this.setStyle("-fx-background-color: #F3E5F5;"); // Fondo lavanda general

        // -------------------------------------------------------------------------
        // 2. LAYOUT TIPO TARJETA (Fondo Blanco Central)
        // -------------------------------------------------------------------------
        VBox mainLayout = new VBox(20);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setFillWidth(true);
        mainLayout.setPadding(new Insets(30)); // Padding interno de la tarjeta

        // Estilo de tarjeta flotante
        mainLayout.setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 15; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);"
        );

        this.setCenter(mainLayout);

        // =========================================================================
        // 1. HEADER + RATING
        // =========================================================================

        HBox headerAndRating = new HBox(40);
        headerAndRating.setAlignment(Pos.CENTER_LEFT);

        // --- Tutor Info ---
        HBox tutorInfoBox = new HBox(20);
        tutorInfoBox.setAlignment(Pos.CENTER_LEFT);

        imgTutorProfile = new ImageView();
        imgTutorProfile.setFitWidth(100);
        imgTutorProfile.setFitHeight(100);
        // Placeholder circular lila
        imgTutorProfile.setStyle("-fx-background-color: #E1BEE7; -fx-background-radius: 60; -fx-border-radius: 60;");

        VBox textInfoBox = new VBox(8);

        lblTutorName = new Label(tutor.getName() + " " + tutor.getLastName()+ ", " + tutor.getAge());
        // Nombre morado oscuro
        lblTutorName.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");

        lblClassDate = new Label("CLASS DATE: " + DateNow);
        lblClassDate.setStyle("-fx-text-fill: #757575; -fx-font-weight: bold;");

        lblClassSubject = new Label("Select Subject:");
        lblClassSubject.setStyle("-fx-text-fill: #424242;");

        FlowPane subjectTags = new FlowPane(8, 8);

        List<String> subjects =
                (tutor.tutorInfo.getSubjects() != null && !tutor.tutorInfo.getSubjects().isEmpty())
                        ? tutor.tutorInfo.getSubjects()
                        : List.of("Maths", "Programming", "Finance");

        for (String subject : subjects) {
            Label tagLabel = new Label(subject);
            // Estilo inicial de tags (Lila claro)
            tagLabel.setStyle("-fx-background-color: #EDE7F6; -fx-text-fill: #5E35B1; -fx-background-radius: 15; -fx-padding: 5 12; -fx-cursor: hand; -fx-font-weight: bold;");
            tagLabel.setOnMouseClicked(e -> handleSubjectSelection(tagLabel));
            subjectTags.getChildren().add(tagLabel);
        }

        textInfoBox.getChildren().addAll(lblTutorName, lblClassDate, lblClassSubject, subjectTags);
        tutorInfoBox.getChildren().addAll(imgTutorProfile, textInfoBox);

        // =========================================================================
        // ⭐ RATING (Estrellas Doradas)
        // =========================================================================

        VBox ratingBox = new VBox(5);
        ratingBox.setAlignment(Pos.CENTER_RIGHT);

        ratingStarsContainer = new HBox(5);
        ratingStarsContainer.setAlignment(Pos.CENTER);

        // Crear 5 estrellas
        for (int i = 1; i <= 5; i++) {
            Label star = new Label("☆");
            // Color gris claro inicialmente
            star.setStyle("-fx-font-size: 35px; -fx-cursor: hand; -fx-text-fill: #BDBDBD;");
            int index = i;
            star.setOnMouseClicked(e -> updateRating(index));
            ratingStarsContainer.getChildren().add(star);
        }

        // Texto debajo de estrellas
        lblRatingValue = new Label("Tap to rate");
        lblRatingValue.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #757575;");

        ratingBox.getChildren().addAll(ratingStarsContainer, lblRatingValue);

        headerAndRating.getChildren().addAll(tutorInfoBox, ratingBox);
        HBox.setHgrow(tutorInfoBox, Priority.ALWAYS);

        // =========================================================================
        // 2. COMENTARIO + TAGS
        // =========================================================================

        HBox bodySection = new HBox(30);
        bodySection.setPrefHeight(300);
        bodySection.setAlignment(Pos.TOP_LEFT);

        // --- Comentarios ---
        VBox commentBox = new VBox(10);
        // commentBox.setPadding(new Insets(10)); // Ya tiene padding el padre

        Label commentLabel = new Label("Write a review:");
        commentLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #4A148C; -fx-font-size: 16px;");

        txtComment = new TextArea();
        txtComment.setPromptText("How was your experience with " + tutor.getName() + "?");
        txtComment.setWrapText(true);
        // Estilo del TextArea: Focus morado
        txtComment.setStyle("-fx-control-inner-background: white; -fx-background-color: white; -fx-border-color: #D1C4E9; -fx-border-radius: 5; -fx-focus-color: #7B1FA2; -fx-faint-focus-color: transparent;");

        VBox.setVgrow(txtComment, Priority.ALWAYS);
        commentBox.getChildren().addAll(commentLabel, txtComment);
        HBox.setHgrow(commentBox, Priority.ALWAYS);

        // --- Suggested Tags ---
        VBox tagsBox = new VBox(15);
        tagsBox.setPrefWidth(280);
        tagsBox.setMinWidth(280);
        // Fondo gris muy suave para separar visualmente las sugerencias
        tagsBox.setStyle("-fx-background-color: #FAFAFA; -fx-background-radius: 10; -fx-padding: 15;");

        tagSuggestionsContainer = new FlowPane(10, 10);
        Label suggestionLabel = new Label("Quick Tags (Click to add):");
        suggestionLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #616161;");
        tagsBox.getChildren().add(suggestionLabel);

        List<String> suggestedTags = List.of(
                "+ Good Explanation",
                "+ Punctual",
                "+ Clear Teaching",
                "+ Helpful",
                "+ Friendly",
                "+ Expert"
        );

        for (String tag : suggestedTags) {
            Label tagLabel = new Label(tag);
            // Estilo Pill Morado Suave
            tagLabel.setStyle("-fx-background-color: #F3E5F5; -fx-text-fill: #7B1FA2; -fx-background-radius: 15; -fx-padding: 5 10; -fx-cursor: hand; -fx-border-color: #E1BEE7; -fx-border-radius: 15;");

            // Hover effect
            tagLabel.setOnMouseEntered(e -> tagLabel.setStyle("-fx-background-color: #E1BEE7; -fx-text-fill: #4A148C; -fx-background-radius: 15; -fx-padding: 5 10; -fx-cursor: hand; -fx-border-color: #BA68C8; -fx-border-radius: 15;"));
            tagLabel.setOnMouseExited(e -> tagLabel.setStyle("-fx-background-color: #F3E5F5; -fx-text-fill: #7B1FA2; -fx-background-radius: 15; -fx-padding: 5 10; -fx-cursor: hand; -fx-border-color: #E1BEE7; -fx-border-radius: 15;"));

            tagLabel.setOnMouseClicked(e -> handleTagSuggestionClick(tag.replace("+ ", "")));
            tagSuggestionsContainer.getChildren().add(tagLabel);
        }

        tagsBox.getChildren().add(tagSuggestionsContainer);
        bodySection.getChildren().addAll(commentBox, tagsBox);

        // =========================================================================
        // 3. BOTONES
        // =========================================================================

        HBox actionButtons = new HBox(20);
        actionButtons.setAlignment(Pos.CENTER);
        actionButtons.setPadding(new Insets(20, 0, 0, 0));

        btnSkip = new Button("Skip");
        btnSkip.setPrefWidth(120);
        btnSkip.setPrefHeight(40);
        // Botón gris
        btnSkip.setStyle("-fx-font-size: 16px; -fx-background-color: #E0E0E0; -fx-text-fill: #616161; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;");
        btnSkip.setOnMouseEntered(e -> btnSkip.setStyle("-fx-font-size: 16px; -fx-background-color: #BDBDBD; -fx-text-fill: #424242; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;"));
        btnSkip.setOnMouseExited(e -> btnSkip.setStyle("-fx-font-size: 16px; -fx-background-color: #E0E0E0; -fx-text-fill: #616161; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;"));


        btnSend = new Button("Submit Review");
        btnSend.setPrefWidth(180);
        btnSend.setPrefHeight(40);
        // Botón Morado Principal
        btnSend.setStyle("-fx-font-size: 16px; -fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;");
        btnSend.setOnMouseEntered(e -> btnSend.setStyle("-fx-font-size: 16px; -fx-background-color: #8E24AA; -fx-text-fill: white; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;"));
        btnSend.setOnMouseExited(e -> btnSend.setStyle("-fx-font-size: 16px; -fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 20; -fx-cursor: hand; -fx-font-weight: bold;"));

        actionButtons.getChildren().addAll(btnSkip, btnSend);

        // =========================================================================
        // ENSAMBLAJE
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
                star.setStyle("-fx-font-size: 35px; -fx-cursor: hand; -fx-text-fill: #FBC02D;"); // Dorado lleno
            } else {
                star.setText("☆");
                star.setStyle("-fx-font-size: 35px; -fx-cursor: hand; -fx-text-fill: #BDBDBD;"); // Gris vacío
            }
        }

        lblRatingValue.setText(String.format("%.1f / 5.0", currentRating));
        System.out.println("Nuevo Rating: " + currentRating);
    }

    // =========================================================================
    // SUBJECT SELECTION
    // =========================================================================
    private void handleSubjectSelection(Label newSelectedTag) {

        // Estilos
        String unselectedStyle = "-fx-background-color: #EDE7F6; -fx-text-fill: #5E35B1; -fx-background-radius: 15; -fx-padding: 5 12; -fx-cursor: hand; -fx-font-weight: bold;";
        String selectedStyle = "-fx-background-color: #7B1FA2; -fx-text-fill: white; -fx-background-radius: 15; -fx-padding: 5 12; -fx-cursor: hand; -fx-font-weight: bold; -fx-effect: dropshadow(three-pass-box, rgba(123, 31, 162, 0.4), 5, 0, 0, 2);";

        if (selectedSubjectTag != null) {
            selectedSubjectTag.setStyle(unselectedStyle);
        }

        if (selectedSubjectTag != newSelectedTag) {
            newSelectedTag.setStyle(selectedStyle);
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