import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Request {
    protected String subject;
    protected String tutorName;
    protected String studentName;
    protected String modality;
    protected double price;
    protected LocalDate classDate;
    protected LocalTime starTime;
    protected LocalTime finishTime;
    protected LocalDate requestDate;



    public Request(String subject, String tutorName, String studentName, String modality, double price, int dayClass, int monthClass, int yearClass, 
    int hourStartClass, int minuteStartClass, int hourFinishClass, int minuteFinishClass){
        this.subject = subject;
        this.tutorName = tutorName;
        this.studentName = studentName;
        this.modality = modality;
        this.starTime = LocalTime.of(hourStartClass, minuteStartClass, 0);
        this.finishTime = LocalTime.of(hourFinishClass, minuteStartClass, 0);
        this.classDate = setClassDate(yearClass, monthClass, dayClass);
        this.requestDate = LocalDate.now();
        this.price = calculatePrice(price, starTime, finishTime);
    }

    /**
     * This method validates that class date is after the current date
     * @param yearClass
     * @param monthClass
     * @param dayClass
     * @return
     */
    public LocalDate setClassDate(int yearClass, int monthClass, int dayClass){
        if(LocalDate.now().isBefore(LocalDate.of(yearClass, monthClass, dayClass))){
            this.classDate = LocalDate.of(yearClass, monthClass, dayClass);
            return this.classDate;
        }
        else{
            return null;
        }
    }


    /**
     * This method calculate the price with the hourly rate and the class duration
     * @param price
     * @param starTime
     * @param finishTime
     * @return
     */
    public double calculatePrice(double price, LocalTime starTime, LocalTime finishTime){
        this.price = price * (Duration.between(starTime, finishTime).toMinutes() / 60.0);
        return this.price;
    }
    
}
