package DataBase.DTO;

import java.time.LocalDate;

public class UserStudentDTO extends UserDTO{

    public StudentInfoDTO studentInfo;

    public UserStudentDTO(int id, String name, String password, String email, int age, LocalDate birthDate,    String role){
        super(id, name, birthDate, email, age, password, role);
        
    } 

    public void setStudentInfo(StudentInfoDTO studentInfo){
        this.studentInfo = studentInfo;
    }

    public StudentInfoDTO getStudentInfo(){
        return this.studentInfo;
    }
    
}
