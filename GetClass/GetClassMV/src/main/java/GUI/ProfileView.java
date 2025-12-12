package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ProfileView extends BorderPane {

    // ---- CONTROLES QUE MANEJARÁ EL CONTROLADOR ----
    public ImageView imgProfile;
    public Label lblName;
    public Label lblLocation;
    public Label lblHourlyRate;
    public Label lblAboutMe;
    public VBox subjectsContainer;
    public VBox certificationsContainer;

    public ProfileView(Stage stage) {

        // ---------- IZQUIERDA (Foto + datos básicos) ----------
        VBox leftBox = new VBox(15);
        leftBox.setPadding(new Insets(20));
        leftBox.setAlignment(Pos.TOP_CENTER);

        // Imagen (recuadro vacío)
        imgProfile = new ImageView();
        imgProfile.setFitWidth(150);
        imgProfile.setFitHeight(150);
        imgProfile.setStyle("-fx-background-color: #cccccc; -fx-border-color: black;");
        imgProfile.setPreserveRatio(false);

        lblName = new Label("Nombre del Tutor");
        lblName.setFont(new Font(20));

        lblLocation = new Label("Ubicación: ---");
        lblHourlyRate = new Label("Costo por hora: ---");

        leftBox.getChildren().addAll(imgProfile, lblName, lblLocation, lblHourlyRate);

        // ---------- CENTRO (About Me + Subjects + Certificaciones) ----------
        VBox centerBox = new VBox(20);
        centerBox.setPadding(new Insets(20));

        // About Me
        Label aboutTitle = new Label("Acerca de mí");
        aboutTitle.setFont(new Font(18));

        lblAboutMe = new Label("Descripción del tutor...");
        lblAboutMe.setWrapText(true);

        // Subjects
        Label subjectsTitle = new Label("Especialidades");
        subjectsTitle.setFont(new Font(18));

        subjectsContainer = new VBox(5);
        subjectsContainer.setPadding(new Insets(10));
        subjectsContainer.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        // Certifications
        Label certTitle = new Label("Certificaciones");
        certTitle.setFont(new Font(18));

        certificationsContainer = new VBox(5);
        certificationsContainer.setPadding(new Insets(10));
        certificationsContainer.setStyle("-fx-border-color: black; -fx-border-width: 1;");

        centerBox.getChildren().addAll(
                aboutTitle, lblAboutMe,
                subjectsTitle, subjectsContainer,
                certTitle, certificationsContainer
        );

        // Añadimos todo al BorderPane
        this.setLeft(leftBox);
        this.setCenter(centerBox);
    }
}
