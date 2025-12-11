package GUI.Controller;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserTeacherDTO;
import GUI.MainView;

import java.sql.Connection;
import java.util.List;

public class MainController {

    private final MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
    }

    // =======================
    //  NECESARIO PARA GUI
    // =======================

    public List<UserTeacherDTO> getAllTeachers() {
        Connection conn = ConnectionDB.getConnection();
        return UserTeacherDAO.getAll(conn);
    }

    public UserTeacherDTO getTeacherById(int id) {
        Connection conn = ConnectionDB.getConnection();
        return UserTeacherDAO.getById(conn, id);
    }

    public void openTutorProfile(int tutorId) {

    }

    public void initializeTutorPanel() {
        
    }

}
