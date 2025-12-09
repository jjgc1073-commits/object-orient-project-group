package GUI;

import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.MainController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.util.List;

public class TutorTabsPanel extends VBox {

    private MainController mainController;

    public TutorTabsPanel(MainController mainController) {
        this.mainController = mainController;

        setSpacing(10);
        setPrefHeight(450);

        TabPane tabPane = new TabPane();

        // Crea varias pestañas como antes
        addTab(tabPane);
        addTab(tabPane);
        addTab(tabPane);
        addTab(tabPane);

        getChildren().add(tabPane);
    }

    private void addTab(TabPane tabPane) {
        Connection conn = null;
        try {
            conn = DataBase.ConnectionDB.getConnection();
            UserTeacherDAO dao = new UserTeacherDAO();
            List<UserTeacherDTO> teachers = dao.getAll(conn);

            // Aquí tu TutorListPanel también debe convertirse a FX
            TutorListPanel panel = new TutorListPanel(teachers, mainController);
            Tab tab = new Tab("Category", panel);

            tabPane.getTabs().add(tab);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
