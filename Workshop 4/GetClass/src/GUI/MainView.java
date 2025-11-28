package GUI;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    public MainView() {
        setTitle("GetClasses");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        addHeader();
        addSearchAndCategories();
        addTutorsTabs();   // ⬅️  NUEVO
    }

    private void addHeader() {
        JPanel header = new JPanel();
        JLabel title = new JLabel("Find Your Ideal Tutor!!");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        header.add(title);

        add(header, BorderLayout.NORTH);
    }

    private void addSearchAndCategories() {
        JPanel searchPanel = new JPanel(new BorderLayout());

        // Search bar
        JPanel topSearch = new JPanel(new FlowLayout());
        JTextField txtSearch = new JTextField("Search subjects...");
        txtSearch.setPreferredSize(new Dimension(300, 30));
        JButton btnSearch = new JButton("🔍");

        topSearch.add(txtSearch);
        topSearch.add(btnSearch);

        searchPanel.add(topSearch, BorderLayout.NORTH);

        // Categories
        JPanel categoriesPanel = new JPanel(new FlowLayout());
        String[] cats = {"Training", "Math", "English", "Dance", "Guitar",
                "Programming", "Piano", "Finance"};

        for (String c : cats) {
            JButton btn = new JButton(c);
            categoriesPanel.add(btn);
        }

        searchPanel.add(categoriesPanel, BorderLayout.SOUTH);

        add(searchPanel, BorderLayout.CENTER);
    }

    // ⬅️ REEMPLAZA addTutorsList()
    private void addTutorsTabs() {
        TutorTabsPanel tabs = new TutorTabsPanel();
        tabs.setPreferredSize(new Dimension(900, 450)); // Ajusta el tamaño del panel inferior
        add(tabs, BorderLayout.SOUTH);
    }
}
