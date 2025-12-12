package GUI;

import DataBase.DTO.ReviewDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class ReviewsListPanel extends ScrollPane {
    
    private VBox content;

    public ReviewsListPanel(List<ReviewDTO> reviews) {
        // Configuración visual del ScrollPane
        this.setFitToWidth(true);
        this.setHbarPolicy(ScrollBarPolicy.NEVER); // Solo scroll vertical
        this.setStyle("-fx-background-color: transparent; -fx-background: transparent;");

        // Configuración del contenedor vertical
        content = new VBox(15); // 15px de espacio entre tarjetas
        content.setPadding(new Insets(20)); // Margen interno para que no pegue a los bordes
        content.setStyle("-fx-background-color: #f4f4f4;");

        // Validación: Si no hay reseñas, mostrar mensaje
        if (reviews == null || reviews.isEmpty()) {
            Label noReviews = new Label("No hay reseñas disponibles todavía.");
            noReviews.setStyle("-fx-text-fill: gray; -fx-font-size: 14px;");
            content.getChildren().add(noReviews);
            content.setAlignment(Pos.CENTER);
        } else {
            // Rellenar con tarjetas
            for (ReviewDTO r : reviews) {
                ReviewCard card = new ReviewCard(
                        r.studentId, // Usamos el ID del estudiante (o podrías buscar su nombre)
                        r.rate,
                        r.comment,
                        r.date
                );
                content.getChildren().add(card);
            }
        }

        this.setContent(content);
    }
}