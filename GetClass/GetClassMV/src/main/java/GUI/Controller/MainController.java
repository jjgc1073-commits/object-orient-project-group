package GUI.Controller;

import DataBase.DAOS.UserDAO;
import GUI.ProfileView;
import GUI.TutorCard;
import GUI.MainView;
import javafx.stage.*;

import java.sql.Connection;
import DataBase.ConnectionDB;

public class MainController implements TutorCardListener {

    private MainView view;
    private UserDAO userDAO;
    private Connection conn;

    public MainController(MainView view) {
        this.view = view;
        this.userDAO = userDAO;
        this.conn = ConnectionDB.getConnection();
    }

    @Override
    public void onTutorClicked(int tutorId) {
        ProfileView profileView = new ProfileView(tutorId);
        Stage profileStage = new Stage();
        profileStage.setScene(profileView.getScene());
        profileStage.show();

        // Cerrar la ventana actual
        Stage currentStage = view.getStage();
        currentStage.close();
    }
}
