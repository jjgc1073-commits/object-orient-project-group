package GUI.Controller;

import DataBase.DAOS.UserDAO;
import DataBase.DTO.UserTeacherDTO;
import GUI.ContactView;
import GUI.LoginForm;
import GUI.MainView;
import GUI.ProfileView;
import GUI.WriteReview;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProfileController {

    public ProfileView view;
    public int tutorId;

    public ProfileController(ProfileView profileController){
        this.view = profileController;
        this.tutorId = view.id;
        this.view.btnBack.setOnAction(e -> back());
        this.view.btnRate.setOnAction(e -> rate());
        this.view.btnBook.setOnAction(e -> contact());
    }

    private void contact() {
        TutorProfile t = new TutorProfile(tutorId);
        UserTeacherDTO tutor = t.defineTutor();
        Stage contactStage = new Stage();
        ContactView contactView = new ContactView(/**tutor.getTel()*/ "crear columna en BD", tutor.getEmail()); // o pasar stage
        contactStage.setScene(contactView.getScene());
        contactStage.show();
    }

    public void back(){
        Stage mainStage = new Stage();
        MainView mainView = new MainView(mainStage); // o pasar stage
        MainController controller = new MainController(mainView);
        mainStage.setScene(mainView.getScene());
        mainStage.show();

        Stage stage = (Stage) view.btnBack.getScene().getWindow();
        stage.close();
    }

    public void rate(){
        Stage rateStage = new Stage();
        WriteReview rateform = new WriteReview(rateStage, this.tutorId); // o pasar stage
        Scene scene = new Scene(rateform, 600, 600);
        //ReviewController controller = new ReviewController(rateform);
        rateStage.setScene(scene);
        rateStage.setTitle("Calificar Tutor");
        rateStage.show();

        Stage stage = (Stage) view.btnBack.getScene().getWindow();
        stage.close();
    }
    
}
