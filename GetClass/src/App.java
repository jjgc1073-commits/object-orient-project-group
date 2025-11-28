
import DataBase.DAOS.UserDAO;
import GUI.LoginForm;
import GUI.MainView;
import GUI.TutorCard;
import GUI.Controller.LoginController;

public class App {
    public static void main(String[] args) throws Exception {



        LoginForm view = new LoginForm();
        UserDAO dao = new UserDAO();
        LoginController controller = new LoginController(view, dao);

        view.setVisible(true);

       

        
    }
}   


