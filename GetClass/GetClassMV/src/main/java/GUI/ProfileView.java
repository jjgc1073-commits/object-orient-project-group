package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font; // Usar Font de JavaFX
import javafx.stage.Stage;
import javafx.scene.Node;

// 🟢 Heredamos de Stage para crear una nueva ventana
public class ProfileView {

    public final int id;
    public final UserTeacherDTO tutor;
    public Stage stage;
    public Scene scene;
    
    // Controles accesibles si se necesita un controlador
    public Button btnBack;
    public VBox westPanelContent; // Contenido del panel izquierdo para scroll
    public VBox eastPanel;      // Contenido del panel derecho
    
    // Controles internos para los datos del tutor
    public Label lblNameTitle;
    public HBox subjectsContainer;
    public TextArea txtAboutMe;

    public ProfileView(Stage stage, int id) {
        this.id = id;
        this.stage = stage;
        // 1. CARGA DE DATOS (Se mantiene la lógica de tu Swing)
        TutorProfile tut = new TutorProfile(id);
        this.tutor = tut.defineTutor();

        // 2. CONFIGURACIÓN DEL STAGE (Equivalente a JFrame)
        stage.setTitle("GetClasses - Perfil de " + (tutor != null ? tutor.getName() + tutor.getLastName() : "Tutor"));
        // El tamaño se establece en la escena
        stage.setResizable(true); // Permitir redimensionar (opcional)
        // setDefaultCloseOperation(EXIT_ON_CLOSE) se maneja con el cierre de la ventana
        
        // 3. ESTRUCTURA PRINCIPAL (BorderPane)
        BorderPane root = new BorderPane();
        
        // Nota: En JavaFX, no usamos directamente BorderLayout.WEST/EAST para la división 50/50,
        // sino que usamos un HBox en el CENTER, o definimos el LEFT/RIGHT. 
        
        // Usaremos un HBox para los dos paneles (West y East)
        HBox mainContent = new HBox();

        // =========================================================================
        // 4. PANEL DERECHO (EAST) - Fijo 300px
        // =========================================================================
        eastPanel = createEastPanel();
        
        // =========================================================================
        // 5. PANEL IZQUIERDO (WEST) - 600px de ancho y con Scroll
        // =========================================================================
        
        // Crear el contenido del panel izquierdo (sin scroll)
        westPanelContent = createWestPanelContent();
        
        // Envolver el contenido en un ScrollPane (Equivalente a JScrollPane)
        ScrollPane scrollWest = new ScrollPane();
        scrollWest.setContent(westPanelContent);
        scrollWest.setFitToWidth(true); // Permite que el contenido se ajuste al ancho
        scrollWest.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // No hay scroll horizontal

        // Establecer el ancho preferido para el área de desplazamiento (600px)
        scrollWest.setPrefWidth(600);
        scrollWest.setMinWidth(300); // Mínimo para que no desaparezca

        // 6. ENSAMBLAR
        
        // Añadir el panel de desplazamiento y el panel fijo al HBox
        mainContent.getChildren().addAll(scrollWest, eastPanel);
        
        // Esto asegura que si la ventana crece, el espacio extra va al ScrollPane.
        HBox.setHgrow(scrollWest, Priority.ALWAYS);

        // Colocar el HBox en el centro del BorderPane (Root)
        root.setCenter(mainContent);
        
        // 7. CONFIGURACIÓN FINAL DE LA ESCENA
        this.scene = new Scene(root, 900, 700); // Tamaño inicial de 900x700
        this.stage.setScene(scene);
    }
    
    // --- MÉTODOS DE CREACIÓN DE PANELES ---

    private VBox createEastPanel() {
        VBox panel = new VBox(20); 
        panel.setPrefWidth(300);
        panel.setPadding(new Insets(30, 15, 15, 15));
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setStyle("-fx-background-color: #f0f0f0; -fx-border-color: lightgray; -fx-border-width: 0 0 0 1;");

        // 1. Header (Foto, Nombre y Rating)
        Node header = addEastHeader();
        
        // 2. Etiqueta de Precio
        Label priceLabel = new Label("$" + String.format("%d", tutor.tutorInfo.getHourlyRate()) + " / hour");
        priceLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #be0bf5ff;");
        
        // 3. Botón de Acción
        Button btnBook = new Button("Contact with me");
        btnBook.setMaxWidth(Double.MAX_VALUE);
        btnBook.setPrefHeight(40);
        btnBook.setStyle("-fx-font-size: 16px; -fx-background-color: #4f1cc7ff; -fx-text-fill: white; -fx-background-radius: 5;");
        
        // 4. Separador (Para empujar el botón al fondo si el panel es alto)
        Region separator = new Region();
        VBox.setVgrow(separator, Priority.ALWAYS); 
        
        // Añadir elementos al panel
        panel.getChildren().addAll(header, priceLabel, separator, btnBook);

        return panel;
    }

    private VBox createWestPanelContent() {
        // Usamos VBox para apilar el header y el aboutme (BoxLayout.Y_AXIS)
        VBox content = new VBox(20);
        content.setPadding(new Insets(20));
        
        // Añadir las secciones del panel West (Oeste)
        content.getChildren().addAll(
            addWestHeader(),
            addAboutMe()
        );
        
        return content;
    }

    // --- MÉTODOS AUXILIARES (Migrados de tu Swing) ---

    public Node addWestHeader() {
        // En Swing usabas un JPanel(BoxLayout.Y_AXIS)
        VBox header = new VBox(15);
        header.setAlignment(Pos.CENTER); // Para centrar el título y el FlowLayout
        
        // Panel superior (Botón Atrás) - BorderLayout.WEST en Swing
        HBox topBar = new HBox();
        btnBack = new Button("←"); // Ahora como atributo
        topBar.getChildren().add(btnBack);
        topBar.setAlignment(Pos.CENTER_LEFT);

        header.getChildren().add(topBar);

        // Título: "Hi! I'm [Name]"
        lblNameTitle = new Label("Hi! I'm " + tutor.getName() + " " + tutor.getLastName());
        // Equivalente a setFont y setAlignmentX(Component.CENTER_ALIGNMENT)
        lblNameTitle.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        header.getChildren().add(lblNameTitle);
        // Box.createVerticalStrut(15) es VBox(15) en el constructor, pero podemos añadir un separador:
        header.getChildren().add(new Region());
        VBox.setVgrow(header.getChildren().get(header.getChildren().size() - 1), Priority.NEVER); // Separa el título de los tags

        // Panel de Materias (Subjects) - FlowLayout.CENTER en Swing
        subjectsContainer = new HBox(10); // HBox con espaciado 10
        subjectsContainer.setAlignment(Pos.CENTER); 
        
        if (tutor.tutorInfo.getSubjects() != null) {
            for (String s : tutor.tutorInfo.getSubjects()) {
                Button btn = new Button(s);
                subjectsContainer.getChildren().add(btn);
            }
        }
        header.getChildren().add(subjectsContainer);
        
        return header; 
    }


    public Node addEastHeader(){
        VBox header = new VBox(10);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(30));

        // Foto (Placeholder)
        Region photo = new Region();
        photo.setPrefSize(120, 150);
        photo.setStyle("-fx-background-color: lightgray; -fx-border-radius: 90; -fx-background-radius: 90;"); 
        
        // Nombre Completo
        Label nameLabel = new Label(tutor.getName() + " " + tutor.getLastName() + "-" + tutor.getAge());
        nameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Rating
        Label ratingLabel = new Label("Rate: " + String.format("%.1f", tutor.tutorInfo.getRating()));
        
        header.getChildren().addAll(photo, nameLabel, ratingLabel);
        
        return header;
    }


    public Node addAboutMe() {
        // Equivalente a JPanel(BoxLayout.Y_AXIS)
        VBox aboutme = new VBox(10);
        aboutme.setPadding(new Insets(10)); // Borde de 10
        aboutme.setAlignment(Pos.CENTER_LEFT); // Alineación a la izquierda

        // Título: "About Me:"
        HBox titlePanel = new HBox(); // Equivalente a FlowLayout.LEFT
        Label abouttittle = new Label("About Me:");
        abouttittle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        titlePanel.getChildren().add(abouttittle);

        // Área de texto (JTextArea)
        txtAboutMe = new TextArea(tutor.tutorInfo.getAboutMe());
        txtAboutMe.setEditable(false);
        txtAboutMe.setWrapText(true); // setLineWrap(true) y setWrapStyleWord(true)
        txtAboutMe.setPrefHeight(150); // Altura preferida
        txtAboutMe.setMaxHeight(Region.USE_PREF_SIZE); // No permite que se estire demasiado

        aboutme.getChildren().addAll(titlePanel, txtAboutMe);
        
        // Nota: En el diseño original, el JScrollPane del westPanel contendría
        // el header y el aboutme apilados. Al usar VBox como westPanelContent
        // y añadir estas dos secciones, la estructura se mantiene.
        
        return aboutme;
    }

    public Node addReviews(){
        
    }


    

    public Scene getScene(){
        return scene;
    }
}