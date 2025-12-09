package Classes;

import java.util.List;

public class TutorInfo {

    public String aboutMe;
    public List<String> subjects;
    public List<String> certifications;
    public int hourlyRate;
    public String locatedIn;
    public double rating;
    protected List<Review> reviews;
    protected int tutorInfoId;

    public TutorInfo(String aboutMe, List<String> subjects, List<String> certifications, int hourlyRate, String locatedIn) {
        this.aboutMe = aboutMe;
        this.subjects = subjects;
        this.certifications = certifications;
        this.hourlyRate = hourlyRate;
        this.locatedIn = locatedIn;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public String getLocatedIn() {
        return locatedIn;
    }

    public int getTutorInfoId() {
        return tutorInfoId;
    }

}
