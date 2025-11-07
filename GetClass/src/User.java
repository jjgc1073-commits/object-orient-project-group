import java.time.LocalDate;

public class User extends UpdateProfile implements ShowRates {

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

    @Override
    public void getRates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRates'");
    }

    @Override
    public void calculateAverage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calculateAverage'");
    }

}




