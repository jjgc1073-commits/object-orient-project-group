package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserStudentDAO;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserStudentDTO;
import DataBase.DTO.UserTeacherDTO;

public class TutorProfile {

    public int id;
    public Connection conn;
    public UserTeacherDAO dao;
    public UserStudentDAO sdao;


    public TutorProfile(int id){
        this.id = id;
        this.conn = ConnectionDB.getConnection();
    }


    public UserTeacherDTO defineTutor(){
        return UserTeacherDAO.getById(conn, this.id);
    }

    public UserStudentDTO defineStudent(){
        return UserStudentDAO.getById(conn, id);
    }
    
}
