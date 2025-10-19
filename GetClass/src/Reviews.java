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
     * Constructor: inicializa la lista de reseñas vacía
     */
    public Reviews() {
        this.reviewList = new ArrayList<>();
    }

    /**
     * Agrega una nueva reseña a la lista
     * 
     * @param review la reseña a agregar
     */
    public void addReview(Review review) {
        reviewList.add(review);
    }

    /**
     * Muestra todas las reseñas almacenadas
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
     * Calcula el promedio de las calificaciones
     * 
     * @return promedio de rating o 0 si no hay reseñas
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
     * Devuelve la cantidad de reseñas registradas
     */
    public int countReviews() {
        return reviewList.size();
    }

    public List<Review> getReviewList() {
        return reviewList;
    }
}
