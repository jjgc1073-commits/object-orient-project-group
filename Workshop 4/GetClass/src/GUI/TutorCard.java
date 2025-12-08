package GUI;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import GUI.Controller.TutorCardListener;



import java.awt.*;

public class TutorCard extends JPanel {

    private int id;
    public int idClicked;
    private TutorCardListener listener;

    public TutorCard(String name, int age, int id, String about, String[] subjects, double cost, double rating) {
        
        this.id = id;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setPreferredSize(new Dimension(800, 180));


        // --- FOTO ---
        JLabel photo = new JLabel();
        photo.setPreferredSize(new Dimension(120, 120));
        photo.setOpaque(true);
        photo.setBackground(Color.LIGHT_GRAY); // Placeholder
        photo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        photo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(listener != null) listener.onTutorClicked(id);
            }
        });
        add(photo, BorderLayout.WEST);
    
        

        // --- PANEL CENTRAL (info y materias) ---
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel(name + " · " + age + " years");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        nameLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(listener != null) listener.onTutorClicked(id);
            }
        });


       


        JLabel aboutLabel = new JLabel("About me:");
        JTextArea aboutText = new JTextArea(about);
        aboutText.setEditable(false);
        aboutText.setLineWrap(true);
        aboutText.setWrapStyleWord(true);
        aboutText.setRows(3);

        infoPanel.add(nameLabel);
        infoPanel.add(aboutLabel);
        infoPanel.add(aboutText);

        // Subjects buttons
        JPanel subjectsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        for (String s : subjects) {
            JButton btn = new JButton(s);
            subjectsPanel.add(btn);
        }

        infoPanel.add(subjectsPanel);

        add(infoPanel, BorderLayout.CENTER);

        // --- PANEL DERECHO (precio, rating, favorito) ---
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(150, 150));

        JLabel costLabel = new JLabel("COST/HOUR: $" + cost);
        JButton favButton = new JButton("♥");
        JLabel ratingLabel = new JLabel("Rating: " + rating);

        rightPanel.add(costLabel);
        rightPanel.add(Box.createVerticalStrut(10));
        rightPanel.add(favButton);
        rightPanel.add(Box.createVerticalStrut(20));
        rightPanel.add(ratingLabel);

        add(rightPanel, BorderLayout.EAST);
    }


    public void setListener(TutorCardListener listener){
            this.listener = listener;
        }
}

