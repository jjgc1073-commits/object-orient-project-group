package GUI;

import DataBase.DTO.UserTeacherDTO;
import GUI.Controller.TutorProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class ProfileView extends Stage {

    private int id;
    private UserTeacherDTO tutor;
    private Stage stage;

    public ProfileView(int id) {
        this.id = id;
        this.stage = stage;

        TutorProfile tut = new TutorProfile(this.id);
        this.tutor = tut.defineTutor();

        BorderPane root = new BorderPane();

        // --- WEST PANEL ---
        VBox westPanel = new VBox();
        westPanel.setSpacing(15);
        westPanel.setPadding(new Insets(10));

        // Back button
        Button btnBack = new Button("←");
        westPanel.getChildren().add(btnBack);

        // Title
        Label title = new Label("Hi! I'm " + tutor.name);
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");
        westPanel.getChildren().add(title);

        // Subjects
        HBox subjectsBox = new HBox(10);
        subjectsBox.setAlignment(Pos.CENTER_LEFT);
        for (String s : tutor.tutorInfo.subjects) {
            Button btn = new Button(s);
            subjectsBox.getChildren().add(btn);
        }
        westPanel.getChildren().add(subjectsBox);

        // About Me
        Label aboutTitle = new Label("About Me:");
        aboutTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        TextArea aboutArea = new TextArea(tutor.tutorInfo.aboutMe);
        aboutArea.setWrapText(true);
        aboutArea.setEditable(false);
        aboutArea.setPrefHeight(100);

        VBox aboutBox = new VBox(5, aboutTitle, aboutArea);
        westPanel.getChildren().add(aboutBox);

        ScrollPane scroll = new ScrollPane(westPanel);
        scroll.setFitToWidth(true);

        root.setLeft(scroll);

        Scene scene = new Scene(root, 900, 700);
        stage.setScene(scene);
        stage.setTitle("GetClasses");
        stage.show();
    }
}
