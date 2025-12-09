package DataBase.DTO;
import java.time.LocalDate;

public class UserDTO {

    public String name;
    public int age;
    public int id;
    public String email;
    public LocalDate birthDate;
    protected String password;
    public String role;


    /**
     * Constructor para cargar un usuario DESDE la BD
     */
    public UserDTO(int id, String name, LocalDate birthDate, String email, int age, String password, String role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.age = age;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //seters
    public void setName(String name){
        this.name = name;
    }

    public void setbirthDate(LocalDate birthDate){
        this.birthDate = birthDate;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // getters
    public String getName(){
        return this.name;
    }

    public LocalDate getbirthDate(){
        return this.birthDate;
    }

    public String getEmail(){
        return this.email;
    }

    public int getId(){
        return this.id;
    }

    public String getPassword(){
        return this.password;
    }

}
