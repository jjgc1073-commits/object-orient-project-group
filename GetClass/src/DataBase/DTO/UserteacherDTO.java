package DataBase.DTO;

import java.time.LocalDate;

import Classes.Review;

public class UserteacherDTO extends UserDTO{


    public UserteacherDTO(int id, String name, String email, String password, LocalDate birthDate){
        super(id, name, email, password, birthDate);
        
    }   

}