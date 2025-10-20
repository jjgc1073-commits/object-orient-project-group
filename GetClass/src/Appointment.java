import java.time.LocalDateTime;

// Class that represents an appointment or class between a tutor and a student
public class Appointment {
    // Atributos principales
    private UserTutor tutor; // Tutor assigned to the class
    private UserStudent student; // Student receiving the class
    private LocalDateTime dateTime; // Date and time of the class
    private String classType; // Subject or type of class (ej. "Mathematics", "Programation")
    private String modality; // Modality of class ("Presencial" o "Virtual")
    private AppointmentStatus status; // General status of the appointment (PENDING, ACCEPTED, REJECTED)

    // Individual acceptance states
    private boolean tutorAccepted = false; // if the tutor has already accepted
    private boolean studentAccepted = false; // If the student has already accepted

     /**
     * This is the constructor of the Appointment
     * @param tutor  
     * @param USerStudent
     * @param dateTime
     * @param  classType
     * @param modality
     */
    public Appointment(UserTutor tutor, UserStudent student, LocalDateTime dateTime,
            String classType, String modality) {
        this.tutor = tutor;
        this.student = student;
        this.dateTime = dateTime;
        this.classType = classType;
        this.modality = modality;
        this.status = AppointmentStatus.PENDING; // By default, every appointment starts pending.
    }

    //methods of accepting and rejecting appointments

    // The tutor accept the appointment
    public void tutorAccept() {
        tutorAccepted = true;
        System.out.println(" The tutor " + tutor.getName() + " accepted the appointment.");
        checkStatus();                   // Check if both accepted
    }

    //The student accept the appoinmet
    public void studentAccept() {
        studentAccepted = true;
        System.out.println(" The stundent " + student.getName() + " accepted the appointment.");
        checkStatus();                  // Check if both accepted
    }

    // method to rejection the appointment (anyone)
    public void reject(String by) {
        this.status = AppointmentStatus.REJECTED;
        System.out.println(" the appointment was rejected by: " + by);
    }

    // method to put the status appointment to both (tutor and student)
    private void checkStatus() {
        if (tutorAccepted && studentAccepted) {
            this.status = AppointmentStatus.ACCEPTED;
            tutor.addAppointment(this);
            student.addAppointment(this);
            System.out.println(" The appointment was confirmed by both parties and added to the schedules..");
        }
    }

    //Methods to get informations
    

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

    // format to show when run the code
    public String toString() {
        return "Name Class: " + classType + " | Modality: " + modality + " | Date: " + dateTime +
                " | Tutor: " + tutor.getName() + " | Estudent: " + student.getName() +
                " | Status: " + status;
    }
}
