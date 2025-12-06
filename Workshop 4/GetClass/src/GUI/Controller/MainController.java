package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserDAO;
import GUI.MainView;
import GUI.TutorCard;

public class MainController {

    private MainView view;
    private TutorCard card;
    private UserDAO user;
    private Connection Conn = ConnectionDB.getConnection();


    public MainController(MainView view, TutorCard card, UserDAO dao){
        
    }

    
    
}