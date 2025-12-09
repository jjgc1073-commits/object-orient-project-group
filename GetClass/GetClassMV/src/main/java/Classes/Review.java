package Classes;

import java.time.LocalDate;

public class Review {
    public int id;
    public LocalDate date;
    public String comment;
    public int rate;
    public int tutorId;
    public int studentId;


    //Cuando se crea por primera vez
    public Review(UserTeacher teacher, int rate, String comment, UserStudent student){

        this.tutorId = teacher.getId();
        this.studentId = student.getId();
        this.rate = setRate(rate);
        this.comment = setText(comment);
        this.date = LocalDate.now();
    }

    //Cuando se carga de DB
    public Review(int id, int tutorId, int studentId, int score, String comment, LocalDate date){
        this.id = id;
        this.tutorId = tutorId;
        this.studentId = studentId;
        this.rate = score;
        this.comment = comment;
        this.date = date;
    }

    public void setId(int id){
        this.id = id;
    }

    public String setText(String comment){
        if(comment.length() <= 500){
            this.comment = comment;
        }
        else{
            System.err.println("Carácteres máximos excedidos");
            this.comment = comment.substring(0, 500);
        }
        return this.comment;
    }

    public int setRate(int rate){
        if(0 <= rate && rate <= 10){
            this.rate = rate;
        }
        return this.rate;

    }

    public void setTutorId(int tutorId){
        this.tutorId = tutorId;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    //getters
    public int getTutorId(){
        return this.tutorId;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public int getScore(){
        return this.rate;
    }

    public String getComment(){
        return this.comment;
    }

    public LocalDate getDate(){
        return this.date;
    }

    public int getId(){
        return this.id;
    }
}
