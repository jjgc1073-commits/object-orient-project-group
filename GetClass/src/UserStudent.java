import java.lang.invoke.StringConcatFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class UserStudent extends  User { // Creating the UserStudebt subclass that inherints the attributes of the parent class User

    public String levelAcademic;

    // Constructor

    public UserStudent(String name, String password, String email, LocalDate birthDate, double raiting, String aboutMe, String levelToAccess, String levelAcademic){

        super(name, password, email, birthDate, raiting, aboutMe, levelToAccess);
        this.levelAcademic = levelAcademic;

    }

    public String ShowInfo(){

        return super.ShowInfo();

    }

}
