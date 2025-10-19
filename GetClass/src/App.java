
/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */
import java.time.LocalDate;

public class App {
    public static void main(String[] args) throws Exception {

        User AEB = new UserStudent("Alejandro", "yefbhs", "alehib@gmail.com", LocalDate.of(2005, 5, 16), 4.5,
                "me gusta la ingenieria", "Bajo", "pre grado");

        System.out.println(AEB.ShowInfo());

    }
}
