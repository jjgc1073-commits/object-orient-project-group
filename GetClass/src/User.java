import java.time.LocalDate;
import java.time.Period;

public class User {

    protected int accessLevel;
    public String name;
    public String aboutMe;
    public  int age;
    private int id;
    protected String email;
    protected String password;
    protected LocalDate birthDate;
/**
 * 
 * constructor method of class UpdateProfile
 * @param name
 * @param aboutMe
 * @param birthDate
 * @param id
 * @param email
 * @param password
 */
    public User(String name, String aboutMe, LocalDate birthDate, int id, String email, String password) {

        this.name = name;
        this.aboutMe = aboutMe;
        this.birthDate = birthDate;
        this.age = setAge(birthDate);
        this.id = id;
        this.email = email;
        this.password = password;   

    }

    public void setEmail(String email) {

        this.email = email;


    }

    public void setAboutMe(String aboutMe) {

        this.aboutMe = aboutMe; 

    }

    public void setPassword(String password) {

        this.password = password;

    }


    /**
     * method of calculate age of a client
     * @param birthDate
     * 
     * @return age
     */
    public int setAge(LocalDate birthDate){
        if (birthDate == null) {

            return 0;

        }

        LocalDate hoy = LocalDate.now();
        return Period.between(birthDate, hoy).getYears();
    }
    
}





