package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class RegisterForm {


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


        btnRegister = new Button("Register");

        // UBICACIÓN EN EL GRID
        grid.add(lblName, 0, 0);
        grid.add(txtName, 1, 0);

        grid.add(lblLastName, 0, 1);
        grid.add(txtLastName, 1, 1);

        grid.add(lblbirhdate, 0, 2);
        grid.add(txtbithdate, 1, 2);

        grid.add(lblEmail, 0, 3);
        grid.add(txtEmail, 1, 3);

        grid.add(lblEmailC, 0, 4);
        grid.add(txtEmailC, 1, 4);

        grid.add(lblPass, 0,    5);
        grid.add(txtPass, 1, 5);

        grid.add(lblPassC, 0, 6);
        grid.add(txtPassC, 1, 6);

        // espacio y botón
        grid.add(new Label(), 0, 7);
        grid.add(btnRegister, 1, 7);

        // ESCENA
        scene = new Scene(grid, 350, 650);
        stage.setScene(scene);




    }

    public Scene getScene() {
        return scene;
    }




}
