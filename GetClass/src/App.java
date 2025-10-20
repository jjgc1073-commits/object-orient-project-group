
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
import java.util.Arrays;

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
         * Review r1 = new Review("Jhon", 5.0, "Excelente tutor.",
         * Arrays.asList("Buena explicación", "Puntual"));
         * Review r2 = new Review("Sebastian", 4.0, "Muy amable y claro.",
         * Arrays.asList("Didáctico", "Paciente"));
         * Review r3 = new Review("Alejandro", 3.5, "Cumple con lo esperado.");
         * r3.addTag("Responsable"); // puedes añadir tags luego
         * 
         * reviews.addReview(r1);
         * reviews.addReview(r2);
         * reviews.addReview(r3);
         * 
         * reviews.showAllReviews();
         * System.out.println("Promedio general: " + reviews.averageRating());
         * 
         * Chat chat = new Chat("Jhon (Estudiante)", "Alejandro (Tutor)");
         * Scanner sc = new Scanner(System.in);
         * 
         * System.out.println("=== CHAT ESTUDIANTE - TUTOR ===");
         * System.out.println("Escribe 'exit' para terminar el chat.\n");
         * 
         * while (true) {
         * System.out.print("Estudiante: ");
         * String msg1 = sc.nextLine();
         * if (msg1.equalsIgnoreCase("exit")) break;
         * chat.sendMessage("Estudiante", msg1);
         * 
         * System.out.print("Tutor: ");
         * String msg2 = sc.nextLine();
         * if (msg2.equalsIgnoreCase("exit")) break;
         * chat.sendMessage("Tutor", msg2);
         * }
         * 
         * System.out.println("\n=== HISTORIAL DEL CHAT ===");
         * chat.showChatHistory();
         * sc.close();
         */
        // ------------------------------------------------------------
        //  Crear un tutor y un estudiante
        // ------------------------------------------------------------
        UserTutor tutor = new UserTutor(null, null, null, null, 0, null, 0, null, 0, null, 0, 0);
        UserStudent student = new UserStudent(
                "Jhon González",
                "1234",
                "jhon@mail.com",
                LocalDate.of(2002, 10, 5),
                4.5,
                "Me gusta programar y aprender nuevas tecnologías",
                2,
                "Universitario",
                "CC",
                1001);

        // ------------------------------------------------------------
        //  Crear una cita (Appointment)
        // ------------------------------------------------------------
        Appointment cita = new Appointment(
                tutor, // Tutor asignado
                student, // Estudiante asignado
                LocalDateTime.of(2025, 10, 22, 15, 0), // Fecha y hora
                "Programación en Java", // Tipo de clase
                "Virtual" // Modalidad
        );

        // Mostrar la cita creada
        System.out.println("\n📅 Nueva cita creada:");
        System.out.println(cita);

        // ------------------------------------------------------------
        //  Simular que el tutor acepta primero
        // ------------------------------------------------------------
        cita.tutorAccept(); // El tutor confirma la cita

        // ------------------------------------------------------------
        //  Ahora el estudiante también acepta
        // ------------------------------------------------------------
        cita.studentAccept(); // El estudiante confirma la cita

        // ------------------------------------------------------------
        //  Mostrar horarios actualizados
        // ------------------------------------------------------------
        System.out.println("\n Horario del tutor:");
        tutor.showSchedule();

        System.out.println("\n Horario del estudiante:");
        student.showSchedule();

        // ------------------------------------------------------------
        //  Crear otra cita y rechazarla
        // ------------------------------------------------------------
        Appointment cita2 = new Appointment(
                tutor,
                student,
                LocalDateTime.of(2025, 10, 25, 10, 30),
                "Cálculo Diferencial",
                "Presencial");

        System.out.println("\n Nueva cita creada:");
        System.out.println(cita2);

        // El estudiante decide rechazar
        cita2.reject("Estudiante");

        // Mostrar estado final
        System.out.println("Estado final de la cita: " + cita2.getStatus());
    }
}
