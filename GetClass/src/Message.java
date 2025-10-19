import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Message {

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
        LocalDateTime LocalDate = LocalDateTime.now();
        int Hour = LocalDate.getHour();
        int Minute = LocalDate.getMinute();
        int Second = LocalDate.getSecond();
        return "[" + Hour + ":" + Minute + ":" + Second + "]";
    }
    /**
     * This method return the date when u send or receive the message
     * 
     * @return
     */

}