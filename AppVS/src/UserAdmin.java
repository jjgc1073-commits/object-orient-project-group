public class UserAdmin extends User { // Creating the UserAdmin subclass that inherints the attributes of the parent class User


    public UserAdmin(String name, String password, String email, int age, String levelToAccess){

        super(name,password,email,age,0.0,null, "Total");





    }

    public String ShowInfo(){

        return name + " " + levelToAccess;

    }

}
