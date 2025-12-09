package Classes;

import java.util.List;

public interface IRatingSystem {


    public List<Double> getRates();
    public double calculateAverageRate();
    public void addRate(double rate);
    public List<Review> getReviews();
    public void addReview(Review review);
    public double setRate(double rate);



}
