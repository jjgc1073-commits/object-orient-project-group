package GUI.Controller;

import GUI.ProfileView;
import GUI.WriteReview;
import javafx.stage.Stage;
import javafx.scene.Node; // Import necesario

public class ReviewController {

    public WriteReview view;
    public int id;


    public ReviewController(WriteReview view){
        this.view = view;
        this.id = this.view.id;

        this.view.btnSkip.setOnAction(e -> back());
    }

    public void back(){
        // 1. Obtener la Stage actual a partir del botón y cerrarla
        Stage currentStage = (Stage) this.view.btnSkip.getScene().getWindow();
        currentStage.close();

        // 2. Abrir la nueva ventana de Perfil
        Stage profileStage = new Stage();
        ProfileView profile = new ProfileView(profileStage, id);
        //ProfileController controller = new ProfileController(profile);


    }
}