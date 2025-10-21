
/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) throws Exception {

        /**
         * User AEB = new UserStudent("Alejandro", "yefbhs", "alehib@gmail.com",
         * LocalDate.of(2005, 5, 16), 4.5,
         * "me gusta la ingenieria", 1, "pre grado", "Cédula", 785412101);
         * 
         * 
         * 
         * System.out.println(AEB.ShowInfo());
         */

        /**
         * Reviews reviews = new Reviews();
         * 
         * Review r1 = new Review("Jhon", 5.0, "Excelent tutor.",
         * Arrays.asList("Buena explicación", "Puntual"));
         * Review r2 = new Review("Sebastian", 4.0, "Very friendly and clear.",
         * Arrays.asList("Didactic", "Patient"));
         * Review r3 = new Review("Alejandro", 3.5, "Meets expectations.");
         * r3.addTag("responsible"); // u can add tags later
         * 
         * reviews.addReview(r1);
         * reviews.addReview(r2);
         * reviews.addReview(r3);
         * 
         * reviews.showAllReviews();
         * System.out.println("general average: " + reviews.averageRating());
         * 
         * Chat chat = new Chat("Jhon (Student)", "Alejandro (Tutor)");
         * Scanner sc = new Scanner(System.in);
         * 
         * System.out.println("=== CHAT STUDENT - TUTOR ===");
         * System.out.println("write 'exit' to end the chat .\n");
         * 
         * while (true) {
         * System.out.print("Student: ");
         * String msg1 = sc.nextLine();
         * if (msg1.equalsIgnoreCase("exit")) break;
         * chat.sendMessage("Student", msg1);
         * 
         * System.out.print("Tutor: ");
         * String msg2 = sc.nextLine();
         * if (msg2.equalsIgnoreCase("exit")) break;
         * chat.sendMessage("Tutor", msg2);
         * }
         * 
         * System.out.println("\n=== CHAT HISTORY ===");
         * chat.showChatHistory();
         * sc.close();
         */
        //to crew a new tutor and new student
        UserTutor tutor = new UserTutor(null, null, null, null, 0, null, 0, null, 0, null, 0, 0);
        Schedule available = new Schedule(true);
        UserStudent student = new UserStudent("Jhon González",
                "1234",
                "jhon@mail.com",
                LocalDate.of(2002, 10, 5),
                4.5,
                "I love the progrmation and love the new technologies",
                2,
                "Universitary",
                "CC",
                1001);
          
        

      
        //  crew a new Appointment
        
        RequestClass request = new RequestClass(60000, "maths", tutor.getName() , student.getName(), LocalTime.of(15, 30), LocalDate.of(2025, 10, 25), LocalTime.of(17, 00), "Virutal");


        // show de created appointment
        System.out.println("\n Request class sended:");
        request.sendRequest();

        //  simulate if the tutor accept the apoinment first
       
        ; // the tutor accept the appoinmet first

       request.setAnswer(1, 1, tutor, available, tutor.getSchedule() );
        
       
        //  show schedules refresh
        
        System.out.println("\n Schedule of tutor:");
        tutor.showSchedule();

        System.out.println("\n Schedule of student:");
        student.showSchedule();


        
        //  crew a new appointment and reject
        
        RequestClass cita2 = new RequestClass(60000, "math", tutor.getName(), student.getName(), LocalTime.of(12, 30, 0), LocalDate.of(2025, 10, 25), LocalTime.of(14, 0, 0), "virtual" );

        System.out.println("\n New appointment created:");
        System.out.println(cita2.getInfoRequest());


        // show the final status to the apointmet
        System.out.println(" Final status to the appoointment " + cita2.getStatus());
    }
}
