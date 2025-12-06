package GUI.Controller;

import DataBase.DAOS.UserDAO;
import GUI.LoginForm;
import GUI.MainView;
import GUI.TutorCard;
import DataBase.ConnectionDB;
import java.sql.Connection;

import javax.swing.*;

public class LoginController {


    private LoginForm view;
    private UserDAO dao;
    private Connection Conn = ConnectionDB.getConnection();

    public LoginController(LoginForm view, UserDAO dao) {
        this.view = view;

        this.dao = dao;

        this.view.btnLogin.addActionListener(e -> login());
    }

    public boolean login() {
        String user = view.txtUser.getText();
        String pass = new String(view.txtPass.getPassword());

        if (dao.Login(Conn, user, pass) != null) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + user);

            MainView view= new MainView();
            view.setVisible(true);
            this.view.dispose();


            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
        return false;
    } 
}

    

