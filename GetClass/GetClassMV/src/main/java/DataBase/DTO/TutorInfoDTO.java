package DataBase.DTO;

import java.util.ArrayList;
import java.util.List;

import Classes.Review;

public class TutorInfoDTO {

    public String aboutMe;
    public List<String> subjects;
    public List<String> certifications;
    public int hourlyRate;
    public String locatedIn;
    public double rating;
    protected List<Review> reviews;
    protected int tutorInfoId;


    public TutorInfoDTO() {
        this.subjects = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }


    //setters
    public void setAboutMe(String aboutMe){
        this.aboutMe = aboutMe;
    }

    public void setSubjects(List<String> subjects){
        this.subjects = subjects;
    }

    public void setCertifications(List<String> certifications){
        this.certifications = certifications;
    }

    public void setHourlyRate(int hourlyRate){
        this.hourlyRate = hourlyRate;
    }

    public void setLocatedIn(String locatedIn){
        this.locatedIn = locatedIn;
    }

    public void setRating(double rating){
        this.rating = rating;
    }

    public void setReviews(List<Review> reviews){
        this.reviews = reviews;
    }

    public void setTutorInfoId(int tutorInfoId){
        this.tutorInfoId = tutorInfoId;
    }

    //getters

    public String getAboutMe() {
        return aboutMe;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void addSubject(String subject) {
        this.subjects.add(subject);
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public void addCertification(String certification) {
        this.certifications.add(certification);
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public String getLocatedIn() {
        return locatedIn;
    }

    public double getRating() {
        return rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public int getTutorInfoId() {
        return tutorInfoId;
    }

}
