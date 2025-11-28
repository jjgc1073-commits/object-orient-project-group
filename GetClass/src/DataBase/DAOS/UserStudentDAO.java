package DataBase.DAOS;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import Classes.StudentInfo;
import Classes.UserStudent;
import DataBase.DTO.StudentInfoDTO;
import DataBase.DTO.TutorInfoDTO;
import DataBase.DTO.UserStudentDTO;
import DataBase.DTO.UserteacherDTO;

public class UserStudentDAO {


    /**
     * Saves a UserStudent to the database.
     * @param conn
     * @param student
     * @return
     */
    public static boolean save(Connection conn, UserStudent student) {

        PreparedStatement stmtUser = null;
        PreparedStatement stmtInfo = null;
        PreparedStatement stmtReview = null;
        ResultSet rsUser = null;
        ResultSet rsInfo = null;
        ResultSet rsReview = null;

        

        try {
            conn.setAutoCommit(false);

            
            stmtUser = conn.prepareStatement(
                "INSERT INTO USER(name, email, password_hash, birth_date, age, role) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            stmtUser.setString(1, student.getName());
            stmtUser.setString(2, student.getEmail());
            stmtUser.setString(3, student.getPassWord());
            stmtUser.setString(4, student.getBirthDate());
            stmtUser.setInt(5, student.getAge());
            stmtUser.setString(6, student.getRole());

            stmtUser.executeUpdate();
            rsUser = stmtUser.getGeneratedKeys();

            if (!rsUser.next())
                throw new SQLException("No se generó ID de usuario");

            student.setId(rsUser.getInt(1));
            
            // INSERT TUTOR_INFO
            stmtInfo = conn.prepareStatement(
                "INSERT INTO STUDENT_INFO(user_id, level_academic, about_me) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );

            stmtInfo.setInt(1, student.getId());
            stmtInfo.setString(2, student.studentInfo.getAcademicLevel());
            stmtInfo.setString(3, student.studentInfo.getAboutMe());

            stmtInfo.executeUpdate();
            rsInfo = stmtInfo.getGeneratedKeys();

            if (!rsInfo.next())
                throw new SQLException("No se generó ID de student_info");

            int studentInfoId = rsInfo.getInt(1);

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
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }



        
    }





    public static UserStudentDTO getById(Connection conn, int userId) {

        PreparedStatement stmtUser   = null;
        PreparedStatement stmtInfo   = null;

        ResultSet rsUser = null;
        ResultSet rsInfo = null;

        try {
            stmtUser = conn.prepareStatement(
                "SELECT * FROM USER WHERE user_id = ? AND role = 'STUDENT'"
            );
            stmtUser.setInt(1, userId);
            rsUser = stmtUser.executeQuery();

            if (!rsUser.next()) 
                return null; // No se encontró el usuario
            UserStudentDTO teacher = new UserStudentDTO(
                rsUser.getInt("user_id"),
                rsUser.getString("name"),
                rsUser.getString("password_hash"),
                rsUser.getString("email"),
                rsUser.getInt("age"),
                LocalDate.parse(rsUser.getString("birth_date")),
                rsUser.getString("role"));


            // 2. Cargar datos de TUTOR_INFO
            stmtInfo = conn.prepareStatement(
                "SELECT * FROM STUDENT_INFO WHERE user_id = ?"
                );
            stmtInfo.setInt(1, rsUser.getInt("user_id"));

        rsInfo = stmtInfo.executeQuery();
        if (!rsInfo.next())
            throw new SQLException("No existe STUDENT_INFO para el usuario");

        StudentInfoDTO info = new StudentInfoDTO();
        int tutorInfoId = rsInfo.getInt("user_id");
        info.setAcademicLevel(rsInfo.getString("level_academic"));
        info.setAboutMe(rsInfo.getString("about_me"));

        // 3. Cargar SUBJECTS
        stmtReview = conn.prepareStatement(
            "SELECT * FROM REVIEW WHERE student_user_id = ?"
        );
        stmtReview.setInt(1, tutorInfoId);
        rsReview = stmtReview.executeQuery();

        while (rsReview.next()) {
            info.addSubject(rsReview.getString("subject"));
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
    
}
