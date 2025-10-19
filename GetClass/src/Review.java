import java.sql.Date;
import java.time.LocalDate;

public class Review {
    public int id;
    public String author;
    public LocalDate date;
    public String text;
    public double rate;

    /**
     * Review constructor
     * @param author
     * @param text
     * @param rate
     */
    public Review(String author, String text, double rate){

        this.author = author;
        this.date = LocalDate.now();
        this.text = text;
        this.rate = rate;

    }

}
