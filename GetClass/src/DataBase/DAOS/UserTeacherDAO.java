package DataBase.DAOS;
import Classes.UserTeacher;
import DataBase.DTO.UserteacherDTO;
import DataBase.DTO.TutorInfoDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class UserTeacherDAO {



    /**
     * Saves a UserTeacher to the database.
     * @param conn
     * @param teacher
     * @return
     */
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
            stmtUser.setString(1, teacher.getName());
            stmtUser.setString(2, teacher.getEmail());
            stmtUser.setString(3, teacher.getPassWord());
            stmtUser.setString(4, teacher.getBirthDate());
            stmtUser.setInt(5, teacher.getAge());
            stmtUser.setString(6, teacher.getRole());

            stmtUser.executeUpdate();
            rsUser = stmtUser.getGeneratedKeys();

            if (!rsUser.next())
                throw new SQLException("No se generó ID de usuario");

            teacher.setId(rsUser.getInt(1));
            
            // INSERT TUTOR_INFO
            stmtInfo = conn.prepareStatement(
                "INSERT INTO TUTOR_INFO(user_id, about_me, hourly_rate, located_in) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmtInfo.setInt(1, teacher.getId());
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





    public static UserteacherDTO getById(Connection conn, int userId) {

        PreparedStatement stmtUser   = null;
        PreparedStatement stmtInfo   = null;
        PreparedStatement stmtSubject= null;
        PreparedStatement stmtCert   = null;

        ResultSet rsUser = null;
        ResultSet rsInfo = null;
        ResultSet rsSubject = null;
        ResultSet rsCert = null;

        try {
            stmtUser = conn.prepareStatement(
                "SELECT * FROM USER WHERE user_id = ? AND role = 'TEACHER'"
            );
            stmtUser.setInt(1, userId);
            rsUser = stmtUser.executeQuery();

            if (!rsUser.next()) 
                return null; // No se encontró el usuario
            UserteacherDTO teacher = new UserteacherDTO(
                rsUser.getInt("user_id"),
                rsUser.getString("name"),
                rsUser.getString("password_hash"),
                rsUser.getString("email"),
                rsUser.getInt("age"),
                LocalDate.parse(rsUser.getString("birth_date")),
                rsUser.getString("role"));


            // 2. Cargar datos de TUTOR_INFO
            stmtInfo = conn.prepareStatement(
                "SELECT * FROM TUTOR_INFO WHERE user_id = ?"
                );
            stmtInfo.setInt(1, rsUser.getInt("user_id"));

        rsInfo = stmtInfo.executeQuery();
        if (!rsInfo.next())
            throw new SQLException("No existe TUTOR_INFO para el usuario");

        TutorInfoDTO info = new TutorInfoDTO();
        int tutorInfoId = rsInfo.getInt("user_id");
        info.setAboutMe(rsInfo.getString("about_me"));
        info.setHourlyRate(rsInfo.getInt("hourly_rate"));
        info.setLocatedIn(rsInfo.getString("located_in"));

        // 3. Cargar SUBJECTS
        stmtSubject = conn.prepareStatement(
            "SELECT subject FROM TUTOR_SUBJECT WHERE tutor_info_id = ?"
        );
        stmtSubject.setInt(1, tutorInfoId);
        rsSubject = stmtSubject.executeQuery();

        while (rsSubject.next()) {
            info.addSubject(rsSubject.getString("subject"));
        }

        // 4. Cargar CERTIFICATIONS
        stmtCert = conn.prepareStatement(
            "SELECT certification FROM TUTOR_CERTIFICATION WHERE tutor_info_id = ?"
        );
        stmtCert.setInt(1, tutorInfoId);
        rsCert = stmtCert.executeQuery();

        while (rsCert.next()) {
            info.addCertification(rsCert.getString("certification"));
        }

        teacher.setTutorInfo(info);

        return teacher;

    } catch (SQLException e) {
        e.printStackTrace();
        return null;

        } finally {
            try { if (rsUser != null) rsUser.close(); } catch (SQLException ex) {}
            try { if (rsInfo != null) rsInfo.close(); } catch (SQLException ex) {}
            try { if (rsSubject != null) rsSubject.close(); } catch (SQLException ex) {}
            try { if (rsCert != null) rsCert.close(); } catch (SQLException ex) {}
    
            try { if (stmtUser != null) stmtUser.close(); } catch (SQLException ex) {}
            try { if (stmtInfo != null) stmtInfo.close(); } catch (SQLException ex) {}
            try { if (stmtSubject != null) stmtSubject.close(); } catch (SQLException ex) {}
            try { if (stmtCert != null) stmtCert.close(); } catch (SQLException ex) {}
        }
    }


}