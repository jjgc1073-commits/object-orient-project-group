package Classes;

import java.time.LocalDate;

public class Review {
    public int id;
    public String author;
    public LocalDate date;
    public String text;
    public double rate;


    public Review(UserStudent student, String text, double rate){

        this.author = student.getName();
        this.text = setText(text);
        this.rate = setRate(rate);
        this.date = LocalDate.now();

    }

    public String setText(String text){
        if(text.length() <= 500){
            this.text = text;
        }
        else{
            System.err.println("Carácters máximos excedidos");
            this.text = text.substring(0, 500);
        }
        return this.text;
    }

    public double setRate(double rate){
        if(0 <= rate && rate <= 10){
            this.rate = rate;
        }
        return this.rate;
            
    }
    
}
