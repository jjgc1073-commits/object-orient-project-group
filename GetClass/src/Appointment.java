import java.time.LocalDateTime;

// Clase que representa una cita o clase entre un tutor y un estudiante
public class Appointment {
    // Atributos principales
    private UserTutor tutor; // Tutor asignado a la clase
    private UserStudent student; // Estudiante que recibe la clase
    private LocalDateTime dateTime; // Fecha y hora de la clase
    private String classType; // Tipo de clase (ej. "Matemáticas", "Programación")
    private String modality; // Modalidad de la clase ("Presencial" o "Virtual")
    private AppointmentStatus status; // Estado general de la cita (PENDING, ACCEPTED, REJECTED)

    // Estados de aceptación individuales
    private boolean tutorAccepted = false; // Si el tutor ya aceptó
    private boolean studentAccepted = false; // Si el estudiante ya aceptó

    // Constructor de la cita
    public Appointment(UserTutor tutor, UserStudent student, LocalDateTime dateTime,
            String classType, String modality) {
        this.tutor = tutor;
        this.student = student;
        this.dateTime = dateTime;
        this.classType = classType;
        this.modality = modality;
        this.status = AppointmentStatus.PENDING; // Por defecto toda cita comienza pendiente
    }

    // ------------------------------------------------------------
    // Métodos de aceptación y rechazo
    // ------------------------------------------------------------

    // El tutor acepta la cita
    public void tutorAccept() {
        tutorAccepted = true;
        System.out.println("📘 El tutor " + tutor.getName() + " aceptó la cita.");
        checkStatus(); // Verifica si ambos aceptaron
    }

    // El estudiante acepta la cita
    public void studentAccept() {
        studentAccepted = true;
        System.out.println("📗 El estudiante " + student.getName() + " aceptó la cita.");
        checkStatus(); // Verifica si ambos aceptaron
    }

    // Método para rechazar una cita (por cualquiera)
    public void reject(String by) {
        this.status = AppointmentStatus.REJECTED;
        System.out.println("❌ La cita fue rechazada por: " + by);
    }

    // ------------------------------------------------------------
    // Lógica interna para actualizar el estado
    // ------------------------------------------------------------
    private void checkStatus() {
        if (tutorAccepted && studentAccepted) {
            this.status = AppointmentStatus.ACCEPTED;
            tutor.addAppointment(this);
            student.addAppointment(this);
            System.out.println("✅ La cita fue confirmada por ambas partes y añadida a los horarios.");
        }
    }

    // ------------------------------------------------------------
    // Métodos de acceso (Getters)
    // ------------------------------------------------------------

    public AppointmentStatus getStatus() {
        return status;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getClassType() {
        return classType;
    }

    public String getModality() {
        return modality;
    }

    public UserTutor getTutor() {
        return tutor;
    }

    public UserStudent getStudent() {
        return student;
    }

    // ------------------------------------------------------------
    // Representación en texto (para mostrar en consola)
    // ------------------------------------------------------------
    @Override
    public String toString() {
        return "Clase: " + classType + " | Modalidad: " + modality + " | Fecha: " + dateTime +
                " | Tutor: " + tutor.getName() + " | Estudiante: " + student.getName() +
                " | Estado: " + status;
    }
}
