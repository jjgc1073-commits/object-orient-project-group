package GUI;

import GUI.Controller.TutorCardListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;

public class TutorCard extends BorderPane {

    private int id;
    private TutorCardListener listener;

    public TutorCard(String name, int age, int id, String about, String[] subjects, double cost, double rating) {
        this.id = id;

        setPadding(new Insets(10));
        setPrefSize(800, 180);
        setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: white;");

        // --- FOTO ---
        Region photo = new Region();
        photo.setPrefSize(120, 120);
        photo.setStyle("-fx-background-color: lightgray;");
        photo.setCursor(Cursor.HAND);
        photo.setOnMouseClicked(e -> {
            if (listener != null) listener.onTutorClicked(id);
        });
        setLeft(photo);
        BorderPane.setMargin(photo, new Insets(0, 10, 0, 0));

        // --- PANEL CENTRAL (info y materias) ---
        VBox infoPanel = new VBox(5);

        Label nameLabel = new Label(name + " · " + age + " years");
        nameLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        nameLabel.setCursor(Cursor.HAND);
        nameLabel.setOnMouseClicked(e -> {
            if (listener != null) listener.onTutorClicked(id);
        });

        Label aboutLabel = new Label("About me:");
        TextArea aboutText = new TextArea(about);
        aboutText.setEditable(false);
        aboutText.setWrapText(true);
        aboutText.setPrefRowCount(3);
        aboutText.setMaxWidth(500);

        // Subjects buttons
        FlowPane subjectsPanel = new FlowPane(5, 5);
        for (String s : subjects) {
            Button btn = new Button(s);
            subjectsPanel.getChildren().add(btn);
        }

        infoPanel.getChildren().addAll(nameLabel, aboutLabel, aboutText, subjectsPanel);
        setCenter(infoPanel);

        // --- PANEL DERECHO (precio, rating, favorito) ---
        VBox rightPanel = new VBox(10);
        rightPanel.setPrefWidth(150);
        rightPanel.setAlignment(Pos.TOP_CENTER);

        Label costLabel = new Label("COST/HOUR: $" + cost);
        Button favButton = new Button("♥");
        Label ratingLabel = new Label("Rating: " + rating);

        rightPanel.getChildren().addAll(costLabel, favButton, ratingLabel);
        setRight(rightPanel);
    }

    public void setListener(TutorCardListener listener) {
        this.listener = listener;
    }
}
