package DataBase.DTO;

import java.time.LocalDate;
import Classes.Review;

public class UserteacherDTO extends UserDTO{

    public TutorInfoDTO tutorInfo;


    public UserteacherDTO(int id, String name, String email, String password, int age, LocalDate birthDate, String role) {
        
        super(id, name, birthDate, email, age, password, role);
        
    }   

    public void setTutorInfo(TutorInfoDTO tutorInfo) {
        this.tutorInfo = tutorInfo;
    }
    
    public TutorInfoDTO getTutorInfoDTO(){
        return this.tutorInfo;
    }

}