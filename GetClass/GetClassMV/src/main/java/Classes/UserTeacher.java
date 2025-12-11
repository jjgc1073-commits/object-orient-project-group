package Classes;

import java.time.LocalDate;




public class UserTeacher extends User {

    public TutorInfo tutorInfo;


    /**
     * Constructor para crear NUEVO usuario
     */
    public UserTeacher(String name, String lastName, LocalDate birthDate, String email, String password) {
        super(name, lastName, birthDate, email, password);
        this.role = "TEACHER";
    }

    /**
     * Constructor para cargar desde BD
     */
    public UserTeacher(int id, String name, String lastName, LocalDate birthDate, String email, String password, String role) {
        super(id, name, lastName, birthDate, email, password, role);
    }

    public void setId(int id){
        this.id = id;
    }

}