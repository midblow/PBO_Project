package Provider;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfileView extends JFrame {

    public ProfileView() {
        setTitle("Profile");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel profilePanel = new JPanel();
        profilePanel.setBackground(new Color(12, 34, 64));
        profilePanel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(new Color(12, 34, 64));
        JLabel backButton = new JLabel(new ImageIcon(new ImageIcon("asset/backProfile.png").getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); 
            }
        });
        topPanel.add(backButton);

        profilePanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(12, 34, 64));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel profilePic = new JLabel(new ImageIcon(new ImageIcon("asset/Zoro.jpg").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)));
        profilePic.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(profilePic);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel nameLabel = new JLabel("Erwin");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(nameLabel);

        profilePanel.add(centerPanel, BorderLayout.CENTER);

        add(profilePanel);
        setVisible(true);
    }

    public static void showProfileView() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProfileView();
            }
        });
    }
}
