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
import javafx.stage.Stage;
import GUI.LoginForm;
import GUI.Controller.LoginController;
import GUI.Controller.RegisterController;
import DataBase.DAOS.UserDAO;

import java.sql.Connection;


public class App extends Application {


    public void start(Stage primaryStage) {
        Connection conn = ConnectionDB.getConnection();
        UserTeacherDTO dto = UserTeacherDAO.getById(conn, 1);

        ProfileView view = new ProfileView(primaryStage);

        TutorProfile tutorProfile = new TutorProfile(
                view.imgProfile,
                view.lblName,
                view.lblLocation,
                view.lblHourlyRate,
                view.lblAboutMe,
                view.subjectsContainer,
                view.certificationsContainer
        );

        tutorProfile.loadTutorData(dto);

        Scene scene = new Scene(view, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /**
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


    @Override
    public void start(Stage primaryStage) {
        // Crear la vista
        RegisterForm registerForm = new RegisterForm(primaryStage);

        RegisterController controller = new RegisterController(registerForm);

        // Mostrar la ventana
        primaryStage.show();

    }*/
    public static void main(String[] args) {
        DatabaseInitializer.initialize();
        launch(args);

    }
}