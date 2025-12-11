package GUI.Controller;

import GUI.MainView;
import GUI.Controller.*;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserDAO;
import java.sql.Connection;

import GUI.LoginForm;

public class LoginController {

    private LoginForm view;
    private UserDAO dao;
    private Connection conn = ConnectionDB.getConnection();

    public LoginController(LoginForm view, UserDAO dao) {
        this.view = view;
        this.dao = dao;

        // JavaFX usa setOnAction en lugar de addActionListener
        this.view.btnLogin.setOnAction(e -> login());
    }

    public boolean login() {
        String user = view.txtUser.getText();
        String pass = view.txtPass.getText(); // PasswordField usa getText()

        if (dao.Login(conn, user, pass) != null) {
            // Mostrar mensaje de bienvenida
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bienvenido");
            alert.setHeaderText(null);
            alert.setContentText("Bienvenido " + user);
            alert.showAndWait();

            // Abrir la ventana principal
            MainView mainView = new MainView((Stage) view.getScene().getWindow()); // o pasar stage
            MainController controller = new MainController(mainView);

            // Cerrar la ventana de login
            Stage stage = (Stage) view.txtUser.getScene().getWindow();
            stage.close();

            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
        }

        return false;
    }

}
