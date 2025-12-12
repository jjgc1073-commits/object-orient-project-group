package GUI.Controller;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserTeacherDTO;
import GUI.MainView;
import GUI.ProfileView;
import javafx.stage.Stage;

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


    public void initializeTutorPanel() {

    }

    public void openTutorProfile(int tutorId) {
        System.out.println("Abriendo perfil del tutor ID: " + tutorId);
        
        Stage stage = mainView.getStage();
        
        
        ProfileView profeile = new GUI.ProfileView(stage, tutorId);
        ProfileController profileController = new ProfileController(profeile);
    }

    @Override
    public void onTutorClicked(int id) {
        
        openTutorProfile(id);
    }
}
