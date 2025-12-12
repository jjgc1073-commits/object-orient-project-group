package GUI;

import java.time.LocalDate;
import DataBase.DTO.UserStudentDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ReviewCard extends BorderPane {
    
    public ImageView imgStudentProfile; // Cambiado nombre a Student para coherencia
    public Label lblStudentName;
    public Label lblReviewDate;
    public HBox ratingStarsContainer;
    public Label lblRatingValue;
    public TextArea txtComment;
    
    public UserStudentDTO student;

    // 🟢 NOTA: Agregué 'int rate' al constructor porque es necesario para mostrar la calificación
    public ReviewCard(int studentId, int rate, String comment, LocalDate date) {
        
        // 1. Obtener datos del estudiante (Autor de la reseña)
        // Nota: Asumo que TutorProfile tiene un método para obtener estudiantes por ID
        // Si no, deberías usar un StudentDAO o similar.
        TutorProfile profile = new TutorProfile(studentId); 
        this.student = profile.defineStudent();

        // 2. Configuración General de la Tarjeta
        setPadding(new Insets(15));
        setPrefWidth(800);
        // Altura dinámica según el contenido, o fija si prefieres
        setMinHeight(120); 
        setStyle("-fx-border-color: #e0e0e0; -fx-border-width: 1; -fx-background-color: white; -fx-background-radius: 8; -fx-border-radius: 8;");

        // =====================================================================
        // IZQUIERDA: Foto del Estudiante
        // =====================================================================
        imgStudentProfile = new ImageView(); // Aquí deberías cargar la imagen real si existe
        imgStudentProfile.setFitWidth(60);
        imgStudentProfile.setFitHeight(60);
        // Placeholder gris redondo
        VBox imageContainer = new VBox(imgStudentProfile);
        imageContainer.setStyle("-fx-background-color: #ccc; -fx-background-radius: 50;");
        imageContainer.setAlignment(Pos.CENTER);
        imageContainer.setPrefSize(60, 60);
        
        setLeft(imageContainer);
        BorderPane.setMargin(imageContainer, new Insets(0, 15, 0, 0));

        // =====================================================================
        // CENTRO: Nombre, Rating y Comentario
        // =====================================================================
        VBox centerBox = new VBox(5);
        centerBox.setAlignment(Pos.CENTER_LEFT);

        // -- Fila Superior: Nombre y Fecha --
        HBox topRow = new HBox(10);
        topRow.setAlignment(Pos.CENTER_LEFT);
        
        String nameText = (student != null) ? student.getName() + " " + student.getLastName() : "Estudiante";
        String ageText = (student != null) ? ", " + student.getAge() + " años" : "";
        
        lblStudentName = new Label(nameText + ageText);
        lblStudentName.setFont(Font.font("System", FontWeight.BOLD, 16));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // Empuja la fecha a la derecha

        lblReviewDate = new Label(date.toString());
        lblReviewDate.setStyle("-fx-text-fill: gray; -fx-font-size: 12px;");

        topRow.getChildren().addAll(lblStudentName, spacer, lblReviewDate);

        // -- Fila de Rating --
        ratingStarsContainer = createStars(rate);
        
        // -- Comentario --
        // Usamos Label para texto estático o TextArea read-only para textos largos
        Label lblCommentContent = new Label(comment);
        lblCommentContent.setWrapText(true);
        lblCommentContent.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
        
        // Si prefieres usar el TextArea que definiste:
        /*
        txtComment = new TextArea(comment);
        txtComment.setEditable(false);
        txtComment.setWrapText(true);
        txtComment.setPrefRowCount(2);
        txtComment.setStyle("-fx-background-color: transparent; -fx-background-insets: 0;");
        */

        centerBox.getChildren().addAll(topRow, ratingStarsContainer, lblCommentContent);
        setCenter(centerBox);
    }

    /**
     * Método auxiliar para crear las estrellas visuales
     */
    private HBox createStars(int rate) {
        HBox stars = new HBox(2);
        stars.setAlignment(Pos.CENTER_LEFT);
        
        for (int i = 0; i < 5; i++) {
            Label star = new Label("★");
            star.setStyle("-fx-font-size: 18px;");
            if (i < rate) {
                star.setStyle("-fx-text-fill: #FFD700; -fx-font-size: 18px;"); // Dorado
            } else {
                star.setStyle("-fx-text-fill: #E0E0E0; -fx-font-size: 18px;"); // Gris
            }
            stars.getChildren().add(star);
        }
        
        // Opcional: Añadir el valor numérico al lado
        lblRatingValue = new Label("(" + rate + ".0)");
        lblRatingValue.setStyle("-fx-text-fill: gray; -fx-font-size: 12px;");
        stars.getChildren().add(lblRatingValue);
        HBox.setMargin(lblRatingValue, new Insets(0, 0, 0, 5));

        return stars;
    }
}