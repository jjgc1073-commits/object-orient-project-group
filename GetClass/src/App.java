import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;
import DataBase.*;
import Classes.*;
import DataBase.DatabaseInitializer;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserteacherDTO;

public class App {
    public static void main(String[] args) throws Exception {



        try (Connection conn = ConnectionDB.getConnection()) {
            UserteacherDTO teacherDTO = UserTeacherDAO.getById(conn, 1);
            System.out.println(teacherDTO.name);
            System.out.println(teacherDTO.age);
            System.out.println(teacherDTO.tutorInfo.getAboutMe());
            System.out.println(teacherDTO.tutorInfo.getHourlyRate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
    }
}
