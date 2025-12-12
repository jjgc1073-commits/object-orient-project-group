package DataBase.DTO;
import java.time.LocalDate;

public class UserDTO {

    public String name;
    public String lastName;
    public int age;
    public int id;
    public String email;
    public LocalDate birthDate;
    protected String password;
    public String role;


    /**
     * Constructor para cargar un usuario DESDE la BD
     */
    public UserDTO(int id, String name, String lastName, LocalDate birthDate, String email, int age, String password, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
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
    
    public void setLastName(String lastName){
        this.lastName = lastName;
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

    public String getLastName(){
        return this.lastName;
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

<<<<<<< HEAD
    public int getAge(){
        return this.age;
    }
=======
    public int getAge(){ return this.age;}
>>>>>>> cb3d788b606d18b7c04d075c8d67376816cf3edc

}
