package GUI.Controller;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Classes.User;
import Classes.UserStudent;
import Classes.UserTeacher;
import DataBase.ConnectionDB;
import DataBase.DAOS.UserDAO;
import DataBase.DAOS.UserStudentDAO;
import DataBase.DAOS.UserTeacherDAO;
import GUI.RegisterForm;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

public class RegisterController {

    public RegisterForm view;
    public UserDAO dao;
    public User user;


    public RegisterController(RegisterForm view){
        this.view = view;
        this.view.btnRegister.setOnAction(e -> register());
    }

    public void register(){
        String rol = this.view.txtrol.getValue();
        String name = this.view.txtName.getText();
        String lastName = this.view.txtLastName.getText();
        LocalDate birthday = this.view.txtbithdate.getValue();
        String email = this.view.txtEmail.getText();
        String emailC = this.view.txtEmailC.getText();
        String pass = this.view.txtPass.getText();
        String passC = this.view.txtPassC.getText();


        List<String> errores = new ArrayList<>();

        if (name.isEmpty()) {
            errores.add("El nombre no puede estar vacío");
            view.txtName.setStyle("-fx-border-color: red;");
        }else view.txtName.setStyle(null);
        if (lastName.isEmpty()) {
            errores.add("El apellido no puede estar vacío");
            view.txtLastName.setStyle("-fx-border-color: red;");
        }else view.txtLastName.setStyle(null);
        if (birthday == null){
            errores.add("Debe seleccionar una fecha de nacimiento");
            view.txtbithdate.setStyle("-fx-border-color: red;");
        }else view.txtbithdate.setStyle(null);
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            errores.add("Email inválido");
            view.txtEmail.setStyle("-fx-border-color: red;");
        }else view.txtEmail.setStyle(null);
        if (!email.equals(emailC)) {
            errores.add("Los emails no coinciden");
            view.txtEmailC.setStyle("-fx-border-color: red;");
        }else view.txtEmailC.setStyle(null);
        if (pass.isEmpty() || pass.length() < 5) {
            errores.add("Contraseña inválida (mínimo 5 caracteres)");
            view.txtPass.setStyle("-fx-border-color: red;");
        }else view.txtPass.setStyle(null);
        if (!pass.equals(passC)) {
            errores.add("Las contraseñas no coinciden");
            view.txtPassC.setStyle("-fx-border-color: red;");
        }else view.txtPassC.setStyle(null);
        if (rol == null) errores.add("Debe seleccionar un rol");

        if (!errores.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(String.join("\n", errores));
            alert.showAndWait();
            
            return; 
        }

        
        try (Connection conn = ConnectionDB.getConnection()) {
            if ("STUDENT".equals(rol)) {
                UserStudent student = new UserStudent(name, lastName, birthday, email, pass);
                UserStudentDAO studentDAO = new UserStudentDAO();
                studentDAO.save(conn, student);
            } else if ("TEACHER".equals(rol)) {
                UserTeacher teacher = new UserTeacher(name, lastName, birthday, email, pass);
                UserTeacherDAO teacherDAO = new UserTeacherDAO();
                teacherDAO.save(conn, teacher);
                }
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
    }
    
}
