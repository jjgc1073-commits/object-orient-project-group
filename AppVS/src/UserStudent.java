import java.util.Arrays;

public class UserStudent extends  User { // Creating the UserStudebt subclass that inherints the attributes of the parent class User



    public UserStudent(String name, String password, String email, int age, Double raiting, String aboutMe, String levelToAccess){


        super(name, password, email, age,raiting, aboutMe, "Basico");





    }

    public String ShowInfo(){

        return super.ShowInfo() + levelToAccess;


    }

}
