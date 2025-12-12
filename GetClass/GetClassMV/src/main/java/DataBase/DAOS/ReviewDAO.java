package DataBase.DAOS;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Classes.Review;
import DataBase.DTO.ReviewDTO;
import DataBase.DTO.TutorInfoDTO;
import DataBase.DTO.UserTeacherDTO;

public class ReviewDAO {

    /**
     * Saves a UserTeacher to the database.
     * @param conn
     * @param review
     * @return
     */
    public static boolean save(Connection conn, Review review) {

        PreparedStatement stmtReview = null;
        ResultSet rsReview = null;




        try {
            conn.setAutoCommit(false);


            stmtReview = conn.prepareStatement(
                    "INSERT INTO REVIEW(tutor_user_id, student_user_id, score, comment, review_date) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            stmtReview.setInt(1, review.tutorId);
            stmtReview.setInt(2, review.studentId);
            stmtReview.setInt(3, review.rate);
            stmtReview.setString(4, review.comment);
            stmtReview.setString(5, review.date.toString());


            stmtReview.executeUpdate();
            rsReview = stmtReview.getGeneratedKeys();

            if (!rsReview.next())
                throw new SQLException("No se generó ID de reseña");

            review.setId(rsReview.getInt(1));



            conn.commit();
            return true;

        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ex) {}
            e.printStackTrace();
            return false;
        }finally {
            try { if (rsReview != null) rsReview.close(); } catch (SQLException ex) {}
            try { if (stmtReview != null) stmtReview.close(); } catch (SQLException ex) {}
            try { conn.setAutoCommit(true); } catch (SQLException ex) {}
        }
    }



    public static ReviewDTO getById(Connection conn, int tutorId) {

        PreparedStatement stmtReview   = null;

        ResultSet rsReview = null;

        try {
            stmtReview = conn.prepareStatement(
                    "SELECT * FROM REVIEW WHERE tutor_user_id = ?"
            );
            stmtReview.setInt(2, tutorId);
            rsReview = stmtReview.executeQuery();

            if (!rsReview.next())
                return null; // No se encontró la reseña
            ReviewDTO review = new ReviewDTO(
                    rsReview.getInt("review_id"),
                    rsReview.getInt("tutor_user_id"),
                    rsReview.getInt("student_user_id"),
                    rsReview.getInt("score"),
                    rsReview.getString("comment"),
                    LocalDate.parse(rsReview.getString("review_date")));
            return review;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            try { if (rsReview != null) rsReview.close(); } catch (SQLException ex) {}

            try { if (stmtReview != null) stmtReview.close(); } catch (SQLException ex) {}
        }
    }

    public static List<ReviewDTO> getByTutorId(Connection conn, int tutorId) {

    List<ReviewDTO> reviews = new ArrayList<>();

    
    String queryReviews = "SELECT * FROM REVIEW WHERE tutor_user_id = ?";
    
    
    try (PreparedStatement stmtReviews = conn.prepareStatement(queryReviews)) {

        
        stmtReviews.setInt(1, tutorId); 
        
        
        try (ResultSet rsReviews = stmtReviews.executeQuery()) {

            
            while (rsReviews.next()) {

                ReviewDTO review = new ReviewDTO(
                    rsReviews.getInt("review_id"),
                    rsReviews.getInt("tutor_user_id"),
                    rsReviews.getInt("student_user_id"),
                    rsReviews.getInt("score"),
                    rsReviews.getString("comment"),
                    LocalDate.parse(rsReviews.getString("review_date"))
                    );
                reviews.add(review);
                }
            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
    
        return reviews;
    }
}


