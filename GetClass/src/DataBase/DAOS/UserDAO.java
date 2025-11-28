package DataBase.DAOS;
import Classes.UserTeacher;
import DataBase.DTO.UserteacherDTO;
import DataBase.DTO.TutorInfoDTO;
import DataBase.DTO.UserDTO;
import DataBase.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class UserDAO {



    

    /**
     * Authenticates a user by email and password.
     * @param conn
     * @param email
     * @param password
     * @return UserDTO if authentication is successful, null otherwise.
     */
    public static UserDTO Login(Connection Conn, String email, String password) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = Conn.prepareStatement(
                "SELECT user_id, name, email, password_hash, birth_date, age, role FROM USER WHERE email = ? AND password_hash = ?"
            );
            stmt.setString(1, email);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                String userEmail = rs.getString("email");
                String passHash = rs.getString("password_hash");
                LocalDate birthDate = LocalDate.parse(rs.getString("birth_date"));
                int age = rs.getInt("age");
                String role = rs.getString("role");

                return new UserDTO(id, name, birthDate, userEmail, age, passHash, role);

            } else {
                return null; // No user found
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
        }
    }


  
    
}
