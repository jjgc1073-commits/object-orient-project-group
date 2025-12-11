package GUI;

import GUI.Controller.RegisterController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;


public class TutorInfoForm {

    public TextField txtAboutMe;
    public ListView<String> subjects;
    public TextField txtcert;
    public TextField txtprice;
    public TextField txtLocated;
    
    public Button btnRegister;

    private Scene scene;

    public TutorInfoForm (Stage stage){

        stage.setTitle("Profile");

        GridPane grid = new GridPane();
        grid.setHgap(75);
        grid.setVgap(50);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);


        Label lblAboutMe = new Label("Tell us about you:");
        txtAboutMe = new TextField();

        Label lblSubjects = new Label("Select the subjects you teach:");
        subjects = new ListView<>();
        subjects.getItems().setAll("Math", "Physics", "Chemistry", "Literature", "History", 
        "Music", "Personal Training", "Sports", "Geography", "Social Studies", 
        "Religion");
        subjects.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Label lblcert = new Label("Name all your certificates (separated by ,): ");
        txtcert = new TextField();

        Label lblPrice = new Label("Hourly rate:");
        txtprice = new TextField();

        txtprice.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                return; 
            }
            if (!newValue.matches("\\d*")) {
                
                ((javafx.scene.control.TextField) txtprice).setText(oldValue);
            }
        });

        Label lblLocation = new Label("Where are you?:");
        txtLocated = new TextField();


        btnRegister = new Button("Register");

        // UBICACIÓN EN EL GRID
        grid.add(lblAboutMe, 0, 0);
        grid.add(txtAboutMe, 1, 0);

        grid.add(lblSubjects, 0, 1);
        grid.add(subjects, 1, 1);

        grid.add(lblcert, 0, 2);
        grid.add(txtcert, 1, 2);

        grid.add(lblPrice, 0, 3);
        grid.add(txtprice, 1, 3);

        grid.add(lblLocation, 0, 4);
        grid.add(txtLocated, 1, 4);

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
