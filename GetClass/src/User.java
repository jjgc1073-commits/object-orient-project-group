import java.lang.invoke.StringConcatFactory;
import java.util.Arrays;
import java.util.Scanner;

// Parent class creation (User)

public class User {
    
    // assignment of general attributes of every subclass

    public String name;
    private String password;
    protected int id;
    protected String email;
    protected int age;
    public String aboutMe;
    protected Double raiting;
    protected String levelToAccess;

    // constructor method

    public User(String name, String password, String email, int age, Double raiting, String aboutMe, String levelToAccess) {

        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.setRating(raiting);
        this.aboutMe = aboutMe;
        this.levelToAccess = levelToAccess;

    }

    // Show information method

    public String ShowInfo(){

        return name + " " + String.valueOf(raiting) + " " + aboutMe + " ";
        
    }

    public void setRating(double newRating){

        if (newRating >= 0.0 && newRating <= 5.0) {

            this.raiting = newRating;
            
        }else{

            System.out.println("Valor no valido");

        }




    }







}

