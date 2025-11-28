package DataBase.DTO;
import java.time.LocalDate;

public class UserDTO {
    
    public String name;
    public int age;
    public int id; 
    public String email;
    public LocalDate birthDate;
    protected String password;


    public UserDTO(int id, String name, String email, String password, LocalDate birthDate){
        this.name = name;
        this.id = id;
        this.email = email;
        this.birthDate = birthDate;
        this.password = password;
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
