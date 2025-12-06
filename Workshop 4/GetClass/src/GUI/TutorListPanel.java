package GUI;

import DataBase.DTO.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TutorListPanel extends JPanel {

    public TutorListPanel(List<UserteacherDTO> teachers) {

        setLayout(new BorderLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        for (UserteacherDTO t : teachers) {
            TutorCard card = new TutorCard(
                    t.name,
                    t.age,
                    t.id,
                    t.tutorInfo.getAboutMe(),
                    t.tutorInfo.getSubjects().toArray(new String[0]),
                    t.tutorInfo.getHourlyRate(),
                    t.tutorInfo.getRating());

            content.add(card);
            content.add(Box.createVerticalStrut(15));
        }

        JScrollPane scroll = new JScrollPane(content);
        add(scroll, BorderLayout.CENTER);
    }
}
