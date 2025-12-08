package GUI;

import javax.swing.*;

import DataBase.DTO.UserteacherDTO;
import GUI.Controller.TutorProfile;

import java.awt.*;

public class ProfileView extends JFrame{

    public int id;
    public UserteacherDTO tutor;
    private JPanel westPanel;
    private JPanel eastPanel;
    
    public ProfileView(int id){
        this.id = id;
        TutorProfile tut = new TutorProfile(this.id);
        this.tutor = tut.defineTutor();

        setTitle("GetClasses");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        westPanel = new JPanel(new BorderLayout());
        westPanel.setPreferredSize(new Dimension(600, 700));
        eastPanel = new JPanel(new BorderLayout());
        eastPanel.setPreferredSize(new Dimension(300, 700));

        add(eastPanel, BorderLayout.EAST);

        JScrollPane scrollWeast = new JScrollPane(westPanel,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollWeast, BorderLayout.WEST);
        addWestHeader();
        addAboutMe();
    }


    public void addWestHeader(){
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        JPanel topBar = new JPanel(new BorderLayout());
        JButton btnBack = new JButton("←");
        topBar.add(btnBack, BorderLayout.WEST);
        header.add(topBar);

        JLabel title = new JLabel("Hi! I'm " + tutor.name);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);

        header.add(Box.createVerticalStrut(15));

        JPanel subjectsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subjectsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (String s : tutor.tutorInfo.subjects) {
            JButton btn = new JButton(s);
            subjectsPanel.add(btn);
        }
        header.add(subjectsPanel);

        westPanel.add(header, BorderLayout.NORTH);
        
    }

    public void addAboutMe(){
        JPanel aboutme = new JPanel();
        aboutme.setLayout(new BoxLayout(aboutme, BoxLayout.Y_AXIS));
        aboutme.setAlignmentX(Component.CENTER_ALIGNMENT);
        aboutme.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel abouttittle = new JLabel("About Me:");
        abouttittle.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(abouttittle);

        JTextArea about = new JTextArea(tutor.tutorInfo.aboutMe);
        about.setEditable(false);
        about.setLineWrap(true);
        about.setWrapStyleWord(true);
        about.setOpaque(true);
        about.setFont(new Font("Arial", Font.PLAIN, 14));
        about.setAlignmentX(Component.CENTER_ALIGNMENT);  
        about.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        about.setPreferredSize(new Dimension(400, 100));
        
        aboutme.add(titlePanel);
        aboutme.add(about);
        
        westPanel.add(aboutme, BorderLayout.CENTER);
    }

    // lunes 8 de diciembre termino esto, vaya haciendo otras pantallas :D




}
