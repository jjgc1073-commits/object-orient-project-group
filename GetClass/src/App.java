import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {
        DatabaseInitializer.initialize();

        UserTeacher teacher = new UserTeacher(
            "Alejandro Escobar",
            LocalDate.of(2005, 9, 16),
            "AEscobar@gmail.com",
            "AlexPrueba1609");

        teacher.tutorInfo = new TutorInfo(
            "Experienced Programming Tutor",
            List.of("Mathematics", "Algebra", "Calculus", "Programming"),
            List.of("Certified  Teacher", "BSc in Computer Science"),
            30,
            "Colombia"
        );

        try (Connection conn = ConnectionDB.getConnection()) {
            UserTeacherDAO.save(conn, teacher);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserTeacherDAO.save(ConnectionDB.getConnection(), teacher);
    }
}
