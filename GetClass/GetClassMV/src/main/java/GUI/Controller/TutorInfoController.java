package GUI.Controller;

import Classes.TutorInfo;
import Classes.UserStudent;
import Classes.UserTeacher;
import DataBase.ConnectionDB;
import DataBase.DAOS.UserStudentDAO;
import DataBase.DAOS.UserTeacherDAO;
import GUI.TutorInfoForm;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TutorInfoController {

    
    public TutorInfoForm view;
    public UserTeacherDAO dao;
    public String name;
    public String lastname;
    public LocalDate birthday;
    public String email;
    public String pass;
    

    public TutorInfoController(TutorInfoForm view, String name, String lastName, LocalDate birthday, String email, String pass){
        this.name = name;
        this.lastname = lastName;
        this.birthday = birthday;
        this.email = email;
        this.pass = pass;
        this.view = view;
        this.dao = new UserTeacherDAO();

        this.view.btnRegister.setOnAction(e -> register());

    }   

    public void register(){
        int price = 0;
        String aboutMe = this.view.txtAboutMe.getText();
        List<String> subjects = this.view.subjects.getSelectionModel().getSelectedItems();
        String certs = this.view.txtcert.getText();
        List<String> certificates = Arrays.asList(certs.split(",")).stream().map(String::trim).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        String priceText = this.view.txtprice.getText().trim();
        boolean priceValid = true;
        String location = this.view.txtLocated.getText();
        
        List<String> errores = new ArrayList<>();
        try {
            if (!priceText.isEmpty()) {
                price = Integer.parseInt(priceText); 
            } else {
                priceValid = false; // El campo está vacío
            }
        } catch (NumberFormatException e) {
            priceValid = false; 
        }

        if(aboutMe.isEmpty()){
             errores.add("Please tell us about yourself");
            view.txtAboutMe.setStyle("-fx-border-color: red;");
        }
        if(subjects.isEmpty()){
             errores.add("Please add subjects");
            view.subjects.setStyle("-fx-border-color: red;");
        }
        if(certificates.isEmpty()){
             errores.add("Please add certificates");
            view.txtcert.setStyle("-fx-border-color: red;");
        }
        if(location.isEmpty()){
             errores.add("Please add you location");
            view.txtLocated.setStyle("-fx-border-color: red;");
        }
        if(!priceValid || price <= 0){
             if (priceText.isEmpty()) {
                 errores.add("Please enter a valid price");
             } else if (!priceValid) {
                 errores.add("Price must be a number");
             } else if (price <= 0) {
                 errores.add("Price must be greater than zero");
             }
            view.txtprice.setStyle("-fx-border-color: red;");
        }
         
        if (!errores.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(String.join("\n", errores));
            alert.showAndWait();
            
            return; 
        }

        try (Connection conn = ConnectionDB.getConnection()) { 
            UserTeacher teacher = new UserTeacher(name, lastname, birthday, email, pass);
            teacher.setTutorInfo(aboutMe, subjects, certificates, price, location);
            dao.save(conn, teacher);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
    

    }
}
