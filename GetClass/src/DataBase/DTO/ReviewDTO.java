package DataBase.DTO;

import java.time.LocalDate;

public class ReviewDTO {

    public int id;
    public LocalDate date;
    public String comment;
    public int rate;
    public int tutorId;
    public int studentId;


    public ReviewDTO(int id, int tutorId, int studentId, int score, String comment, LocalDate date) {
        this.id = id;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.rate = score;
        this.comment = comment;
        this.date = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
