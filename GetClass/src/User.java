import java.time.LocalDate;

public class User extends UpdateProfile {

    protected int accessLevel;

    
    
    /**
     * constructor method of class user
     * @param name
     * @param aboutMe
     * @param birthDate
     * @param id
     * @param email
     * @param password
     * @param accessLevel
     */
    public User(String name, String aboutMe, LocalDate birthDate, int id, String email, String password, int accessLevel) {
        super(name, aboutMe, birthDate, id, email, password);
        this.accessLevel = accessLevel;
    }



}




