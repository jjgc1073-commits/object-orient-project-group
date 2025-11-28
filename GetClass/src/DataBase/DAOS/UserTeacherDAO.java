import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
public class UserTeacherDAO {

    public static boolean save(Connection conn, UserTeacher teacher) {

        PreparedStatement stmtUser = null;
        PreparedStatement stmtInfo = null;
        PreparedStatement stmtSubject = null;
        PreparedStatement stmtCert = null;
        ResultSet rsUser = null;
        ResultSet rsInfo = null;

        

        try {
            conn.setAutoCommit(false);

            
            stmtUser = conn.prepareStatement(
                "INSERT INTO USER(name, email, password_hash, birth_date, age, role) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmtUser.setString(1, teacher.name);
            stmtUser.setString(2, teacher.email);
            stmtUser.setString(3, teacher.password);
            stmtUser.setString(4, teacher.birthDate.toString());
            stmtUser.setInt(5, teacher.age);
            stmtUser.setString(6, teacher.role);

            stmtUser.executeUpdate();
            rsUser = stmtUser.getGeneratedKeys();

            if (!rsUser.next())
                throw new SQLException("No se generó ID de usuario");

            teacher.id = rsUser.getInt(1);
            
            // INSERT TUTOR_INFO
            stmtInfo = conn.prepareStatement(
                "INSERT INTO TUTOR_INFO(user_id, about_me, hourly_rate, located_in) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmtInfo.setInt(1, teacher.id);
            stmtInfo.setString(2, teacher.tutorInfo.getAboutMe());
            stmtInfo.setInt(3, teacher.tutorInfo.getHourlyRate());
            stmtInfo.setString(4, teacher.tutorInfo.getLocatedIn());

            stmtInfo.executeUpdate();
            rsInfo = stmtInfo.getGeneratedKeys();

            if (!rsInfo.next())
                throw new SQLException("No se generó ID de tutor_info");

            int tutorInfoId = rsInfo.getInt(1);

            // INSERT SUBJECTS
            stmtSubject = conn.prepareStatement(
                "INSERT INTO TUTOR_SUBJECT(tutor_info_id, subject) VALUES (?, ?)"
            );

            for (String subject : teacher.tutorInfo.getSubjects()) {
                stmtSubject.setInt(1, tutorInfoId);
                stmtSubject.setString(2, subject);
                stmtSubject.executeUpdate();
            }

            // INSERT CERTIFICATIONS
            stmtCert = conn.prepareStatement(
                "INSERT INTO TUTOR_CERTIFICATION(tutor_info_id, certification) VALUES (?, ?)"
            );

            for (String cert : teacher.tutorInfo.getCertifications()) {
                stmtCert.setInt(1, tutorInfoId);
                stmtCert.setString(2, cert);
                stmtCert.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
            return false;
        }finally {
            try { if (rsUser != null) rsUser.close(); } catch (SQLException ex) {}
            try { if (rsInfo != null) rsInfo.close(); } catch (SQLException ex) {}
            try { if (stmtUser != null) stmtUser.close(); } catch (SQLException ex) {}
            try { if (stmtInfo != null) stmtInfo.close(); } catch (SQLException ex) {}
            try { if (stmtSubject != null) stmtSubject.close(); } catch (SQLException ex) {}
            try { if (stmtCert != null) stmtCert.close(); } catch (SQLException ex) {}
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }
}
