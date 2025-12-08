package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserDAO;
import GUI.MainView;
import GUI.ProfileView;
import GUI.TutorCard;

public class MainController implements TutorCardListener{

    private MainView view;
    private TutorCard card;
    private UserDAO user;
    public boolean mouseClicked;
    private Connection Conn = ConnectionDB.getConnection();


    public MainController(MainView view){
        this.view = view;
        this.mouseClicked = false;
    }


    @Override
    public void onTutorClicked(int id) {
        ProfileView profile = new ProfileView(id);
        profile.setVisible(true);
        this.view.dispose();
    }


    
    
    
    
    
}