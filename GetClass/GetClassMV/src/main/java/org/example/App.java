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


    public void start(Stage primaryStage) {
        Connection conn = ConnectionDB.getConnection();
        UserTeacherDTO dto = UserTeacherDAO.getById(conn, 2);

        ProfileView view = new ProfileView(primaryStage);

        // --- VERIFICACIÓN DE NULIDAD (Mantenida) ---
        if (dto == null) {
            System.err.println("❌ Error: Tutor con ID 1 no encontrado o datos inconsistentes. Verifique la DB.");
            primaryStage.setTitle("Error de Carga");
            primaryStage.setScene(new Scene(new Label("Error: Perfil de Tutor no encontrado."), 300, 100));
            primaryStage.show();
            return;
        }
        // ------------------------------------------

        // *** LLAMADA AL CONSTRUCTOR ACTUALIZADA CON TODOS LOS ELEMENTOS ***
        TutorProfile tutorProfile = new TutorProfile(
                view.imgProfile,
                view.lblName,
                view.lblLocation,
                view.lblHourlyRate,
                view.lblAboutMe,
                view.subjectsContainer,
                view.certificationsContainer,
                // Nuevos elementos
                view.lblCost,
                view.lblResponseTime,
                view.btnSchedule,
                view.btnContact,
                view.btnRate
        );

        tutorProfile.loadTutorData(dto);

        Scene scene = new Scene(view.root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tutor Profile");
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