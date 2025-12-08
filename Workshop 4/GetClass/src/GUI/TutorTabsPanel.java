package GUI;

import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserteacherDTO;
import GUI.Controller.MainController;
import DataBase.DTO.TutorInfoDTO;
import DataBase.ConnectionDB;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class TutorTabsPanel extends JPanel {

    public MainController mainController;

    public TutorTabsPanel(MainController mainController) {
        this.mainController = mainController;
        setLayout(new BorderLayout());

        UserTeacherDAO dao = new UserTeacherDAO();

        JTabbedPane tabs = new JTabbedPane();

        // Puedes agregar todas las pestañas que quieras
        addTab(tabs, dao);
        addTab(tabs, dao);
        addTab(tabs, dao);
        addTab(tabs, dao);

        add(tabs, BorderLayout.CENTER);
    }

    

    private void addTab(JTabbedPane tabs, UserTeacherDAO dao) {

        List<UserteacherDTO> teachers = dao.getAll(ConnectionDB.getConnection());

        TutorListPanel panel = new TutorListPanel(teachers, mainController);

        tabs.addTab("Category", panel);
    }

}
