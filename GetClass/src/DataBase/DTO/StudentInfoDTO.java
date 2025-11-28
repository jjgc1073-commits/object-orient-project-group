package DataBase.DTO;

import java.util.ArrayList;
import java.util.List;
import Classes.Review;

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

    public void setSendedReviews(List<Review> sendedReviews) {
        this.sendedReviews = sendedReviews;
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
