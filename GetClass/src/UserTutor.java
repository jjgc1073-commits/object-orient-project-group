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

    /**
      *atributes about the tutor
      */
    private String specialization; // área de conocimiento o materia
    private int experienceYears; // años de experiencia
    private double hourlyRate; // tarifa por hora

    /**
     * Constructor de UserTutor
     * 
     * @param name            name the tutor
     * @param password        password of the tutor
     * @param email           email of the tutor
     * @param birthDate       birth date of the tutor
     * @param raiting         raiting of the tuttor
     * @param aboutMe         about me of the tutor
     * @param levelToAccess   level to access of the tutor
     * @param typeId          identification type of the tutor
     * @param idNumber        identification number of the tutor
     * @param specialization  area of ​​specialization of the tutor
     * @param experienceYears years of experience of the tutor
     * @param hourlyRate      hourly rate of the tutor
     */
    public UserTutor(String name, String password, String email, LocalDate birthDate, double raiting, String aboutMe,
            int levelToAccess, String typeId, int idNumber, String specialization, int experienceYears,
            double hourlyRate) {
        super(name, password, email, birthDate, raiting, aboutMe, levelToAccess, typeId, idNumber);
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.hourlyRate = hourlyRate;
    }

    /**
      *Getters and  Setters
      */
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
     * Method to show the informatioin of the tutor
     */
    @Override
    public String ShowInfo() {
        return super.ShowInfo() +
                "\nSpecialization: " + specialization +
                "\nExperience: " + experienceYears + " years" +
                "\nHourly Rate: $" + hourlyRate;
    }

    /**
     * Method for calculating the estimated weekly income of the tutor
     * 
     * @param hoursPerWeek number of hours worked per week
     * @return estimated income
     */
    public double calculateWeeklyIncome(int hoursPerWeek) {
        return hourlyRate * hoursPerWeek;
    }
    
    /**
      *list of classes that the tutor has accepted
      */
    private List<Appointment> appointments = new ArrayList<>();

    /**
      *Method to add a appointment to the list of the tutor 
      */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
      *Method to get all appointments of the tutor 
      */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
      *Method to show the tutor schedule
      * and show if the tutor dont have a classes into the schedule
      */
    public void showSchedule() {
        System.out.println(" Schedule of the tutor " + getName() + ":");
        if (appointments.isEmpty()) {
            System.out.println("There are no classes scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(" - " + a);
            }
        }
    }
}
