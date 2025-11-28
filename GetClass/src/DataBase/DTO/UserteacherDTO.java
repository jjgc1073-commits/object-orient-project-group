package DataBase.DTO;

import java.time.LocalDate;
import java.util.List;

import Classes.Review;

public class UserteacherDTO extends UserDTO{

    public String aboutMe;
    public List<String> subjects;
    public List<String> certifications;
    public int hourlyRate;
    public String locatedIn;
    public double rating;
    protected List<Review> reviews;
    protected int tutorInfoId;


    public UserteacherDTO(int id, String name, String email, String password, LocalDate birthDate, String aboutMe, List<String> subjects
        , List<String> certifications, int hourlyRate, String locatedIn, double rating, List<Review> reviews, int tutorInfoId ){
        super(id, name, email, password, birthDate);
        this.aboutMe = aboutMe;
        this.subjects = subjects;
        this.certifications = certifications;
        this.hourlyRate = hourlyRate;
        this.locatedIn = locatedIn;
        this.rating = rating;
        this.reviews = reviews;
        this.tutorInfoId = tutorInfoId;
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

    public List<String> getCertifications() {
        return certifications;
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