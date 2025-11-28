package GUI;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {
    
    public JTextField txtUser;
    public JPasswordField txtPass;
    public JButton btnLogin;

    public LoginForm() {
        setTitle("Login");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Email:"));
        txtUser = new JTextField();
        panel.add(txtUser);

        panel.add(new JLabel("Contraseña:"));
        txtPass = new JPasswordField();
        panel.add(txtPass);

        btnLogin = new JButton("Iniciar sesión");
        panel.add(new JLabel());  // espacio
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
    }
}
