package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.MainController;
import GUI.Controller.TutorCardListener;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

public class TutorTabsPanel extends TabPane {

    private MainController mainController;

    public TutorTabsPanel() {
        setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
    }

    // Este método se llama DESPUÉS, cuando MainController ya existe
    public void initialize(MainController controller) {
        this.mainController = controller;

        // TAB 1
        Tab tab1 = new Tab("Tutores");

        // Obtener tutores solo cuando el controller YA EXISTE
        List<UserTeacherDTO> teachers = mainController.getAllTeachers();

        TutorListPanel tutorList = new TutorListPanel(
                teachers,
                tutorId -> mainController.openTutorProfile(tutorId)
        );

        tab1.setContent(tutorList);

        // Agregar tab
        getTabs().add(tab1);
    }

    public MainController setMainController(MainController mainController){
        return mainController;
    }

}


