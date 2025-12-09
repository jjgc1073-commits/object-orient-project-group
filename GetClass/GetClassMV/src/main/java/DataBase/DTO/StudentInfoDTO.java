package DataBase.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Classes.Review;
import Classes.UserStudent;
import Classes.UserTeacher;

public class StudentInfoDTO {

    public int id;
    public String aboutMe;
    public String academicLevel;
    public List<Review> sendedReviews;


    public StudentInfoDTO(){
        this.sendedReviews = new ArrayList<>();

    }

    //setters
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public void addSendedReviews(int id, int tutorId, int studentId, int score, String comment, LocalDate date) {
        Review review = new Review(id, tutorId, studentId, score, comment, date);
        sendedReviews.add(review);
    }

    //getters
    public String getAboutMe() {
        return aboutMe;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public List<Review> getSendedReviews() {
        return sendedReviews;
    }

}
