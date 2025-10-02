import java.util.ArrayList;

public class App {

    public static void main(String[] args) {

        String[] tags = {"Mach","Programing"};

        UserTutor Villada = new UserTutor("villada perro desgraciado", "VL1241", "villa@gmail.com", 65, tags, 3.1, "nada", null);
        UserAdmin admin = new UserAdmin("admin", "admin123", "admin@gmail.com", 30, "Total");


        System.out.println(Villada.ShowInfo());

        Villada.setRating(4.5);

        System.out.println(Villada.ShowInfo());
        System.out.println(admin.ShowInfo());

   




    }
  
   
    


}
