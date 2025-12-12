package GUI.Controller;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserTeacherDTO;
import GUI.MainView;

import java.sql.Connection;
import java.util.List;

public class MainController implements TutorCardListener {

    Connection conn = ConnectionDB.getConnection();
    private final MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    // =======================
    //  NECESARIO PARA GUI
    // =======================

    public List<UserTeacherDTO> getAllTeachers() {
        return UserTeacherDAO.getAll(conn);
    }

    public UserTeacherDTO getTeacherById(int id) {
        return UserTeacherDAO.getById(conn, id);
    }

    public void openTutorProfile(int tutorId) {

    }

    public void initializeTutorPanel() {

    }

    @Override
    public void onTutorClicked(int id) {
        System.out.println("Hola");
    }
}
