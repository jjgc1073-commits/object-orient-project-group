/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class RequestClass {

    private int id;
    protected double price;
    protected LocalDate date;
    protected LocalTime startTime;
    protected LocalTime finishTime;
    protected String subject;
    protected String tutorName;
    protected String studentName;

    /**
     * This is the constructor of the request
     * @param hourlydate
     * @param subject
     * @param tutorName
     * @param studentName
     * @param startTime
     * @param finishTime
     */
    public RequestClass(double hourlydate, String subject, String tutorName, String studentName, LocalTime startTime, LocalTime finishTime){

        this.date = LocalDate.now();
        this.finishTime = finishTime;
        this.tutorName = tutorName;   // hay que mirar como definimos nosotros esa hora
        this.startTime = startTime;  // hay que mirar como definimos nosotros esa hora
        this.subject = subject;
        this.price = setPrice(hourlydate, startTime, finishTime);
    }

    /**
     * This method calculates the class price depending on the time
     * @param hourlydate
     * @param startTime
     * @param finishTime
     * @return
     */
    public double setPrice(double hourlydate, LocalTime startTime, LocalTime finishTime){
        if(hourlydate>0.0){
            return Duration.between(startTime, finishTime).get(java.time.temporal.ChronoUnit.HOURS) * hourlydate;
        }
        else{
            System.out.println("Data error. contact with support");
            return 0;
        }

    }
    
}
