package GUI.Controller;

import DataBase.DTO.UserTeacherDTO;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import DataBase.DTO.TutorInfoDTO;
import javafx.scene.control.Label;

public class TutorProfile {

    private ImageView imgProfile;
    private Label lblName;
    private Label lblLocation;
    private Label lblHourlyRate;
    private Label lblAboutMe;
    private VBox subjectsContainer;
    private VBox certificationsContainer;

    public TutorProfile(ImageView imgProfile, Label lblName, Label lblLocation,
                                  Label lblHourlyRate, Label lblAboutMe,
                                  VBox subjectsContainer, VBox certificationsContainer) {

        this.imgProfile = imgProfile;
        this.lblName = lblName;
        this.lblLocation = lblLocation;
        this.lblHourlyRate = lblHourlyRate;
        this.lblAboutMe = lblAboutMe;
        this.subjectsContainer = subjectsContainer;
        this.certificationsContainer = certificationsContainer;
    }

    public void loadTutorData(UserTeacherDTO dto) {

        lblName.setText(dto.getName() + " " + dto.getLastName());
        lblLocation.setText("Ubicación: " + dto.tutorInfo.getLocatedIn());
        lblHourlyRate.setText("Costo por hora: $" + dto.tutorInfo.getHourlyRate());
        lblAboutMe.setText(dto.tutorInfo.getAboutMe());

        // RECUADRO DE IMAGEN (sin imagen real)
        imgProfile.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");

        // Subjects
        subjectsContainer.getChildren().clear();
        for (String s : dto.tutorInfo.getSubjects()) {
            subjectsContainer.getChildren().add(new Label("• " + s));
        }

        // Certifications
        certificationsContainer.getChildren().clear();
        for (String c : dto.tutorInfo.getCertifications()) {
            certificationsContainer.getChildren().add(new Label("✔ " + c));
        }
    }
}
