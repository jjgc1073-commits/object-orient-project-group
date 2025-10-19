
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
import java.time.LocalDate;

public class Message {
    // assignment of general attributes
    protected Date d_ate;
    protected Date Day;
    protected Date Month;
    protected Date Year;
    protected Time Hour;
    protected Time Minute;
    protected Time Second;
    protected String Text;

    /**
     * This method return the hour, minutes, and second when u send or receive the
     * message
     * 
     * @return
     */
    private String setHour() {
        LocalDateTime LocalHour = LocalDateTime.now();
        int Hour = LocalHour.getHour();
        int Minute = LocalHour.getMinute();
        int Second = LocalHour.getSecond();
        return "[" + Hour + ":" + Minute + ":" + Second + "]";
    }

    /**
     * This method return the date when u send or receive the message
     * 
     * @return
     */
    private String setDate() {
        LocalDate LocalD_ate = LocalDate.now();
        return "[ " + LocalD_ate + " ]";
    }

}