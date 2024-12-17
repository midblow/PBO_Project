package Landing_Page;

import javax.swing.*;

import DB.*;
import Provider.HomeProvider;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PLoginForm extends JFrame{

    private boolean verifyLogin(String gmail, String password) {
        try (Connection conn = DbConnection.getConnection()) {
            if (conn != null) {
                String query = "SELECT id_provider, gmail FROM provider WHERE gmail = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, gmail);
                    stmt.setString(2, password);
                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            Session.loggedInProviderId = rs.getInt("id_provider");
                            Session.loggedInProviderEmail = rs.getString("gmail");
                            return true; 
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Provider");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(450, 650); 
        frame.getContentPane().setBackground(new Color(12, 34, 64)); 
        frame.setLayout(new GridBagLayout());
        
        JPanel roundedPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        roundedPanel.setBackground(new Color(20, 44, 84)); 
        roundedPanel.setPreferredSize(new Dimension(350, 500));
        roundedPanel.setLayout(null);
        
        ImageIcon astronautIconImage = new ImageIcon("asset/hello_provider.png");
        Image scaledAstronautIcon = astronautIconImage.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH); // Resize astronaut icon
        JLabel astronautIcon = new JLabel(new ImageIcon(scaledAstronautIcon));
        astronautIcon.setBounds(135, 50, 80, 80); 
        roundedPanel.add(astronautIcon);

        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(105, 140, 140, 30); 
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Poppins", Font.BOLD, 24));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        roundedPanel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(70, 200, 40, 30);
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(70, 230, 220, 30);
        emailField.setBackground(new Color(12, 34, 64));
        emailField.setForeground(Color.WHITE);
        emailField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        emailField.setCaretColor(Color.WHITE);
        roundedPanel.add(emailField);

        ImageIcon emailIconImage = new ImageIcon("asset/email.png");
        Image scaledEmailIcon = emailIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel emailIcon = new JLabel(new ImageIcon(scaledEmailIcon));
        emailIcon.setBounds(300, 230, 20, 20); 
        roundedPanel.add(emailIcon);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 280, 80, 30);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Poppins", Font.PLAIN, 14));
        roundedPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(70, 310, 220, 30);
        passwordField.setBackground(new Color(12, 34, 64));
        passwordField.setForeground(Color.WHITE);
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.WHITE));
        passwordField.setCaretColor(Color.WHITE);
        roundedPanel.add(passwordField);

        ImageIcon passwordIconImage = new ImageIcon("asset/password.png");
        Image scaledPasswordIcon = passwordIconImage.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        JLabel passwordIcon = new JLabel(new ImageIcon(scaledPasswordIcon));
        passwordIcon.setBounds(300, 310, 20, 20); 
        roundedPanel.add(passwordIcon);

        JCheckBox rememberMe = new JCheckBox("Remember Me");
        rememberMe.setBounds(70, 360, 120, 20);
        rememberMe.setBackground(new Color(12, 34, 64));
        rememberMe.setForeground(Color.WHITE);
        roundedPanel.add(rememberMe);

        JLabel forgotPassword = new JLabel("Forgot Password");
        forgotPassword.setBounds(240, 360, 120, 20); 
        forgotPassword.setForeground(Color.WHITE);
        forgotPassword.setFont(new Font("Poppins", Font.PLAIN, 12));
        forgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
        roundedPanel.add(forgotPassword);

        JButton loginButton = new JButton("Log In");
        loginButton.setBounds(110, 400, 200, 40);
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(new Color(12, 34, 64));
        loginButton.setFocusPainted(false);
        loginButton.setFont(new Font("Poppins", Font.BOLD, 14));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = emailField.getText();
                String password = new String(passwordField.getPassword());

                PLoginForm loginForm = new PLoginForm();
                if (loginForm.verifyLogin(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose();
                    HomeProvider.main(new String[]{}); 
                    frame.dispose(); 
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        roundedPanel.add(loginButton);

        JLabel signUpLabel = new JLabel("Don't have an account? Sign Up");
        signUpLabel.setBounds(110, 460, 200, 20); // Posisikan lebih bawah untuk keseimbangan
        signUpLabel.setForeground(Color.WHITE);
        signUpLabel.setFont(new Font("Poppins", Font.PLAIN, 12));
        signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        signUpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new PSignUpForm();  
                    }
                });
                frame.dispose();  
            }
        });
        roundedPanel.add(signUpLabel);        

        frame.add(roundedPanel);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
