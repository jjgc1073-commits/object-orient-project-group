package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.UserTeacherDAO;
import DataBase.DTO.UserteacherDTO;

public class TutorProfile {

    public int id;
    public Connection conn;
    public UserTeacherDAO dao;


    public TutorProfile(int id){
        this.id = id;
        this.conn = ConnectionDB.getConnection();
        this.dao = new UserTeacherDAO();

    }


    public UserteacherDTO defineTutor(){
        return this.dao.getById(conn, this.id);
    }
    
}
