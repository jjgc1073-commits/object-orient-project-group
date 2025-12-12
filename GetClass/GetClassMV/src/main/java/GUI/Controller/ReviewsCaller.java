package GUI.Controller;

import java.sql.Connection;

import DataBase.ConnectionDB;
import DataBase.DAOS.ReviewDAO;
import DataBase.DTO.ReviewDTO;

public class ReviewsCaller {

    public int tutorId;
    public Connection conn;
    public ReviewDAO dao;

    public ReviewsCaller(int tutorId){
        conn = ConnectionDB.getConnection();
        this.tutorId = tutorId;
        this.dao = new ReviewDAO();
    }

    public ReviewDTO defineReviews(){
        return ReviewDAO.getById(conn, tutorId);
    }
    
}
