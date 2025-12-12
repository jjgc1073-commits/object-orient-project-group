package org.example;


import DataBase.DTO.UserTeacherDTO;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.ConnectionDB;
import DataBase.DatabaseInitializer;
import GUI.Controller.TutorProfile;
import GUI.ProfileView;
import GUI.RegisterForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import GUI.LoginForm;
import GUI.Controller.LoginController;
import GUI.Controller.RegisterController;
import DataBase.DAOS.UserDAO;

import java.sql.Connection;


public class App extends Application {

/** 
    public void start(Stage primaryStage) {
        Stage mainStage = new Stage();
        ProfileView mainView = new ProfileView(mainStage, 1); // o pasar stage
        
        mainStage.setScene(mainView.getScene());
        mainStage.show();
    }*/

     
     @Override
    public void start(Stage primaryStage) {
        // Crear la vista
        LoginForm loginForm = new LoginForm(primaryStage);

        // Crear el DAO
        UserDAO userDao = new UserDAO();

        // Crear el controlador
        LoginController loginController = new LoginController(loginForm, userDao);

        // Mostrar la ventana
        primaryStage.show();
    }

/** 
    @Override
    public void start(Stage primaryStage) {
        // Crear la vista
        RegisterForm registerForm = new RegisterForm(primaryStage);

        RegisterController controller = new RegisterController(registerForm);

        // Mostrar la ventana
        primaryStage.show();

    }*/
    public static void main(String[] args) {
        
        launch(args);

    }
}