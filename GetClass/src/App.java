import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {


        User AEB = new UserStudent("Alejandro", "yefbhs", "alehib@gmail.com", LocalDate.of(2005, 5, 16), 4.5, "me gusta la ingenieria", "Bajo", "pre grado");


        System.out.println(AEB.ShowInfo());
        




    }
}
