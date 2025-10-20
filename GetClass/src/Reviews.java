/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro Escobar 20251020094
 * Sebastian Zambrano 20251020102
 */

import java.util.ArrayList;
import java.util.List;

public class Reviews {
    private List<Review> reviewList;

    /**
     * Constructor: initializes the empty review list
     */
    public Reviews() {
        this.reviewList = new ArrayList<>();
    }

    /**
     * add a new review to the list
     * 
     * @param review the review to add
     */
    public void addReview(Review review) {
        reviewList.add(review);
    }

    /**
     * show all reviws in the list
     */
    public void showAllReviews() {
        if (reviewList.isEmpty()) {
            System.out.println("No reviews yet.");
        } else {
            for (Review r : reviewList) {
                System.out.println(r.toString());
                System.out.println("-------------------");
            }
        }
    }

    /**
     * calcule the avarage of the classes
     * 
     * @return average rating or 0 if there are no reviews
     */
    public double averageRating() {
        if (reviewList.isEmpty())
            return 0.0;

        double total = 0;
        for (Review r : reviewList) {
            total += r.getRating();
        }
        return total / reviewList.size();
    }

    /**
     * Returns the number of reviews recorded
     */
    public int countReviews() {
        return reviewList.size();
    }

    public List<Review> getReviewList() {
        return reviewList;
    }
}
