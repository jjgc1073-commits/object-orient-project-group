/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro Escobar 20251020094
 * Sebastian Zambrano 20251020102
 */

import java.util.ArrayList;
import java.util.List;

public class Review {
    private String authorName;
    private double rating;
    private String comment;
    private List<String> tags; // ← NUEVO: lista de etiquetas descriptivas

    /**
     * constuctor main with tags abouthe to make the revies
     * 
     * @param authorName name of author
     * @param rating     rate (0.0 a 5.0)
     * @param comment    comments of the author
     * @param tags       list with tags 
     */
    public Review(String authorName, double rating, String comment, List<String> tags) {
        this.authorName = authorName;
        this.rating = rating;
        this.comment = comment;
        this.tags = (tags != null) ? new ArrayList<>(tags) : new ArrayList<>();
    }

    /**
     * constructor without tags (optional)
     */
    public Review(String authorName, double rating, String comment) {
        this(authorName, rating, comment, new ArrayList<>());
    }

    /**
      *Getters y Setters
      */
    
    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * add a new tag
     * 
     * @param tag tect in the tag
     */
    public void addTag(String tag) {
        if (tag != null && !tag.isEmpty() && !tags.contains(tag)) {
            tags.add(tag);
        }
    }

    /**
     * Returns a human-readable description of the review
     */
    @Override
    public String toString() {
        return "Author: " + authorName +
                "\nRating: " + rating +
                "\nComment: " + comment +
                "\nTags: " + (tags.isEmpty() ? "No tags" : String.join(", ", tags)) + "\n";
    }
}
