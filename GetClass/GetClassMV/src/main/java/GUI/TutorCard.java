package GUI;

import GUI.Controller.TutorCardListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class TutorCard extends BorderPane {

    private int id;
    private TutorCardListener listener;

    public TutorCard(String name, String lastName, int age, int id, String about, String[] subjects, double cost, double rating) {
        this.id = id;

        setPadding(new Insets(15)); // Un poco más de aire
        setPrefSize(800, 180);

        // ESTILO TARJETA: Fondo blanco, bordes redondeados y una sombra suave
        setStyle(
                "-fx-background-color: white; " +
                        "-fx-background-radius: 10; " +
                        "-fx-border-radius: 10; " +
                        "-fx-border-color: #E0E0E0; " + // Borde gris muy sutil
                        "-fx-border-width: 1; " +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 0, 2);" // Sombra para profundidad
        );

        // --- FOTO ---
        Region photo = new Region();
        photo.setPrefSize(120, 120);
        // Placeholder de foto en color lila
        photo.setStyle("-fx-background-color: #E1BEE7; -fx-background-radius: 5;");
        photo.setCursor(Cursor.HAND);
        photo.setOnMouseClicked(e -> {
            if (listener != null) listener.onTutorClicked(id);
        });
        setLeft(photo);
        BorderPane.setMargin(photo, new Insets(0, 20, 0, 0)); // Más margen a la derecha de la foto

        // --- PANEL CENTRAL (info y materias) ---
        VBox infoPanel = new VBox(8); // Espaciado vertical un poco mayor

        Label nameLabel = new Label(name + " " + lastName + " · "  + age + " years");
        // Nombre en Morado Oscuro
        nameLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #4A148C;");
        nameLabel.setCursor(Cursor.HAND);
        nameLabel.setOnMouseClicked(e -> {
            if (listener != null) listener.onTutorClicked(id);
        });

        Label aboutLabel = new Label("About me:");
        aboutLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #757575;"); // Gris oscuro

        TextArea aboutText = new TextArea(about);
        aboutText.setEditable(false);
        aboutText.setWrapText(true);
        aboutText.setPrefRowCount(3);
        aboutText.setMaxWidth(500);
        // Estilo limpio para el TextArea (sin bordes fuertes, fondo muy suave)
        aboutText.setStyle(
                "-fx-control-inner-background: #F3E5F5; " + // Fondo interno lavanda muy claro
                        "-fx-background-color: transparent; " +
                        "-fx-background-insets: 0; " +
                        "-fx-text-fill: #424242;" // Texto gris oscuro
        );

        // Subjects buttons (Tags/Pills)
        FlowPane subjectsPanel = new FlowPane(8, 8); // Espaciado entre botones
        for (String s : subjects) {
            Button btn = new Button(s);
            // Estilo "Pill" (Pastilla) pequeña
            btn.setStyle(
                    "-fx-background-color: #EDE7F6; " + // Lila muy pálido
                            "-fx-text-fill: #5E35B1; " +        // Texto morado medio
                            "-fx-background-radius: 15; " +     // Bordes muy redondos
                            "-fx-font-size: 11px; " +
                            "-fx-padding: 3 10 3 10;"           // Padding ajustado
            );
            subjectsPanel.getChildren().add(btn);
        }

        infoPanel.getChildren().addAll(nameLabel, aboutLabel, aboutText, subjectsPanel);
        setCenter(infoPanel);

        // --- PANEL DERECHO (precio, rating, favorito) ---
        VBox rightPanel = new VBox(15);
        rightPanel.setPrefWidth(150);
        rightPanel.setAlignment(Pos.TOP_RIGHT); // Alineado a la derecha superior

        // Costo destacado
        Label costLabel = new Label("$" + cost + " / hr");
        costLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #2E7D32;"); // Verde para dinero, o podría ser morado #8E24AA

        // Botón de corazón
        Button favButton = new Button("♥");
        // Estilo circular para el botón de favorito
        favButton.setStyle(
                "-fx-background-color: white; " +
                        "-fx-text-fill: #D81B60; " + // Rosa fuerte
                        "-fx-font-size: 20px; " +
                        "-fx-border-color: #D81B60; " +
                        "-fx-border-radius: 50; " +
                        "-fx-background-radius: 50; " +
                        "-fx-cursor: hand; " +
                        "-fx-min-width: 40px; " +
                        "-fx-min-height: 40px;"
        );

        // Efecto hover simple en el corazón
        favButton.setOnMouseEntered(e -> favButton.setStyle("-fx-background-color: #FCE4EC; -fx-text-fill: #D81B60; -fx-font-size: 20px; -fx-border-color: #D81B60; -fx-border-radius: 50; -fx-background-radius: 50; -fx-min-width: 40px; -fx-min-height: 40px;"));
        favButton.setOnMouseExited(e -> favButton.setStyle("-fx-background-color: white; -fx-text-fill: #D81B60; -fx-font-size: 20px; -fx-border-color: #D81B60; -fx-border-radius: 50; -fx-background-radius: 50; -fx-min-width: 40px; -fx-min-height: 40px;"));

        Label ratingLabel = new Label("★ " + rating);
        ratingLabel.setStyle("-fx-text-fill: #F9A825; -fx-font-weight: bold; -fx-font-size: 14px;"); // Amarillo/Dorado

        rightPanel.getChildren().addAll(costLabel, ratingLabel, favButton);
        setRight(rightPanel);
    }

    public void setListener(TutorCardListener listener) {
        this.listener = listener;
    }
}