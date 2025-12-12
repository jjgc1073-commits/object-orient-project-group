package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ContactView {

    private Scene scene;
    private Stage stage;

    public ContactView( String ContactNum, String Email) {

        stage.setTitle("contact information");

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        Label lblNum = new Label("phone number: " + ContactNum);

        Label lblEmail = new Label("Email: " + Email);

        grid.add(lblNum, 0, 0);
        grid.add(lblEmail, 0, 1);

        scene = new Scene(grid, 350, 200);
        stage.setScene(scene);


    }

    public Scene getScene() {
        return scene;
    }

}


