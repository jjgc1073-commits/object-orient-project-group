package GUI.Controller;

import DataBase.DTO.TutorInfoDTO;
import DataBase.DTO.UserTeacherDTO;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TutorProfile {

    // Controles de la vista (variables de instancia)
    private ImageView imgProfile;
    private Label lblName;
    private Label lblLocation;
    private Label lblHourlyRate; // Usada para mostrar el valor de la tarifa
    private Label lblAboutMe;
    private VBox subjectsContainer; // Usado para "How I teach" o Especialidades
    private VBox certificationsContainer; // Usado para Reviews/Certificaciones

    // Elementos nuevos añadidos en ProfileView:
    private Label lblCost; // Título "COST/HOUR"
    private Label lblResponseTime; // Etiqueta para el tiempo de respuesta
    private Button btnSchedule;
    private Button btnContact;
    private Button btnRate;


    // *** CONSTRUCTOR ACTUALIZADO PARA RECIBIR TODOS LOS ELEMENTOS DE PROFILEVIEW ***
    public TutorProfile(ImageView imgProfile, Label lblName, Label lblLocation,
                        Label lblHourlyRate, Label lblAboutMe,
                        VBox subjectsContainer, VBox certificationsContainer,
                        Label lblCost, Label lblResponseTime,
                        Button btnSchedule, Button btnContact, Button btnRate) {

        this.imgProfile = imgProfile;
        this.lblName = lblName;
        this.lblLocation = lblLocation;
        this.lblHourlyRate = lblHourlyRate;
        this.lblAboutMe = lblAboutMe;
        this.subjectsContainer = subjectsContainer;
        this.certificationsContainer = certificationsContainer;

        // Inicializar nuevos elementos
        this.lblCost = lblCost;
        this.lblResponseTime = lblResponseTime;
        this.btnSchedule = btnSchedule;
        this.btnContact = btnContact;
        this.btnRate = btnRate;
    }

    public void loadTutorData(UserTeacherDTO dto) {

        // --- 1. Verificación de Nulidad (Previene NullPointerException en el inicio) ---
        if (dto == null) {
            System.err.println("❌ Error de Carga de Datos: UserTeacherDTO es nulo. No se encontró el tutor.");

            // Establecer valores por defecto/error en todas las etiquetas principales
            lblName.setText("Tutor No Encontrado");
            lblLocation.setText("");
            lblAboutMe.setText("No se pudo cargar la información del perfil.");

            if (lblHourlyRate != null) lblHourlyRate.setText("N/A");
            if (lblResponseTime != null) lblResponseTime.setText("N/A");
            if (subjectsContainer != null) subjectsContainer.getChildren().clear();

            return;
        }

        // --- 2. Carga de Datos de Usuario y Tutor ---

        // Datos de Usuario (Nombre y Apellido)
        lblName.setText(dto.getName() + " " + dto.getLastName());

        // Acceso a la información detallada del tutor
        TutorInfoDTO info = dto.tutorInfo;

        // Nota: Se asume que 'info' no es nulo, lo cual es manejado en el DAO.
        if (info != null) {

            // Sección de Datos Básicos y Tarifa (Panel Derecho)
            this.lblHourlyRate.setText("$" + info.getHourlyRate());
            this.lblLocation.setText("Ubicación: " + info.getLocatedIn());

            // Sección "About me"
            this.lblAboutMe.setText(info.getAboutMe());

            // Tiempo de Respuesta (Necesitarás obtener este valor de algún lugar)
            this.lblResponseTime.setText("24 horas"); // Ejemplo fijo

            // Subjects (Sección "How I Teach" / Especialidades)
            if (subjectsContainer != null) {
                subjectsContainer.getChildren().clear();
                for (String s : info.getSubjects()) {
                    subjectsContainer.getChildren().add(new Label("• " + s));
                }
            }

            // Certifications (o contenido de Reviews/Certificaciones)
            if (certificationsContainer != null) {
                certificationsContainer.getChildren().clear();
                for (String c : info.getCertifications()) {
                    certificationsContainer.getChildren().add(new Label("✔ " + c));
                }
            }
        } else {
            // Manejar caso donde el UserTeacher existe pero le falta TutorInfoDTO
            System.err.println("Advertencia: Falta la información de TutorInfo para el usuario.");
            if (lblAboutMe != null) lblAboutMe.setText("Información de tutor no disponible.");
        }

        // Estilo o imagen (Placeholder)
        imgProfile.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
    }
}