import java.util.Arrays;
import java.util.Scanner;

public class UserTutor  extends User {  // Creating the UserTutor subclass that inherints the attributes of the parent class User

    public String[] tags; // own attribute addiction

    public UserTutor(String name, String password, String email, int age, String[] tags, Double raiting, String aboutMe, String levelToAccess) {

        super(name, password, email, age,raiting, aboutMe, "Basico"); // use of the constructor method of the parent class
        this.tags = tags; //construction of own attribute

    }

    public String ShowInfo(){

        return super.ShowInfo()  + Arrays.toString(tags) + levelToAccess; 
        
    }



}
