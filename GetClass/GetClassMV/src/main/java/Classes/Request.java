package Classes;

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
    protected LocalTime startTime;
    protected LocalTime finishTime;
    protected LocalDate requestDate;

    public Request(String subject, String tutorName, String studentName, String modality,
                   double hourlyRate, int dayClass, int monthClass, int yearClass,
                   int hourStartClass, int minuteStartClass,
                   int hourFinishClass, int minuteFinishClass) {

        this.subject = subject;
        this.tutorName = tutorName;
        this.studentName = studentName;
        this.modality = modality;

        this.startTime = LocalTime.of(hourStartClass, minuteStartClass, 0);
        this.finishTime = LocalTime.of(hourFinishClass, minuteFinishClass, 0);

        this.classDate = setClassDate(yearClass, monthClass, dayClass);
        this.requestDate = LocalDate.now();

        this.price = calculatePrice(hourlyRate, startTime, finishTime);
    }

    public LocalDate setClassDate(int yearClass, int monthClass, int dayClass) {
        LocalDate input = LocalDate.of(yearClass, monthClass, dayClass);

        if (!LocalDate.now().isBefore(input)) {
            throw new IllegalArgumentException("La clase debe ser en una fecha posterior a hoy.");
        }

        this.classDate = input;
        return this.classDate;
    }

    public double calculatePrice(double hourlyRate, LocalTime startTime, LocalTime finishTime) {
        double hours = Duration.between(startTime, finishTime).toMinutes() / 60.0;
        this.price = hourlyRate * hours;
        return this.price;
    }
}
