import java.sql.Date;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

public class Request {
    protected String subject;
    protected String tutorName;
    protected String studentName;
    protected String modality;
    protected double price;
    protected Date classDate;
    protected LocalTime starTime;
    protected LocalTime finishTime;
    protected Date requestDate;



    public Request(String subject, String tutorName, String studentName, String modality, double price, Date classDate, LocalTime starTime, LocalTime finishTime, Date reqDate){
        this.subject = subject;
        this.tutorName = tutorName;
        this.studentName = studentName;
        this.modality = modality;
        this.starTime = starTime;
        this.finishTime = finishTime;
        this.classDate = classDate;
        this.requestDate = requestDate;
        this.price = calculatePrice(price, starTime, finishTime);
    }


    public double calculatePrice(double price, LocalTime starTime, LocalTime finishTime){
        this.price = price * (Duration.between(starTime, finishTime).toMinutes() / 60.0);
        return this.price;
    }
    
}
