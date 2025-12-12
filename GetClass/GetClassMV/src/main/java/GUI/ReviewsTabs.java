package GUI;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import DataBase.ConnectionDB;
import DataBase.DAOS.ReviewDAO;
import DataBase.DTO.ReviewDTO;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class ReviewsTabs extends TabPane {

    public final int tutorId;
    private List<ReviewDTO> reviews;

    public ReviewsTabs(int tutorId) {
        this.tutorId = tutorId;
        
        // 1. Cargar datos
        this.reviews = loadReviews(); 
        
        // 2. Construir la interfaz (¡IMPORTANTE llamar a esto aquí!)
        initializeTabs();
    }

    /**
     * Carga la lista de reseñas de forma segura cerrando la conexión.
     */
    private List<ReviewDTO> loadReviews() {
        // Usamos try-with-resources para cerrar la conexión automáticamente
        try (Connection conn = ConnectionDB.getConnection()) {
            
            // Asumiendo que el método en ReviewDAO es estático según corregimos antes.
            // Si no lo hiciste estático, usa: new ReviewDAO().getByTutorId(conn, tutorId);
            return ReviewDAO.getByTutorId(conn, tutorId);
            
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Retornar lista vacía en caso de error para no romper la UI
        }
    }

    /**
     * Configura las pestañas.
     */
    private void initializeTabs() {
        // Crear la pestaña
        Tab tabReviews = new Tab("Reseñas (" + (reviews != null ? reviews.size() : 0) + ")");
        tabReviews.setClosable(false); // Es mejor que no se pueda cerrar

        // Crear el panel de lista (ReviewsListPanel)
        // Nota: Asegúrate de que ReviewsListPanel maneje una lista vacía sin errores
        ReviewsListPanel reviewsListPanel = new ReviewsListPanel(this.reviews);

        // Asignar contenido a la pestaña
        tabReviews.setContent(reviewsListPanel);

        // Agregar la pestaña al TabPane
        getTabs().add(tabReviews);
    }
}