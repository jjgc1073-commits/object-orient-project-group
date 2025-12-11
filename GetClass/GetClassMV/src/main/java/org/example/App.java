package org.example;



import DataBase.ConnectionDB;
import DataBase.DatabaseInitializer;
import GUI.RegisterForm;
import javafx.application.Application;
import javafx.stage.Stage;
import GUI.LoginForm;
import GUI.Controller.LoginController;
import DataBase.DAOS.UserDAO;




public class App extends Application {

    /** @Override
    public void start(Stage primaryStage) {
        // Crear la vista
        LoginForm loginForm = new LoginForm(primaryStage);

        // Crear el DAO
        UserDAO userDao = new UserDAO();

        // Crear el controlador
        LoginController loginController = new LoginController(loginForm, userDao);

        // Mostrar la ventana
        primaryStage.show();
    }*/

    @Override
    public void start(Stage primaryStage) {
        // Crear la vista
        RegisterForm registerForm = new RegisterForm(primaryStage);

        // Mostrar la ventana
        primaryStage.show();

    }
    public static void main(String[] args) {

        launch(args);

    }
}