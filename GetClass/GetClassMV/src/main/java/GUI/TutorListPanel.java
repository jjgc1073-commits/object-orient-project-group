package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorCardListener;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class TutorListPanel extends ScrollPane {

    private VBox content;

    public TutorListPanel(List<UserTeacherDTO> teachers, TutorCardListener listener) {
        content = new VBox(30); // Espaciado entre tarjetas
        content.setPrefWidth(800); // Ajusta según necesites

        for (UserTeacherDTO t : teachers) {
            TutorCard card = new TutorCard(
                    t.name,
                    t.age,
                    t.id,
                    t.tutorInfo.getAboutMe(),
                    t.tutorInfo.getSubjects().toArray(new String[0]),
                    t.tutorInfo.getHourlyRate(),
                    t.tutorInfo.getRating()
            );


            card.setOnMouseClicked(e -> listener.onTutorClicked(t.id));

            content.getChildren().add(card);
        }

        setContent(content);
        setFitToWidth(true); // Hace que el contenido se ajuste al ancho del ScrollPane
    }
}
