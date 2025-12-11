package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.MainController;
import GUI.Controller.TutorCardListener;
import javafx.geometry.Insets;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class TutorTabsPanel extends TabPane {

    public TutorTabsPanel(MainController mainController) {

        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        //=== TAB 1: LISTA DE TUTORES =========================
        Tab tab1 = new Tab("Tutores");

        // Obtiene los tutores desde el controller
        List<UserTeacherDTO> teachers = mainController.getAllTeachers();

        TutorListPanel tutorList = new TutorListPanel(
                teachers,
                new TutorCardListener() {
                    @Override
                    public void onTutorClicked(int tutorId) {
                        mainController.openTutorProfile(tutorId);
                    }
                }
        );

        tab1.setContent(tutorList);
        getTabs().add;
    }
}
