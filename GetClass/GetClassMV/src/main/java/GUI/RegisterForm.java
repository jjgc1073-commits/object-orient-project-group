package GUI;

import GUI.Controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class RegisterForm {

    public ChoiceBox<String> txtrol;
    public TextField txtName;
    public TextField txtLastName;
    public DatePicker txtbithdate;
    public TextField txtEmail;
    public TextField txtEmailC;
    public PasswordField txtPass;
    public PasswordField txtPassC;
    public Button btnRegister;

    private Scene scene;

    public RegisterForm (Stage stage){

        stage.setTitle("Register");

        GridPane grid = new GridPane();
        grid.setHgap(75);
        grid.setVgap(50);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        Label lblrol = new Label("Role:");
        txtrol = new ChoiceBox<>();
        txtrol.getItems().addAll("STUDENT", "TEACHER");

        Label lblName = new Label("Name:");
        txtName = new TextField();

        Label lblLastName = new Label("Last Name:");
        txtLastName = new TextField();

        Label lblbirhdate = new Label("BirhDate:");
        txtbithdate = new DatePicker();

        Label lblEmail = new Label("Email:");
        txtEmail = new TextField();

        Label lblEmailC = new Label("Confirm Email:");
        txtEmailC = new TextField();


        Label lblPass = new Label("Password:");
        txtPass = new PasswordField();

        Label lblPassC = new Label("Confirm password:");
        txtPassC = new PasswordField();


        btnRegister = new Button("Continue");

        // UBICACIÓN EN EL GRID
        grid.add(lblrol, 0, 0);
        grid.add(txtrol, 1, 0);

        grid.add(lblName, 0, 1);
        grid.add(txtName, 1, 1);

        grid.add(lblLastName, 0, 2);
        grid.add(txtLastName, 1, 2);

        grid.add(lblbirhdate, 0, 3);
        grid.add(txtbithdate, 1, 3);

        grid.add(lblEmail, 0, 4);
        grid.add(txtEmail, 1, 4);

        grid.add(lblEmailC, 0, 5);
        grid.add(txtEmailC, 1, 5);

        grid.add(lblPass, 0, 6);
        grid.add(txtPass, 1, 6);

        grid.add(lblPassC, 0, 7);
        grid.add(txtPassC, 1, 7);

        // espacio y botón
        grid.add(new Label(), 0, 8);
        grid.add(btnRegister, 1, 8);

        // ESCENA
        scene = new Scene(grid, 350, 750);
        stage.setScene(scene);

        

    }

    public Scene getScene() {
        return scene;
    }




}
