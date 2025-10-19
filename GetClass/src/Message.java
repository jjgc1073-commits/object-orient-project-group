/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;

public class Message {
    protected LocalDate date;
    protected LocalTime time;
    protected String text;
    
/**
 * This is the message constructor
 * @param text
 */

    public Message(String text){

        this.text = text;
        this.date = LocalDate.now();
        this.time = LocalTime.now().truncatedTo(java.time.temporal.ChronoUnit.SECONDS);
        
    }
    
    /**
     * This method return the hour, minutes, and second when you send or receive the
     * message
     * 
     * @return time
     */
    private LocalTime getHour() {
        return time;
    }

    /**
     * This method return the date when u send or receive the message
     * 
     * @return date
     */
    private LocalDate getDate() {
        return date;
    }

}