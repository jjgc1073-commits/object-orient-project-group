package GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox; // Usamos VBox para apilar elementos verticalmente
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContactView {

    private Scene scene;

    public ContactView(String contactNum, String email) {
        
        // 1. Creamos el contenedor raíz (root)
        VBox root = new VBox(15); // 15px de espacio entre elementos
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle("-fx-background-color: white; -fx-border-color: #be0bf5; -fx-border-width: 2;");

        // 2. Título
        Label lblTitle = new Label("Contact Information");
        lblTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblTitle.setStyle("-fx-text-fill: #4f1cc7;");

        // 3. Información
        Label lblNum = new Label("📞 Phone: " + contactNum);
        lblNum.setStyle("-fx-font-size: 14px;");

        Label lblEmail = new Label("✉️ Email: " + email);
        lblEmail.setStyle("-fx-font-size: 14px;");

        // 4. Botón Cerrar
        Button btnClose = new Button("Close");
        btnClose.setStyle("-fx-background-color: #be0bf5; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnClose.setPrefWidth(100);
        
        // Acción para cerrar
        btnClose.setOnAction(e -> {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
        });

        // 5. Agregamos todo al contenedor raíz
        root.getChildren().addAll(lblTitle, lblNum, lblEmail, btnClose);

        // 6. Creamos la escena usando 'root' (NO 'this')
        this.scene = new Scene(root, 350, 250);
    }

    public Scene getScene() {
        return scene;
    }
}