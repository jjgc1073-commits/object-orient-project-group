/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserTutor extends User {

    // Atributos propios del tutor
    private String specialization; // área de conocimiento o materia
    private int experienceYears; // años de experiencia
    private double hourlyRate; // tarifa por hora

    /**
     * Constructor de UserTutor
     * 
     * @param name            nombre del tutor
     * @param password        contraseña
     * @param email           correo
     * @param birthDate       fecha de nacimiento
     * @param raiting         calificación
     * @param aboutMe         descripción breve
     * @param levelToAccess   nivel de acceso
     * @param typeId          tipo de identificación
     * @param idNumber        número de identificación
     * @param specialization  área de especialización
     * @param experienceYears años de experiencia
     * @param hourlyRate      tarifa por hora
     */
    public UserTutor(String name, String password, String email, LocalDate birthDate, double raiting, String aboutMe,
            int levelToAccess, String typeId, int idNumber, String specialization, int experienceYears,
            double hourlyRate) {
        super(name, password, email, birthDate, raiting, aboutMe, levelToAccess, typeId, idNumber);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.hourlyRate = hourlyRate;
    }

    // Getters y Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * Método para mostrar la información del tutor
     */
    @Override
    public String ShowInfo() {
        return super.ShowInfo() +
                "\nSpecialization: " + specialization +
                "\nExperience: " + experienceYears + " years" +
                "\nHourly Rate: $" + hourlyRate;
    }

    /**
     * Método para calcular el ingreso estimado semanal del tutor
     * 
     * @param hoursPerWeek número de horas trabajadas por semana
     * @return ingreso estimado
     */
    public double calculateWeeklyIncome(int hoursPerWeek) {
        return hourlyRate * hoursPerWeek;
    }

    private List<Appointment> appointments = new ArrayList<>(); // Lista de clases aceptadas por el tutor

    /** */

    // Método para agregar una cita a la lista del tutor
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    // Método para obtener todas las citas del tutor
    public List<Appointment> getAppointments() {
        return appointments;
    }

    // Método para mostrar el horario del tutor
    public void showSchedule() {
        System.out.println("📅 Horario del tutor " + getName() + ":");
        if (appointments.isEmpty()) {
            System.out.println("No hay clases programadas.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(" - " + a);
            }
        }
    }
}