package Classes;

import java.util.List;
import Classes.Review;

public class StudentInfo {

    public String aboutMe;
    public String academicLevel;
    public List<Review> sendedreviews;


    public StudentInfo(String aboutMe, String academicLevel, List<Review> sendedReviews){
        this.aboutMe = aboutMe;
        this.academicLevel = academicLevel;
        this.sendedreviews = sendedReviews;
    }


    public String getAboutMe() {
        return aboutMe;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public List<Review> getSendedReviews() {
        return sendedreviews; 
    }
    
}
