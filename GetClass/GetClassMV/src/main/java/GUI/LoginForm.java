package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LoginForm {

    public TextField txtUser;
    public PasswordField txtPass;
    public Button btnLogin;

    private Scene scene;

    public LoginForm(Stage stage) {

        // TÍTULO DE LA VENTANA
        stage.setTitle("Login");

        // CONTENEDOR PRINCIPAL
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        // CAMPOS Y LABELS
        Label lblUser = new Label("Email:");
        txtUser = new TextField();

        Label lblPass = new Label("Contraseña:");
        txtPass = new PasswordField();

        btnLogin = new Button("Iniciar sesión");

        // UBICACIÓN EN EL GRID
        grid.add(lblUser, 0, 0);
        grid.add(txtUser, 1, 0);

        grid.add(lblPass, 0, 1);
        grid.add(txtPass, 1, 1);

        // espacio y botón
        grid.add(new Label(), 0, 2);
        grid.add(btnLogin, 1, 2);

        // ESCENA
        scene = new Scene(grid, 350, 200);
        stage.setScene(scene);
    }

    public Scene getScene() {
        return scene;
    }
}

