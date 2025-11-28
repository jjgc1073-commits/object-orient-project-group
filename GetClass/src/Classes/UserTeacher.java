import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Statement;


public class UserTeacher extends User {

    public TutorInfo tutorInfo;

    public UserTeacher(String name, LocalDate birthDate, String email, String password) {
        super(name, birthDate, email, password);
        this.role = "TEACHER"; // ✅ coincide con SQLite
    }

    /**
     * Constructor para cargar desde BD
     */
    public UserTeacher(int id, String name, LocalDate birthDate, String email, String password) {
        super(id, name, birthDate, email, password, "TEACHER");
    }

    public boolean save(Connection conn) {
        try {
            conn.setAutoCommit(false);

            // INSERT USER
            PreparedStatement stmtUser = conn.prepareStatement(
                "INSERT INTO USER(name, email, password_hash, birth_date, age, role) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmtUser.setString(1, this.name);
            stmtUser.setString(2, this.email);
            stmtUser.setString(3, this.password);
            stmtUser.setString(4, this.birthDate.toString());
            stmtUser.setInt(5, this.age);
            stmtUser.setString(6, this.role);

            stmtUser.executeUpdate();
            ResultSet rsUser = stmtUser.getGeneratedKeys();
            rsUser.next();
            this.id = rsUser.getInt(1);

            // INSERT TUTOR_INFO
            PreparedStatement stmtInfo = conn.prepareStatement(
                "INSERT INTO TUTOR_INFO(user_id, about_me, hourly_rate, located_in) VALUES (?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS
            );

            stmtInfo.setInt(1, this.id);
            stmtInfo.setString(2, this.tutorInfo.getAboutMe());
            stmtInfo.setInt(3, this.tutorInfo.getHourlyRate());
            stmtInfo.setString(4, this.tutorInfo.getLocatedIn());

            stmtInfo.executeUpdate();
            ResultSet rsInfo = stmtInfo.getGeneratedKeys();
            rsInfo.next();
            int tutorInfoId = rsInfo.getInt(1);

            // INSERT SUBJECTS
            PreparedStatement stmtSubject = conn.prepareStatement(
                "INSERT INTO TUTOR_SUBJECT(tutor_info_id, subject) VALUES (?, ?)"
            );

            for (String subject : this.tutorInfo.getSubjects()) {
                stmtSubject.setInt(1, tutorInfoId);
                stmtSubject.setString(2, subject);
                stmtSubject.executeUpdate();
            }
            // INSERT CERTIFICATIONS
            PreparedStatement stmtCert = conn.prepareStatement(
                "INSERT INTO TUTOR_CERTIFICATION(tutor_info_id, certification) VALUES (?, ?)"
            );
            for (String cert : this.tutorInfo.getCertifications()) {
                stmtCert.setInt(1, tutorInfoId);
                stmtCert.setString(2, cert);
                stmtCert.executeUpdate();
            }
            conn.commit();
            return true;

        }catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
            return false;

        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }





}



