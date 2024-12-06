package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileView extends JFrame {

    public ProfileView() {
        // Set up the Profile View Frame
        setTitle("Profile");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(12, 34, 64));
        profilePanel.setLayout(new BorderLayout());

        // Create a top panel to hold the back button aligned to the left
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(12, 34, 64));
        JLabel backButton = new JLabel(new ImageIcon(new ImageIcon("asset/backProfile.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the profile view
            }
        });
        topPanel.add(backButton);

        // Add the top panel to the profile panel
        profilePanel.add(topPanel, BorderLayout.NORTH);

        // Center Panel for Profile Image and Info
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(12, 34, 64));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        // Profile picture
        JLabel profilePic = new JLabel(new ImageIcon(new ImageIcon("asset/Zoro.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(profilePic);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Name label
        JLabel nameLabel = new JLabel("Zoro");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(nameLabel);

        // Add the center panel to the profile panel
        profilePanel.add(centerPanel, BorderLayout.CENTER);

        // Add the profile panel to the frame
        add(profilePanel);
        setVisible(true);
    }

    // Method to show the Profile view when the profile icon is clicked
    public static void showProfileView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfileView();
            }
        });
    }
}
