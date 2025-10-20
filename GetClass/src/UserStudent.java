/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */

import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

public class UserStudent extends User { // Creating the UserStudent subclass that inherints the attributes of the parent
                                        // class User

    public String levelAcademic;

    /**
     * Student constructor
     * 
     * @param name
     * @param password
     * @param email
     * @param birthDate
     * @param raiting
     * @param aboutMe
     * @param levelToAccess
     * @param levelAcademic
     * @param typeId
     * @param idNumber
     */

    public UserStudent(String name, String password, String email, LocalDate birthDate, double raiting, String aboutMe,
            int levelToAccess, String levelAcademic, String typeId, int idNumber) {

        super(name, password, email, birthDate, raiting, aboutMe, levelToAccess, typeId, idNumber);
        this.levelAcademic = levelAcademic;

    }

    /**
     * This method shows the students info
     */
    public String ShowInfo() {

        return super.ShowInfo();

    }

    /**
      *List of classes of the student 
      */
    private List<Appointment> appointments = new ArrayList<>(); 

    /**
       *method to add a appoinment into the schedule of the student
       */
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    /**
      * Method to show all appoitments of the student
      */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
      * Method to show the schedule of the student
      */
    public void showSchedule() {
        System.out.println(" Schedule of the student " + getName() + ":");
        if (appointments.isEmpty()) {
            System.out.println("There are no classes scheduled.");
        } else {
            for (Appointment a : appointments) {
                System.out.println(" - " + a);
            }
        }
    }

}
