package DataBase.DTO;

import java.time.LocalDate;
import Classes.Review;

public class UserTeacherDTO extends UserDTO{

    public TutorInfoDTO tutorInfo;


    public UserTeacherDTO(int id, String name, String lastName, String email, String password, int age, LocalDate birthDate, String role) {

        super(id, name, lastName, birthDate, email, age, password, role);

    }

    public void setTutorInfo(TutorInfoDTO tutorInfo) {
        this.tutorInfo = tutorInfo;
    }

    public TutorInfoDTO getTutorInfoDTO(){
        return this.tutorInfo;
    }

}