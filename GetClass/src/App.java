import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import DataBase.*;
import Classes.*;
import DataBase.DatabaseInitializer;
import DataBase.DAOS.UserDAO;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserteacherDTO;
import GUI.LoginForm;
import GUI.Controller.LoginController;

public class App {
    public static void main(String[] args) throws Exception {



        LoginForm view = new LoginForm();
        UserDAO dao = new UserDAO();
        LoginController controller = new LoginController(view, dao);

        view.setVisible(true);

        
    }
}
