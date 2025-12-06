package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserDAO;
import GUI.MainView;
import GUI.TutorCard;

public class MainController implements TutorCardListener {

    private MainView view;
    private TutorCard card;
    private UserDAO user;
    private Connection Conn = ConnectionDB.getConnection();


    public MainController(MainView view, TutorCard card, UserDAO dao){
        this.view = view;
        this.card = card;
        this.user = dao;


        
    }


    @Override
    public void ontutorClicked(int id) {
        // se abre la pantalla perfil (aun no creada por cier)
    }

    
    
}