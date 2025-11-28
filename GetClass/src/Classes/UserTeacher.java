package Classes;

import java.time.LocalDate;




public class UserTeacher extends User {

    public TutorInfo tutorInfo;

    
    /**
     * Constructor para crear NUEVO usuario
     */
    public UserTeacher(String name, LocalDate birthDate, String email, String password) {
        super(name, birthDate, email, password);
        this.role = "TEACHER";
    }

    /**
     * Constructor para cargar desde BD
     */
    public UserTeacher(int id, String name, LocalDate birthDate, String email, String password, String role) {
        super(id, name, birthDate, email, password, role);
    }

    public void setId(int id){
        this.id = id;
    }

   
    



}



