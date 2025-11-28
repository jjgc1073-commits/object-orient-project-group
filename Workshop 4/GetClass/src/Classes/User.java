package Classes;

import java.time.LocalDate;
import java.time.Period;

public class User implements IAuthentication, IuserBase {

    protected String role;
    protected String name;
    protected int age;
    protected int id; 
    protected String email;
    private String password;
    protected LocalDate birthDate;

    /**
     * Constructor para crear un usuario NUEVO (antes de guardarlo en la BD)
     */
    public User(String name, LocalDate birthDate, String email, String password) {
        this.name = name;
        this.birthDate = birthDate;
        this.age = calculateAge(birthDate);
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor para cargar un usuario DESDE la BD
     */
    public User(int id, String name, LocalDate birthDate, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.age = calculateAge(birthDate);
        this.email = email;
        this.password = password;
        this.role = role;
    }

    //Sets

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Gets

    public String getPassWord() {
        return this.password;
    }

    public String getBirthDate() {
        return this.birthDate.toString();
    }

    public int getAge() {
        return this.age;
    }

       @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getRole() {
        return this.role;
    }




    /**
     * Calcula edad
     */
    private int calculateAge(LocalDate birthDate){
        if (birthDate == null) {
            return 0;
        }
        LocalDate hoy = LocalDate.now();
        return Period.between(birthDate, hoy).getYears();
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Unimplemented method 'logout'");
    }

    @Override
    public void setNewPassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException("Unimplemented method 'setNewPassword'");
    }

    @Override
    public boolean login(String email, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public boolean validatePassword(String password) {
        throw new UnsupportedOperationException("Unimplemented method 'validatePassword'");
    }


}
